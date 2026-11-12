# Progresso do Projeto — Sistema de Gestão Pedagógica

**Última atualização:** 12/11/2026
**Fase atual:** Entidade JPA Coordenador implementada e validada com PostgreSQL
**Status geral:** Em desenvolvimento

## 1. Objetivo

Desenvolver um Sistema de Gestão Pedagógica utilizando tecnologias e práticas comuns no desenvolvimento corporativo com Java e Spring Boot.

O projeto tem como objetivo consolidar conhecimentos de desenvolvimento backend, microsserviços, persistência, segurança, mensageria, testes automatizados e implantação.

## 2. Decisões arquiteturais documentadas e aprovadas

- [x] ADR-001 — Separação dos microsserviços Acadêmico e Identidade.
- [x] ADR-002 — Persistência independente por microsserviço.
- [x] ADR-003 — Integração assíncrona utilizando Apache Kafka.
- [x] ADR-004 — Arquitetura interna tradicional em camadas.

As quatro ADRs estão aprovadas na documentação. A infraestrutura local dos bancos prevista na ADR-002 foi implementada e validada. O `academico-service` iniciou com PostgreSQL e aplicou a primeira migração Flyway com sucesso. A entidade JPA `Coordenador` foi implementada e validada contra a tabela existente. Mensageria e demais camadas de negócio permanecem planejadas.

## 3. Tecnologias definidas

- Java 21 LTS.
- Spring Boot 4.1.2.
- Apache Maven.
- PostgreSQL.
- Spring Data JPA e Hibernate.
- Flyway.
- Jakarta Validation.
- Lombok.
- MapStruct.
- Spring Security.
- Apache Kafka.
- Docker e Docker Compose.
- Kubernetes.
- JUnit 5, Mockito e Testcontainers.

As tecnologias serão introduzidas conforme as necessidades de implementação.

## 4. Estrutura do repositório

O repositório reúne documentação e uma aplicação independente:

- `services/academico-service`: estrutura inicial existente, sem funcionalidades acadêmicas.
- `infra/`: Docker Compose PostgreSQL, exemplo de variáveis de ambiente, script de inicialização dos bancos e README de operação local.
- `docs/requisitos.md`: requisitos funcionais documentados.
- `docs/decisoes/`: ADRs 001 a 004 existentes e aprovadas.
- Arquivos da raiz: `README.md`, `AGENTS.md`, `PROGRESSO.md` e `.gitignore`.

Não existem `services/identidade-service` ou `frontend`. O serviço de identidade está planejado; frontend está fora do escopo da V1. Não existem `ROADMAP.md` ou `docs/arquitetura.md`.

## 5. Atividades concluídas

### Planejamento

- [x] Definição do escopo da versão 1.
- [x] Documentação dos requisitos funcionais.
- [x] Definição dos microsserviços.
- [x] Definição da estratégia de persistência.
- [x] Definição do primeiro evento Kafka: `CoordenadorCadastrado`.
- [x] Definição da arquitetura em camadas.
- [x] Definição das versões iniciais de Java e Spring Boot.
- [x] Diretrizes iniciais de testes documentadas no AGENTS.md; teste básico descrito no README do serviço.

### Repositório e ferramentas

- [x] Configuração do remoto `origin` apontando para o GitHub. A disponibilidade remota não foi verificada nesta auditoria.
- [x] Configuração inicial do Git.
- [x] Criação do README.md.
- [x] Criação do AGENTS.md.
- [x] Criação das quatro ADRs.

Registros históricos de ambiente preservados, sem confirmação independente nesta auditoria:

- Instalação do Codex CLI, anteriormente registrada como concluída.
- Autenticação do Codex com ChatGPT, anteriormente registrada como concluída.

Esses registros não são critérios de conclusão da implementação.

### Implementação

