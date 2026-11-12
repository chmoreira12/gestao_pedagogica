# academico-service

Estrutura inicial independente, gerada pelo [Spring Initializr](https://start.spring.io/), com Java 21, Spring Boot 4.1.2 e Maven Wrapper. O package base é `com.gestaopedagogica.academico`.

A organização seguirá as camadas técnicas da ADR-004. Os pacotes serão criados conforme a implementação exigir; esta etapa contém somente a aplicação e seu teste de inicialização.

## Testes

Com um JDK 21 configurado, execute neste diretório:

```bash
./mvnw clean test
```

No Windows, utilize `mvnw.cmd clean test`.

O teste `contextLoads` carrega o contexto Spring com as autoconfigurações de DataSource, Hibernate JPA, repositories JPA e Flyway excluídas somente no teste. Não exige PostgreSQL, Docker ou banco em memória. Ele não valida persistência nem migrações.

## Dependências

- `spring-boot-starter-webmvc`: Spring Web MVC.
- `spring-boot-starter-data-jpa`: persistência JPA.
- `spring-boot-starter-validation`: Jakarta Bean Validation.
- `postgresql`: driver PostgreSQL, em runtime.
- `spring-boot-starter-flyway` e `flyway-database-postgresql`: Flyway e suporte ao PostgreSQL.
- `lombok`: dependência opcional e annotation processor.
- `spring-boot-starter-actuator`: observabilidade.
- `spring-boot-starter-test`: suporte aos testes, com escopo test.

As versões das dependências são gerenciadas pelo parent Spring Boot 4.1.2. Os starters Web MVC e Flyway seguem a nomenclatura retornada pelo Initializr para essa versão. Os starters especializados de testes gerados pelo Initializr foram substituídos pelo starter geral, suficiente para este teste básico.

## Execução da aplicação

A configuração de conexão está em `src/main/resources/application.yml`. A infraestrutura local descrita em `infra/README.md`, na raiz do repositório, disponibiliza PostgreSQL 18.6 em `127.0.0.1:5434`.

| Variável de ambiente | Uso |
| --- | --- |
| `ACADEMICO_DB_URL` | Opcional. Padrão `jdbc:postgresql://127.0.0.1:5434/academico_db`. |
| `ACADEMICO_DB_USERNAME` | Obrigatória. Para a infraestrutura local, use `academico_user`. |
| `ACADEMICO_DB_PASSWORD` | Obrigatória. Use a senha vigente de `academico_user` no PostgreSQL. |

O `infra/.env` fornece as credenciais ao Docker Compose. Para a execução Java, configure as variáveis também no ambiente do IntelliJ ou do terminal. O Spring Boot não carrega esse arquivo automaticamente. Alterar o `.env` após a inicialização do volume exige atualizar a senha no PostgreSQL por procedimento administrativo.

### IntelliJ

Na configuração de execução de `AcademicoServiceApplication`, selecione o JDK 21 e preencha `ACADEMICO_DB_USERNAME` e `ACADEMICO_DB_PASSWORD` em **Environment variables**. Mantenha a configuração com a senha apenas no ambiente local, fora do versionamento. Configure `ACADEMICO_DB_URL` quando precisar sobrescrever o endereço padrão.

### Maven no Ubuntu

Com o PostgreSQL local disponível, execute neste diretório. O exemplo solicita a senha sem gravá-la no histórico do Bash.

```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export ACADEMICO_DB_USERNAME=academico_user
read -r -s -p 'Senha de academico_user: ' ACADEMICO_DB_PASSWORD
echo
export ACADEMICO_DB_PASSWORD

./mvnw spring-boot:run
```

Ajuste `JAVA_HOME` se o JDK 21 estiver instalado em outro caminho.

### JPA, Flyway e estado da validação

O Flyway está habilitado e utiliza a mesma conexão da aplicação. As migrações serão criadas em `src/main/resources/db/migration` conforme a implementação do domínio. O Hibernate está configurado com `ddl-auto: validate` para validar os mapeamentos, e `open-in-view: false` concentra o acesso JPA nas operações da aplicação.

A infraestrutura teve sua inicialização, conexões autorizadas e isolamento validados, conforme informado pelo desenvolvedor. A configuração de conexão do serviço está implementada. A inicialização da aplicação com PostgreSQL e a execução efetiva do Flyway ainda aguardam validação registrada. Também falta executar novamente `./mvnw clean test` com Java 21 após a inclusão da configuração de conexão. O teste básico exclui as autoconfigurações de persistência e Flyway.

O próximo passo é validar esses dois cenários e registrar os resultados. As primeiras migrações e os testes de persistência acompanharão a implementação das funcionalidades acadêmicas.
