# ADR-003 — Integração entre Acadêmico e Identidade com Apache Kafka

**Status:** Aprovada  
**Data:** 10/11/2026

## 1. Contexto

O Sistema de Gestão Pedagógica possui dois microsserviços inicialmente definidos:

- `academico-service`: responsável pelos dados acadêmicos e cadastrais.
- `identidade-service`: responsável pelas contas de acesso, autenticação e autorização.

Quando um diretor cadastra um coordenador, o sistema precisa registrar seus dados acadêmicos e disponibilizar uma conta para acesso.

Essas operações pertencem a serviços diferentes.

## 2. Decisão

Utilizar Apache Kafka para comunicar o cadastro de coordenadores entre os microsserviços.

O `academico-service` publicará o evento `CoordenadorCadastrado`.

O `identidade-service` consumirá esse evento e iniciará o provisionamento de uma conta pendente de ativação.

A comunicação será assíncrona, sem exigir que o cadastro acadêmico aguarde a criação da conta.

## 3. Contrato inicial do evento

```json
{
  "eventId": "550e8400-e29b-41d4-a716-446655440000",
  "eventType": "CoordenadorCadastrado",
  "eventVersion": 1,
  "occurredAt": "2026-11-10T17:00:00Z",
  "payload": {
    "coordenadorId": "550e8400-e29b-41d4-a716-446655440001",
    "nome": "Maria Silva",
    "cpf": "12345678909",
    "email": "maria@exemplo.com"
  }
}
```

Os valores são fictícios e servem apenas como exemplo.

## 4. Responsabilidades

### academico-service

- Validar e cadastrar o coordenador.
- Persistir os dados acadêmicos.
- Registrar o evento para publicação confiável.
- Publicar o evento no Kafka.

### identidade-service

- Consumir o evento.
- Identificar mensagens já processadas.
- Validar as condições necessárias para criar a conta.
- Criar a conta pendente de ativação.
- Manter referência ao identificador do coordenador.
- Iniciar o processo de ativação da conta.

## 5. Propriedade dos dados

O serviço acadêmico será a fonte de referência dos dados cadastrais do coordenador.

O serviço de identidade será responsável pelos dados de autenticação e autorização.

Os serviços não compartilharão tabelas nem utilizarão chaves estrangeiras entre seus bancos.

Os dados cadastrais necessários à autenticação poderão ser replicados no serviço de identidade por meio de eventos.

Alterações posteriores de CPF e e-mail deverão ser sincronizadas por contratos de integração específicos.

## 6. Confiabilidade

A implementação deverá considerar:

- Publicação confiável de eventos, utilizando o padrão Transactional Outbox.
- Consumidores idempotentes para evitar processamento duplicado.
- Tratamento de falhas transitórias.
- Estratégia de retentativas.
- Tratamento de mensagens que não possam ser processadas.
- Observabilidade do processamento.
- Proteção de dados pessoais transmitidos nos eventos.

A operação de cadastro acadêmico não dependerá da disponibilidade imediata do serviço de identidade.

## 7. Consistência eventual

O coordenador poderá estar cadastrado no serviço acadêmico antes que sua conta esteja disponível no serviço de identidade.

Falhas no provisionamento deverão ser identificáveis e recuperáveis.

O processamento repetido de um evento não deverá criar contas duplicadas.

## 8. Consequências

### Benefícios

- Desacoplamento temporal entre os serviços.
- Integração orientada a eventos.
- Evolução independente dos consumidores.
- Aplicação prática de Apache Kafka e Spring Kafka.

### Desafios

- Consistência eventual.
- Tratamento de mensagens duplicadas.
- Recuperação de falhas.
- Sincronização de dados cadastrais.
- Segurança e proteção de informações pessoais.

## 9. Pontos pendentes de detalhamento

Antes da implementação da integração, deverão ser definidos:

- Nome e configuração do tópico Kafka.
- Chave de particionamento.
- Estratégia de serialização.
- Contratos para atualização cadastral.
- Tratamento de conflitos de CPF e e-mail.
- Política de retentativas e mensagens com falha.
- Mecanismo de envio dos e-mails de ativação.

Esses detalhes não alteram a decisão de utilizar Kafka para a integração entre os serviços.