- [x] Geração da estrutura inicial do `academico-service`.
- [x] Configuração inicial do Maven Wrapper.
- [x] Inclusão das dependências iniciais do microsserviço.
- [x] Confirmação do resultado dos testes iniciais: 1 teste, 0 falhas, 0 erros, 0 ignorados; `BUILD SUCCESS` em 10/11/2026.
- [x] Auditoria local da estrutura, dependências, configuração e teste básico do serviço em 10/11/2026.
- [x] Migração de `application.properties` para `application.yml`, preservando inicialmente o nome da aplicação.
- [x] Configuração de conexão PostgreSQL por variáveis de ambiente, validação de esquema JPA e habilitação do Flyway.
- [x] Atualização do README do serviço com variáveis `ACADEMICO_DB_*`, endereço local, execução por IntelliJ/Maven e próximos passos de validação.
- [x] Versionamento local da estrutura inicial, configuração YAML, infraestrutura e configuração de conexão. Commits identificados abaixo.
- [x] Detalhamento dos requisitos de CPF e e-mail dos coordenadores e da exclusividade entre coordenador e diretor na V1.
- [x] Aplicação da migração `V1__create_coordenadores.sql` via Flyway e inspeção da tabela, restrições e índice no PostgreSQL.
- [x] Inicialização da aplicação com banco usando Java 21.0.13.0.3 e execução de `./mvnw clean verify` com sucesso.

- [x] Implementação da entidade JPA `Coordenador` no pacote `entity` e remoção do import `UniqueConstraint` sem uso.
- [x] Validação do mapeamento pelo Hibernate com PostgreSQL e `ddl-auto: validate`, acompanhada de `./mvnw clean verify` aprovado em Java 21.

### Infraestrutura PostgreSQL local

- [x] Criação da pasta `infra/` e dos arquivos `compose.yml`, `.env.example`, `postgres/init/01-create-databases.sh` e `README.md`.
- [x] Configuração de uma instância PostgreSQL `18.6`, com imagem `postgres:18.6-trixie`, porta local `5434`, volume nomeado e healthcheck.
- [x] Criação de `academico_db` com usuário próprio `academico_user` e de `identidade_db` com usuário próprio `identidade_user`.
- [x] Validação das conexões autorizadas e do isolamento entre os bancos.
- [x] Confirmação do estado saudável do container PostgreSQL.
- [x] Configuração das credenciais locais em `infra/.env`, protegido pelas regras do `.gitignore`. O arquivo `.env.example` contém somente comentários e valores vazios.

## 6. Estado atual e evidências da auditoria

A estrutura inicial, a infraestrutura PostgreSQL local e a inicialização do serviço com banco foram validadas. A V1 criou a tabela de coordenadores e foi registrada com sucesso no histórico Flyway. A migração e os requisitos foram versionados no commit `7d3707b`. A entidade JPA também foi validada. O próximo passo de implementação é a persistência de coordenadores e seus testes de integração, conforme a ADR-004.

### Histórico de versionamento verificado em 11/11/2026

- `33652f7` — estrutura inicial do `academico-service`.
- `bf74d5c` — migração da configuração para YAML.
- `d332af2` — infraestrutura PostgreSQL com Docker Compose.
- `a53d1a6` — configuração PostgreSQL e Flyway no serviço.

Na consulta local, `main` e a referência `origin/main` apontavam para `a53d1a6`. O remoto não foi consultado nesta atualização. Na auditoria inicial, `services/` ainda não era rastreado; essa situação foi superada pelos commits acima. Posteriormente, o commit `7d3707b` registrou a estrutura de persistência de coordenadores, incluindo a V1 final e os requisitos aprovados. A entidade e sua atualização de progresso compõem a entrega atual.

### Estrutura e dependências verificadas

- Projeto Maven independente com parent Spring Boot `4.1.2`, `java.version=21` e package base `com.gestaopedagogica.academico`.
- Maven Wrapper `3.3.4`, distribuição Maven `3.10.0`, scripts Linux/macOS e Windows.
- Classe principal `AcademicoServiceApplication` com `@SpringBootApplication`.
- `application.yml` preserva `spring.application.name=academico-service` e configura URL JDBC com padrão `jdbc:postgresql://127.0.0.1:5434/academico_db`, sobrescrita por `ACADEMICO_DB_URL`, e credenciais obrigatórias `ACADEMICO_DB_USERNAME` e `ACADEMICO_DB_PASSWORD`.
- JPA configurado com `open-in-view: false` e `hibernate.ddl-auto: validate`; Flyway habilitado com `spring.flyway.enabled: true`.
- Starters: `spring-boot-starter-webmvc`, `spring-boot-starter-data-jpa`, `spring-boot-starter-validation`, `spring-boot-starter-flyway` e `spring-boot-starter-actuator`.
- Complementos: `flyway-database-postgresql`, driver `postgresql` em runtime e `lombok` opcional, configurado como annotation processor.
- Testes: `spring-boot-starter-test` com escopo test. Versões das dependências gerenciadas pelo parent Spring Boot.
- Plugins Spring Boot e Maven Compiler presentes. README próprio, regras de exclusão Git e atributos dos scripts presentes.
- A entidade `entity.Coordenador` está implementada. Controllers, services, repositories, DTOs, mappers e endpoints de negócio permanecem pendentes, com criação sob demanda conforme a ADR-004.
- A migração `src/main/resources/db/migration/V1__create_coordenadores.sql` está aplicada. Não há Kafka, Spring Security, MapStruct ou Testcontainers. As credenciais do serviço são referenciadas por variáveis de ambiente. O mapeamento JPA foi validado na inicialização. Operações de gravação e leitura de coordenadores ainda aguardam implementação e testes.

