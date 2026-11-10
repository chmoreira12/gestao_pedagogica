# ADR-002 — Persistência independente por microsserviço

**Status:** Aprovada  
**Data:** 10/11/2026

## Contexto

O Sistema de Gestão Pedagógica utilizará arquitetura de microsserviços, inicialmente composta pelos serviços Acadêmico e Identidade.

Para preservar a autonomia dos serviços, é necessário estabelecer limites claros de propriedade e acesso aos dados.

## Decisão

Cada microsserviço possuirá seu próprio banco de dados lógico PostgreSQL.

- `academico-service`: banco `academico_db`.
- `identidade-service`: banco `identidade_db`.

Os serviços não poderão acessar diretamente as tabelas ou estruturas de persistência uns dos outros.

Cada serviço será responsável pelas migrações de seu banco utilizando Flyway.

No desenvolvimento local, será utilizada uma instância PostgreSQL compartilhada, executada por Docker Compose, com bancos lógicos e credenciais separados.

A aplicação também será implantada em Kubernetes. A estratégia de hospedagem do PostgreSQL nesse ambiente será definida durante o planejamento da infraestrutura.

## Justificativas

- Preservar a propriedade dos dados de cada microsserviço.
- Evitar acoplamento por meio de tabelas compartilhadas.
- Permitir evolução independente dos esquemas de banco.
- Facilitar testes e manutenção.
- Demonstrar práticas de arquitetura distribuída.

## Consequências

### Benefícios

- Isolamento lógico entre os serviços.
- Migrações de banco independentes.
- Maior autonomia de evolução.
- Separação de responsabilidades.

### Desafios

- Ausência de transações tradicionais entre bancos dos diferentes serviços.
- Necessidade de comunicação por contratos de integração.
- Tratamento de consistência eventual quando aplicável.
- Gerenciamento de configurações e credenciais de acesso.

## Observações

O uso de Kubernetes é um requisito do projeto, mas não implica que os bancos de dados precisem obrigatoriamente ser executados dentro do cluster.

A infraestrutura definitiva será documentada em uma etapa posterior.