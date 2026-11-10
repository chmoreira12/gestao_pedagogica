# ADR-001 — Separação dos microsserviços Acadêmico e Identidade

**Status:** Aprovada  
**Data:** 10/11/2026

## Contexto

O Sistema de Gestão Pedagógica precisa gerenciar informações acadêmicas e controlar o acesso de usuários.

Essas responsabilidades possuem características diferentes.

A gestão acadêmica envolve cursos, turmas, alunos, professores, coordenadores e matrículas.

A gestão de identidade envolve autenticação, credenciais, perfis de acesso, ativação e recuperação de contas.

## Decisão

Separar essas responsabilidades em dois microsserviços independentes:

### academico-service

Responsável por:

- Gerenciamento de cursos e turmas.
- Cadastro de alunos, professores, coordenadores e diretores.
- Controle de matrículas.
- Aplicação das regras de negócio acadêmicas.
- Consultas acadêmicas.

### identidade-service

Responsável por:

- Gerenciamento de contas de usuários.
- Autenticação e autorização.
- Gerenciamento de credenciais.
- Ativação e recuperação de contas.
- Emissão de tokens de acesso.

Cada microsserviço terá seu próprio projeto Spring Boot, persistência e ciclo de implantação.

A comunicação entre os serviços utilizará HTTP para operações síncronas e Apache Kafka para eventos assíncronos, conforme os contratos de integração que serão definidos.

## Justificativas

- Separação clara de responsabilidades de negócio.
- Isolamento de credenciais e informações sensíveis.
- Independência de desenvolvimento e implantação.
- Possibilidade de evolução individual dos serviços.
- Aplicação prática de arquitetura distribuída e comunicação orientada a eventos.

## Consequências

### Benefícios

- Maior autonomia entre os serviços.
- Responsabilidades e limites de dados mais claros.
- Possibilidade de escalabilidade independente.
- Melhor isolamento das funcionalidades de autenticação.

### Desafios

- Necessidade de comunicação entre serviços.
- Tratamento de falhas de integração.
- Consistência de dados distribuídos.
- Gerenciamento de contratos e eventos.
- Maior complexidade operacional.

## Observações

A separação entre os serviços está aprovada.

Os contratos HTTP, os eventos Kafka, a estratégia de persistência e os detalhes de implantação serão definidos nas próximas etapas da arquitetura.