### Testes executados em 10/11/2026

Comando no diretório `services/academico-service`: `./mvnw -B test`.

| Verificação | Resultado |
| --- | --- |
| Classe executada | `AcademicoServiceApplicationTests` |
| Método existente | `contextLoads` |
| Testes executados | 1 |
| Falhas | 0 |
| Erros | 0 |
| Ignorados | 0 |
| Status | `BUILD SUCCESS` |
| Tempo total Maven | 7,517 s |
| Ambiente | OpenJDK 25.0.5.0.3; projeto configurado para Java 21 |

O teste carrega o contexto Spring Boot com DataSource, Hibernate JPA, repositories JPA e Flyway excluídos somente no teste. Não exige PostgreSQL externo, Docker ou banco em memória. O Actuator foi carregado pelo contexto, sem validação HTTP de endpoints. Esta execução reutilizou classes já compiladas (`Nothing to compile`); não foi realizado `clean`, empacotamento nem execução da aplicação com banco.

A primeira tentativa no sandbox terminou antes de executar testes por bloqueio de acesso ao arquivo de lock do cache `~/.m2`. A repetição autorizada fora do sandbox produziu o resultado acima, sem alterar configurações do projeto.

### Validação manual com Java 21

- [x] Validar o `academico-service` em Java 21.

| Verificação | Resultado |
| --- | --- |
| Sistema operacional | Ubuntu |
| Java | OpenJDK 21.0.13.0.3 |
| Maven Wrapper | Apache Maven 3.10.0 |
| Comando | `./mvnw clean test` |
| Status | `BUILD SUCCESS` |
| Testes executados | 1 |
| Falhas | 0 |
| Erros | 0 |
| Ignorados | 0 |

### Testes após a migração para YAML — 10/11/2026

A execução de `./mvnw clean test` com Java `21.0.13.0.3`, após a substituição de `application.properties` por `application.yml`, terminou com `BUILD SUCCESS`: 1 teste, 0 falhas, 0 erros e 0 ignorados. O contexto iniciou corretamente. Os avisos Maven/Mockito persistiram. Essa execução ocorreu antes da inclusão das propriedades de conexão PostgreSQL e Flyway no YAML.

### Validação da infraestrutura PostgreSQL local — registro em 11/11/2026

Os resultados de execução abaixo foram informados pelo desenvolvedor. Não houve nova execução de testes ou gerenciamento de containers nesta atualização documental.

| Verificação | Resultado |
| --- | --- |
| PostgreSQL no Docker Compose | `18.6`, imagem `postgres:18.6-trixie` |
| Endereço local | `127.0.0.1:5434` |
| Persistência | Volume nomeado `gestao-pedagogica-local_postgres_data` |
| Banco acadêmico | `academico_db`, proprietário `academico_user` |
| Banco de identidade | `identidade_db`, proprietário `identidade_user` |
| Conexões autorizadas | Testes passaram |
| Isolamento entre os bancos | Testes passaram |
| Estado do container PostgreSQL | Saudável |
| Credenciais locais | Em `infra/.env`, ignorado pelo Git |
| Configuração de conexão do academico-service | Implementada no YAML, com credenciais por ambiente |
| Inicialização da aplicação com PostgreSQL e execução do Flyway | Validada na execução da V1 descrita abaixo |

