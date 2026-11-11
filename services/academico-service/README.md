# academico-service

Estrutura inicial independente, gerada pelo [Spring Initializr](https://start.spring.io/), com Java 21, Spring Boot 4.1.2 e Maven Wrapper. O package base é `com.gestaopedagogica.academico`.

A organização seguirá as camadas técnicas da ADR-004. Os pacotes serão criados conforme a implementação exigir; esta etapa contém somente a aplicação e seu teste de inicialização.

## Testes

Com um JDK 21 configurado, execute neste diretório:

```bash
./mvnw test
```

No Windows, utilize `mvnw.cmd test`.

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

A execução normal mantém a autoconfiguração de persistência habilitada e requer PostgreSQL configurado externamente para o banco `academico_db`, conforme a ADR-002:

- `SPRING_DATASOURCE_URL` (por exemplo, `jdbc:postgresql://localhost:5432/academico_db`).
- `SPRING_DATASOURCE_USERNAME`.
- `SPRING_DATASOURCE_PASSWORD`.

Após configurar essas variáveis, execute `./mvnw spring-boot:run`. Não há credenciais no projeto, migrações, funcionalidades de negócio ou integração Kafka nesta etapa.
