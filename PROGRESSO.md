# Progresso do Projeto — Sistema de Gestão Pedagógica

**Última atualização:** 10/11/2026
**Fase atual:** Estrutura inicial do backend validada localmente; versionamento pendente
**Status geral:** Em desenvolvimento

## 1. Objetivo

Desenvolver um Sistema de Gestão Pedagógica utilizando tecnologias e práticas comuns no desenvolvimento corporativo com Java e Spring Boot.

O projeto tem como objetivo consolidar conhecimentos de desenvolvimento backend, microsserviços, persistência, segurança, mensageria, testes automatizados e implantação.

## 2. Decisões arquiteturais documentadas e aprovadas

- [x] ADR-001 — Separação dos microsserviços Acadêmico e Identidade.
- [x] ADR-002 — Persistência independente por microsserviço.
- [x] ADR-003 — Integração assíncrona utilizando Apache Kafka.
- [x] ADR-004 — Arquitetura interna tradicional em camadas.

As quatro ADRs estão aprovadas na documentação. Isso não representa implementação dos dois serviços, dos bancos, da mensageria ou das camadas de negócio.

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
- `docs/requisitos.md`: requisitos funcionais documentados.
- `docs/decisoes/`: ADRs 001 a 004 existentes e aprovadas.
- Arquivos da raiz: `README.md`, `AGENTS.md`, `PROGRESSO.md` e `.gitignore`.

Não existem `services/identidade-service`, `infra` ou `frontend`. Identidade e infraestrutura estão planejadas; frontend está fora do escopo da V1. Não existem `ROADMAP.md` ou `docs/arquitetura.md`.

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
- [ ] Commit e push da estrutura inicial do microsserviço.

## 6. Estado atual e evidências da auditoria

A validação local da estrutura inicial está concluída. O fechamento dessa etapa permanece em andamento exclusivamente quanto à revisão final e ao versionamento autorizado: `git status` mostra `services/` ainda não rastreado. Nenhum commit ou push foi executado nesta auditoria.

### Estrutura e dependências verificadas

- Projeto Maven independente com parent Spring Boot `4.1.2`, `java.version=21` e package base `com.gestaopedagogica.academico`.
- Maven Wrapper `3.3.4`, distribuição Maven `3.10.0`, scripts Linux/macOS e Windows.
- Classe principal `AcademicoServiceApplication` com `@SpringBootApplication`.
- `application.properties` contém somente `spring.application.name=academico-service`.
- Starters: `spring-boot-starter-webmvc`, `spring-boot-starter-data-jpa`, `spring-boot-starter-validation`, `spring-boot-starter-flyway` e `spring-boot-starter-actuator`.
- Complementos: `flyway-database-postgresql`, driver `postgresql` em runtime e `lombok` opcional, configurado como annotation processor.
- Testes: `spring-boot-starter-test` com escopo test. Versões das dependências gerenciadas pelo parent Spring Boot.
- Plugins Spring Boot e Maven Compiler presentes. README próprio, regras de exclusão Git e atributos dos scripts presentes.
- Não há entidades, controllers, services, repositories, DTOs, mappers, endpoints de negócio ou pacotes vazios representando camadas. A estrutura respeita a criação sob demanda prevista na ADR-004; as camadas de negócio ainda não foram implementadas.
- Não há migrações SQL, configuração de banco/credenciais, Kafka, Spring Security, MapStruct ou Testcontainers. A presença de JPA e Flyway no POM não comprova persistência funcional.

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

### Pendências e limitações identificadas

- Investigar avisos Maven HTTP 401 ao consultar metadados dos repositórios `flyway-community-db-support` e `MavenFeed`. Não impediram o build; a origem da configuração desses repositórios não foi determinada nesta auditoria.
- Avaliar os avisos de autoanexação do Mockito/Byte Buddy e carregamento dinâmico de agente no JDK utilizado. Não houve falha de teste.
- Validar futuramente a inicialização normal com PostgreSQL, migrações e testes de integração. Não existem testes de negócio ou integração nesta etapa.
- Atualizar futuramente o estado descrito no README da raiz: ainda informa planejamento arquitetural, embora a estrutura inicial do serviço já exista. O README não foi modificado nesta tarefa.
- Revisar e versionar as alterações apenas mediante autorização. Já havia alterações em `AGENTS.md` e `PROGRESSO.md`, além de `services/` não rastreado, no início desta auditoria; foram preservadas.

## 7. Próximas atividades

### Fechamento da etapa atual

- [ ] Realizar revisão final e autorizar o commit/push da estrutura inicial, se desejado pelo desenvolvedor.
- [ ] Avaliar os avisos Maven e Mockito registrados.
- [ ] Atualizar o README da raiz em tarefa própria.

### Implementação ainda não iniciada

- [ ] Preparar PostgreSQL e Docker Compose com bancos lógicos e credenciais separados, conforme ADR-002.
- [ ] Configurar a execução com banco e criar as primeiras migrações Flyway conforme a modelagem necessária.
- [ ] Definir e implementar a primeira funcionalidade acadêmica com testes automatizados, respeitando os requisitos e a ADR-004.
- [ ] Criar o `identidade-service` e implementar contas, autenticação e autorização.
- [ ] Detalhar os pontos pendentes da ADR-003 e implementar Kafka, Transactional Outbox, idempotência e tratamento de falhas.
- [ ] Implementar as demais funcionalidades V1: cadastros, cursos, turmas, matrículas, auditoria, consultas e relatórios, conforme detalhamento dos requisitos.
- [ ] Preparar a implantação com Kubernetes e detalhar a hospedagem do PostgreSQL.

Nenhum requisito funcional RF-001 a RF-035 possui implementação de negócio evidenciada no código atual. O evento `CoordenadorCadastrado`, os bancos independentes e a integração HTTP/Kafka estão documentados, mas não implementados. V2 e V3 permanecem planejadas, fora da implementação inicial.

A ordem das atividades poderá ser ajustada conforme as dependências técnicas; não há evidência de implementação dessas próximas atividades em andamento.

## 8. Diretrizes de acompanhamento

- Atualizar este arquivo ao concluir etapas relevantes.
- Registrar o estado real da implementação, sem marcar tarefas planejadas como concluídas.
- Manter apenas uma atividade principal em andamento.
- Revisar código e testes antes de realizar commits.
- Não permitir que o Codex execute commits ou pushes sem autorização explícita.
- Priorizar práticas consolidadas no mercado corporativo Java/Spring Boot.