Em um volume novo, o entrypoint da imagem executa `infra/postgres/init/01-create-databases.sh`, criando os bancos e seus usuários. O volume preserva os dados nas inicializações seguintes. A revisão dos quatro arquivos de infraestrutura confirmou a consistência dos nomes e comandos e a ausência de credenciais reais no `.env.example`.

### Primeira migração Flyway e build — 11/11/2026

Antes da execução, o PostgreSQL confirmou disponibilidade e `current_database() = academico_db`. A consulta ao histórico Flyway retornou zero registros. A aplicação foi iniciada pelo Maven Wrapper com Java `21.0.13.0.3`, usando o mecanismo Flyway configurado no serviço e conexão JDBC em `127.0.0.1:5434/academico_db`, com usuário `academico_user` e senha fornecida por variável de ambiente.

O Flyway registrou a aplicação bem-sucedida de uma migração, levando o schema `public` à versão 1. A aplicação concluiu a inicialização com JPA. Foi utilizada uma porta HTTP temporária livre, e o processo iniciado para essa validação foi encerrado após a confirmação da inicialização.

| Evidência | Resultado |
| --- | --- |
| PostgreSQL | 18.6 |
| Banco e usuário | `academico_db`, `academico_user` |
| Tabela | `public.coordenadores` criada |
| Colunas | `id UUID`, `nome VARCHAR(150)`, `cpf VARCHAR(11)`, `email VARCHAR(255)`, `data_cadastro TIMESTAMP WITH TIME ZONE`; todas obrigatórias |
| Chave primária | `coordenadores_pkey` em `id` |
| CPF único | `uk_coordenadores_cpf` |
| Formato do CPF | `ck_coordenadores_cpf_formato`, CHECK `^[0-9]{11}$`, validado |
| E-mail único sem diferenciar maiúsculas/minúsculas | Índice `uk_coordenadores_email_lower` em `lower(email)` |
| Script no histórico | `V1__create_coordenadores.sql` |
| Versão da migração | `1` |
| Checksum | `405985958` |
| Usuário registrado no histórico | `academico_user` |
| Sucesso no histórico | `success=true` |
| Comando de build | `./mvnw clean verify`, com Java 21.0.13.0.3 |
| Resultado do build | `BUILD SUCCESS` |
| Testes | 1 executado, 0 falhas, 0 erros, 0 ignorados |
| Empacotamento | JAR gerado e reempacotado pelo Spring Boot |
| Tempo total do build | 9,733 s |

As restrições e o índice foram inspecionados no catálogo do PostgreSQL. Não foram realizados testes de inserção de dados válidos ou inválidos nessa validação. O teste básico `contextLoads()` continua excluindo DataSource, Hibernate JPA, repositories e Flyway. A validação da migração ocorreu pela execução real da aplicação e pela inspeção do banco. A V1 aplicada deve ser preservada; futuras alterações de esquema deverão utilizar novas migrações versionadas.

### Entidade JPA Coordenador e validação — 12/11/2026

A entidade `com.gestaopedagogica.academico.entity.Coordenador` mapeia a tabela `coordenadores` com identificador `UUID`, nome/CPF/e-mail como `String` e data de cadastro como `Instant`. Comprimentos e nulabilidade correspondem à V1. O UUID é gerado pelo JPA com `GenerationType.UUID`, e a data é preenchida pelo Hibernate com `@CreationTimestamp`, usando o relógio da JVM. O construtor sem argumentos é protegido, com getters Lombok e construtor para nome, CPF e e-mail. O import `UniqueConstraint` sem uso foi removido. As restrições de CPF e o índice de e-mail permanecem sob responsabilidade da migração Flyway.

| Verificação | Resultado |
| --- | --- |
| Java | 21.0.13.0.3 |
| Comando | `./mvnw clean verify` |
| Build | `BUILD SUCCESS` |
| Testes | 1 executado, 0 falhas, 0 erros, 0 ignorados |
| Empacotamento | JAR gerado e reempacotado |
| Tempo da última execução | 42,427 s |
| Banco | PostgreSQL 18.6, `academico_db`, conexão em `127.0.0.1:5434` |
| Hibernate | `ddl-auto: validate` mantido, `EntityManagerFactory` inicializado e aplicação iniciada sem erro de mapeamento |
| Flyway | V1 validada, schema atualizado, nenhuma migração pendente |

