# Instruções gerais para agentes de desenvolvimento

## 1. Contexto do projeto

Este repositório contém o Sistema de Gestão Pedagógica, uma aplicação desenvolvida em Java e Spring Boot, utilizando arquitetura de microsserviços.

O projeto tem como objetivos construir uma solução de gestão acadêmica e demonstrar práticas profissionais de engenharia de software, incluindo Apache Kafka, Docker, Kubernetes, segurança e testes automatizados.

## 2. Idioma e comunicação

- Toda documentação, explicação e comunicação com o desenvolvedor deve ser realizada em português brasileiro.
- Seguir as convenções de nomenclatura Java e Spring Boot.
- Utilizar nomes claros, consistentes e representativos do domínio.
- Preservar os nomes técnicos de bibliotecas, frameworks e padrões.

## 3. Arquitetura

- Adotar arquitetura de microsserviços com responsabilidades bem definidas.
- Cada microsserviço deve possuir seu próprio projeto Spring Boot, configuração, persistência e ciclo de implantação.
- Não acessar diretamente o banco de dados de outro microsserviço.
- Utilizar HTTP para operações síncronas e Apache Kafka para comunicação assíncrona orientada a eventos, conforme os contratos definidos.
- Não criar novos microsserviços sem aprovação.
- Não modificar contratos de integração sem avaliar seus impactos.
- Registrar decisões arquiteturais relevantes na documentação.

## 4. Tecnologias

Tecnologias previstas para o projeto:

- Java e Spring Boot.
- Spring Security.
- Spring Data JPA e Hibernate.
- Jakarta Bean Validation.
- Jackson.
- Lombok.
- MapStruct.
- PostgreSQL.
- Flyway.
- Apache Kafka e Spring Kafka.
- Docker e Kubernetes.
- Maven.
- JUnit 5, Mockito e Testcontainers.

Utilizar as tecnologias de acordo com as necessidades de cada serviço e as decisões arquiteturais aprovadas.

## 5. Padrões de desenvolvimento

- Priorizar código legível, coeso, testável e de fácil manutenção.
- Utilizar injeção de dependências por construtor.
- Utilizar Lombok para reduzir código repetitivo quando apropriado.
- Não utilizar `@Data` indiscriminadamente em entidades JPA.
- Utilizar DTOs para os contratos das APIs.
- Aplicar Jakarta Bean Validation na validação das entradas.
- Utilizar MapStruct quando houver necessidade de mapeamento entre DTOs e entidades.
- Tratar erros de maneira consistente, sem expor detalhes internos.
- Utilizar Flyway para versionamento do esquema do banco.
- Não armazenar credenciais, tokens ou segredos no código-fonte.
- Evitar abstrações e dependências sem necessidade concreta.

## 6. Testes e qualidade

- Implementar testes adequados às funcionalidades desenvolvidas.
- Priorizar testes unitários para regras de negócio.
- Utilizar testes de integração quando houver interação com banco de dados, mensageria ou outros componentes externos.
- Utilizar Testcontainers quando apropriado.
- Executar os testes relevantes antes de considerar uma tarefa concluída.
- Não declarar testes como aprovados sem executá-los.
- Informar explicitamente qualquer teste que não tenha sido executado.

## 7. Fluxo de trabalho

Antes de implementar:

1. Consultar a documentação relevante.
2. Inspecionar o código existente.
3. Identificar impactos em outros serviços, testes e contratos.
4. Apresentar um plano breve quando a tarefa envolver alterações significativas.

Durante a implementação:

1. Trabalhar somente no escopo solicitado.
2. Evitar refatorações não relacionadas à tarefa.
3. Preservar decisões arquiteturais aprovadas.
4. Utilizar soluções profissionais adequadas ao contexto, evitando implementações provisórias desnecessárias.

Após a implementação:

1. Executar as verificações e testes relevantes.
2. Informar os arquivos alterados.
3. Resumir as alterações realizadas.
4. Informar pendências e limitações.
5. Atualizar a documentação de progresso quando necessário.

## 8. Git e versionamento

- Não realizar commits ou pushes sem autorização explícita.
- Sugerir commits quando uma etapa funcional ou estrutural estiver concluída.
- Utilizar mensagens de commit claras e descritivas.
- Não incluir arquivos gerados, credenciais ou informações sensíveis no versionamento.
- Preservar o histórico existente.

## 9. Documentação

Consultar os seguintes arquivos quando existirem:

- `README.md`: apresentação e instruções gerais.
- `ROADMAP.md`: planejamento e entregas.
- `PROGRESSO.md`: estado atual do desenvolvimento.
- `docs/requisitos.md`: requisitos e regras de negócio.
- `docs/arquitetura.md`: arquitetura e responsabilidades dos serviços.
- `docs/decisoes/`: decisões arquiteturais.

As instruções específicas de cada microsserviço estarão em seus respectivos arquivos `AGENTS.md`.

## 10. Restrições

- Não inventar requisitos de negócio.
- Não adicionar funcionalidades fora do escopo solicitado.
- Não alterar decisões arquiteturais aprovadas sem autorização.
- Não introduzir novos frameworks ou ferramentas sem justificativa.
- Não compartilhar entidades JPA entre microsserviços.
- Não substituir eventos Kafka por chamadas HTTP sem avaliar a decisão arquitetural.
- Não afirmar que uma funcionalidade está implementada quando ainda estiver apenas planejada.

## 11. Princípio fundamental

Este projeto deve ser desenvolvido com padrões profissionais de mercado, priorizando qualidade, clareza arquitetural, confiabilidade e evolução sustentável.

O objetivo não é produzir código artificialmente simplificado para fins didáticos, mas construir uma aplicação real, compreendendo as decisões técnicas durante seu desenvolvimento.

## Acompanhamento do desenvolvimento

O arquivo `PROGRESSO.md`, localizado na raiz do repositório, é a referência para acompanhar o estado atual do projeto.

Antes de iniciar qualquer tarefa, consulte:

1. `AGENTS.md` — instruções gerais de desenvolvimento.
2. `PROGRESSO.md` — estado atual e próximas atividades.
3. `docs/requisitos.md` — requisitos e regras de negócio aplicáveis.
4. `docs/decisoes/` — decisões arquiteturais relevantes.

Durante o desenvolvimento:

- Consulte o progresso antes de propor ou implementar alterações.
- Execute somente a tarefa solicitada.
- Não considere uma atividade concluída sem verificar seus critérios de aceitação.
- Ao finalizar uma tarefa, informe quais itens do `PROGRESSO.md` precisam ser atualizados.
- Não altere o `PROGRESSO.md` automaticamente sem autorização.
- Não execute commits ou pushes sem autorização explícita.
- Preserve as decisões arquiteturais existentes e informe quando identificar conflitos ou necessidade de revisão.