package com.gestaopedagogica.academico.repository;

import java.util.UUID;

import com.gestaopedagogica.academico.entity.Coordenador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.dao.DataIntegrityViolationException;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import jakarta.persistence.EntityManager;
import org.hibernate.exception.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest(properties = "spring.jpa.hibernate.ddl-auto=validate")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class CoordenadorRepositoryIT {
    @Container
    @ServiceConnection
    static final PostgreSQLContainer POSTGRES = new PostgreSQLContainer("postgres:18.6-trixie");

    @Autowired
    CoordenadorRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    void devePersistirERecuperarDoBanco() {
        var salvo = repository.saveAndFlush(new Coordenador("Maria", "52998224725", "maria@example.com"));
        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getDataCadastro()).isNotNull();
        var id = salvo.getId();
        var data = salvo.getDataCadastro();
        entityManager.clear();
        var recuperado = repository.findById(id).orElseThrow();
        assertThat(recuperado).isNotSameAs(salvo);
        assertThat(recuperado.getNome()).isEqualTo("Maria");
        assertThat(recuperado.getCpf()).isEqualTo("52998224725");
        assertThat(recuperado.getEmail()).isEqualTo("maria@example.com");
        assertThat(recuperado.getDataCadastro()).isEqualTo(data);
    }

    @Test
    void deveRetornarVazioParaIdInexistente() {
        assertThat(repository.findById(UUID.randomUUID())).isEmpty();
    }

    @Test
    void deveRejeitarCpfDuplicado() {
        repository.saveAndFlush(new Coordenador("Maria", "52998224725", "primeiro@example.com"));
        verificarViolacao("52998224725", "outro@example.com", "uk_coordenadores_cpf");
    }

    @Test
    void deveRejeitarEmailDuplicado() {
        repository.saveAndFlush(new Coordenador("Maria", "52998224725", "maria@example.com"));
        verificarViolacao("12345678909", "maria@example.com", "uk_coordenadores_email_lower");
    }

    @Test
    void deveRejeitarEmailComCapitalizacaoDiferente() {
        repository.saveAndFlush(new Coordenador("Maria", "52998224725", "maria@example.com"));
        verificarViolacao("12345678909", "MARIA@example.com", "uk_coordenadores_email_lower");
    }

    @Test
    void deveRejeitarCpfComLetras() {
        verificarViolacao("5299822472A", "maria@example.com", "ck_coordenadores_cpf_formato");
    }

    @Test
    void deveRejeitarCpfCurto() {
        verificarViolacao("123", "maria@example.com", "ck_coordenadores_cpf_formato");
    }

    private void verificarViolacao(String cpf, String email, String restricao) {
        assertThatThrownBy(() -> repository.saveAndFlush(new Coordenador("Maria", cpf, email)))
                .isInstanceOf(DataIntegrityViolationException.class)
                .satisfies(erro -> {
                    Throwable causa = erro;
                    while (causa != null && !(causa instanceof ConstraintViolationException)) {
                        causa = causa.getCause();
                    }
                    assertThat(causa).isInstanceOf(ConstraintViolationException.class);
                    assertThat(((ConstraintViolationException) causa).getConstraintName()).isEqualTo(restricao);
                });
        // A transação será revertida pelo teste; nenhuma operação segue a violação.
    }
}
