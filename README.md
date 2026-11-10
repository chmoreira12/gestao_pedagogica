# Sistema de Gestão Pedagógica

Sistema de gerenciamento pedagógico desenvolvido com Java e Spring Boot, utilizando arquitetura de microsserviços, comunicação assíncrona com Apache Kafka e orquestração de contêineres com Kubernetes.

## Objetivo

Desenvolver uma aplicação para gerenciamento administrativo de uma instituição de ensino, permitindo o controle de cursos, turmas, alunos, professores, coordenadores e matrículas.

O projeto também tem como objetivo aplicar práticas profissionais de engenharia de software, arquitetura distribuída, segurança, testes automatizados e infraestrutura.

## Tecnologias previstas

- Java e Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Apache Kafka
- Docker
- Kubernetes
- Flyway
- Lombok
- MapStruct
- JUnit 5, Mockito e Testcontainers
- Maven e Git

## Arquitetura

O sistema será desenvolvido utilizando microsserviços com responsabilidades independentes.

**Microsserviços inicialmente definidos:**

- **Serviço Acadêmico:** responsável pelo gerenciamento de cursos, turmas, matrículas e cadastros acadêmicos.
- **Serviço de Identidade:** responsável pela autenticação, autorização e gerenciamento de contas de usuários.

A integração entre os serviços utilizará APIs HTTP para operações síncronas e Apache Kafka para comunicação assíncrona orientada a eventos, conforme as necessidades do domínio.

Cada microsserviço será responsável pelos próprios dados.

## Funcionalidades previstas para a V1

- Gerenciamento de cursos e turmas.
- Cadastro de alunos, professores e coordenadores.
- Controle de matrículas.
- Autenticação e autorização.
- Perfis de diretor e coordenador.
- Auditoria de operações administrativas.
- Consultas e relatórios acadêmicos.

## Evolução do projeto

O desenvolvimento será realizado de forma incremental, com documentação das decisões arquiteturais, testes automatizados e versionamento contínuo.

## Estado atual

**Em planejamento arquitetural.**

O repositório está em fase inicial de estruturação. As tecnologias e funcionalidades descritas representam o escopo planejado e serão implementadas progressivamente.