A aplicação foi executada com porta HTTP temporária livre e encerrada após a validação. O teste básico continua excluindo persistência e Flyway. A validação real de inicialização complementou esse teste, sem executar inserções ou leituras de coordenadores. A geração de UUID e de data no momento da persistência ainda precisa ser exercitada pelos futuros testes de integração. Os avisos Maven HTTP 401 e Mockito/Byte Buddy persistem.

### Pendências e limitações identificadas

- Investigar avisos Maven HTTP 401 ao consultar metadados dos repositórios `flyway-community-db-support` e `MavenFeed`. Não impediram o build; a origem da configuração desses repositórios não foi determinada nesta auditoria.
- Avaliar os avisos de autoanexação do Mockito/Byte Buddy e carregamento dinâmico de agente no JDK utilizado. Não houve falha de teste.
- Implementar e testar as operações de persistência de coordenadores sobre o mapeamento JPA validado. Testes de negócio e integração automatizada da aplicação ainda não existem.
- Atualizar futuramente o estado descrito no README da raiz: ainda informa planejamento arquitetural, embora a estrutura inicial do serviço já exista. O README não foi modificado nesta tarefa.
- A V1 final e os requisitos já estão versionados em `7d3707b`. A entrega atual reúne somente a entidade e este registro de progresso, com commit e push autorizados pelo desenvolvedor.

## 7. Próximas atividades

### Continuidade imediata

1. Concluir o versionamento autorizado da entidade `Coordenador` e deste registro de progresso.
2. Implementar a camada repository e testes de integração da persistência de coordenadores, cobrindo gravação/leitura, geração de UUID/data, restrições e unicidade de e-mail sem diferenciar maiúsculas/minúsculas, em tarefa própria.
3. Detalhar os contratos da futura API e implementar as regras aprovadas de normalização de CPF/e-mail e validação de CPF na aplicação. A exclusividade entre coordenador e diretor também deverá ser atendida na evolução do cadastro.
4. Evoluir o cadastro em camadas e a integração prevista na ADR-003, conforme tarefas autorizadas e seus critérios de aceitação.

### Pendências de acompanhamento

- [ ] Revisar e versionar as atualizações documentais mediante autorização.
- [ ] Avaliar os avisos Maven e Mockito registrados.
- [ ] Atualizar o README da raiz em tarefa própria.

### Implementação ainda não iniciada

- [ ] Implementar repository e testes de persistência de coordenadores; criar novas migrações conforme a evolução da modelagem.
- [ ] Definir e implementar a primeira funcionalidade acadêmica com testes automatizados, respeitando os requisitos e a ADR-004.
- [ ] Criar o `identidade-service` e implementar contas, autenticação e autorização.
- [ ] Detalhar os pontos pendentes da ADR-003 e implementar Kafka, Transactional Outbox, idempotência e tratamento de falhas.
- [ ] Implementar as demais funcionalidades V1: cadastros, cursos, turmas, matrículas, auditoria, consultas e relatórios, conforme detalhamento dos requisitos.
- [ ] Preparar a implantação com Kubernetes e detalhar a hospedagem do PostgreSQL.

Os bancos independentes estão criados e validados na infraestrutura local. A conexão do `academico-service` e a V1 da tabela de coordenadores foram validadas. A estrutura de banco e a entidade JPA constituem uma entrega parcial do cadastro; as funcionalidades de negócio RF-001 a RF-035 ainda não estão concluídas. A normalização na aplicação, validação dos dígitos verificadores e exclusividade dos papéis estão documentadas e aguardam implementação. O evento `CoordenadorCadastrado` e a integração HTTP/Kafka permanecem planejados. V2 e V3 permanecem fora da implementação inicial.

A ordem das atividades futuras poderá ser ajustada conforme as dependências técnicas. Esta atualização foi documental; os testes e a aplicação não foram executados novamente.

## 8. Diretrizes de acompanhamento

- Atualizar este arquivo ao concluir etapas relevantes.
- Registrar o estado real da implementação, sem marcar tarefas planejadas como concluídas.
- Manter apenas uma atividade principal em andamento.
- Revisar código e testes antes de realizar commits.
- Não permitir que o Codex execute commits ou pushes sem autorização explícita.
- Priorizar práticas consolidadas no mercado corporativo Java/Spring Boot.
