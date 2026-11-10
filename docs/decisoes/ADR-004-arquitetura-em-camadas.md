# ADR-004 — Arquitetura interna em camadas

**Status:** Aprovada
**Data:** 10/11/2026

## Contexto

O Sistema de Gestão Pedagógica será desenvolvido com Java e Spring Boot, utilizando microsserviços.

É necessário definir uma organização interna de código que favoreça a manutenção, a legibilidade, os testes e a familiaridade com práticas de desenvolvimento corporativo.

O projeto também possui o objetivo de demonstrar competências aplicáveis ao cotidiano de equipes Java/Spring Boot.

## Decisão

Adotar arquitetura tradicional em camadas (*Layered Architecture*), com organização de pacotes por responsabilidade técnica (*package by layer*).

Cada microsserviço terá sua própria estrutura interna, respeitando seus limites de responsabilidade e persistência.

As principais camadas serão:

- `controller`: exposição dos endpoints REST.
- `service`: implementação das regras de negócio e coordenação das operações.
- `repository`: acesso aos dados com Spring Data JPA.
- `entity`: mapeamento das entidades JPA.
- `dto`: contratos de entrada e saída.
- `mapper`: conversões entre entidades e DTOs.
- `exception`: exceções e tratamento centralizado.
- `config`: configurações da aplicação.
- `security`: componentes relacionados à segurança.
- `messaging`: integração com sistemas de mensageria.

Os pacotes serão criados conforme a necessidade real da implementação.

## Diretrizes

1. Controllers não deverão implementar regras de negócio.
2. Controllers não deverão acessar diretamente os repositories.
3. Services serão responsáveis pelas regras de negócio e pela coordenação das operações.
4. Repositories serão responsáveis pelo acesso à persistência.
5. Entidades JPA não serão utilizadas diretamente como contratos públicos da API.
6. DTOs serão utilizados para definir os contratos de entrada e saída.
7. MapStruct será utilizado para os mapeamentos apropriados.
8. A integração com Kafka deverá respeitar as responsabilidades das camadas.
9. A aplicação deverá priorizar código legível, testável e de fácil manutenção.
10. Abstrações adicionais deverão possuir justificativa técnica concreta.

## Justificativa

A arquitetura em camadas é uma abordagem consolidada no desenvolvimento corporativo com Java e Spring Boot.

Sua adoção facilita a compreensão do código, a separação de responsabilidades e a manutenção por equipes familiarizadas com esse modelo.

A decisão também está alinhada ao objetivo profissional do projeto: demonstrar competências diretamente aplicáveis a sistemas corporativos.

## Consequências

### Benefícios

- Organização familiar ao ecossistema Java/Spring Boot.
- Separação clara de responsabilidades.
- Facilidade de manutenção e testes.
- Menor complexidade arquitetural inicial.

### Limitações

- Funcionalidades podem ficar distribuídas entre diferentes pacotes.
- O crescimento da aplicação pode exigir maior disciplina para evitar acoplamento entre componentes.

## Alternativas consideradas

### Package by feature

Organização por funcionalidade de negócio.

É uma alternativa válida e profissional, mas não foi selecionada para este projeto por priorizarmos uma estrutura tradicional e familiar a muitos ambientes corporativos Java.

### Arquitetura Hexagonal ou Clean Architecture

Oferecem mecanismos adicionais de isolamento e inversão de dependências.

Não serão adotadas integralmente neste momento, pois acrescentariam complexidade sem uma necessidade concreta identificada.

Seus princípios poderão ser aplicados pontualmente quando houver justificativa técnica.
