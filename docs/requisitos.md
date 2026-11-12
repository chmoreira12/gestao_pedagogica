# Requisitos do Sistema de Gestão Pedagógica

**Versão do documento:** 1.0  
**Data:** 10/11/2026  
**Situação:** Requisitos funcionais iniciais aprovados

## 1. Visão geral

O Sistema de Gestão Pedagógica tem como objetivo centralizar a administração de cursos, turmas, alunos, professores, coordenadores e matrículas de uma instituição de ensino.

O sistema será desenvolvido de forma incremental, permitindo a evolução das funcionalidades sem comprometer os registros acadêmicos existentes.

## 2. Escopo das versões

### V1 — Gestão administrativa

A primeira versão contemplará:

- Cadastro e gerenciamento de cursos.
- Cadastro e gerenciamento de turmas.
- Cadastro de alunos, professores, coordenadores e diretores.
- Controle de matrículas.
- Autenticação e autorização.
- Perfis de diretor e coordenador.
- Consultas e relatórios administrativos.
- Auditoria das operações administrativas.

### V2 — Gestão docente

A segunda versão está planejada para incluir:

- Acesso dos professores ao sistema.
- Lançamento de notas.
- Registro de frequência.
- Avaliações e acompanhamento pedagógico.

### V3 — Portal do aluno

A terceira versão está planejada para incluir:

- Acesso dos alunos.
- Consulta de resultados acadêmicos.
- Consulta e emissão de certificados, conforme regras futuras.
- Acompanhamento das atividades acadêmicas.

As funcionalidades das versões futuras não fazem parte da implementação inicial.

## 3. Perfis de acesso

### RF-001 — Diretor

O diretor poderá realizar todas as operações administrativas da V1, incluindo cadastro, consulta, alteração, cancelamento, arquivamento e gerenciamento de matrículas e usuários.

Poderá também consultar os registros de auditoria.

### RF-002 — Coordenador

O coordenador terá acesso de consulta aos dados acadêmicos de toda a instituição, independentemente dos cursos sob sua responsabilidade.

Não poderá cadastrar, alterar, cancelar ou excluir registros acadêmicos.

Poderá alterar sua própria senha.

### RF-003 — Acesso de alunos e professores

Alunos e professores serão cadastrados na V1, mas não possuirão acesso autenticado ao sistema nessa versão.

## 4. Gestão de cursos

### RF-004 — Cadastro de cursos

O diretor poderá cadastrar cursos contendo:

- Identificador único.
- Nome.
- Descrição.
- Coordenador responsável.

Cada curso deverá possuir um coordenador responsável.

Um coordenador poderá ser responsável por vários cursos.

### RF-005 — Consulta e alteração de cursos

O diretor poderá consultar e alterar cursos.

O coordenador poderá consultar os cursos cadastrados.

### RF-006 — Arquivamento de cursos

O diretor poderá arquivar cursos que não possuam turmas planejadas ou em andamento.

O arquivamento preservará o histórico do curso, suas turmas e matrículas.

Cursos arquivados não poderão receber novas turmas.

O diretor poderá reativar cursos arquivados.

## 5. Gestão de turmas

### RF-007 — Cadastro de turmas

O diretor poderá cadastrar turmas vinculadas a um curso.

Cada turma possuirá:

- Identificador único.
- Nome.
- Data de início.
- Data de término.
- Curso ao qual pertence.

As datas de início e término serão obrigatórias.

A data de término não poderá ser anterior à data de início.

Turmas poderão possuir nomes repetidos, inclusive dentro de um mesmo curso.

Não haverá limite máximo de alunos por turma na V1.

### RF-008 — Situação das turmas

As situações previstas são:

- **PLANEJADA:** a data de início ainda não chegou.
- **EM_ANDAMENTO:** a data atual está entre o início e o término, inclusive.
- **CONCLUIDA:** a data de término já passou.
- **CANCELADA:** a turma foi cancelada administrativamente.

As três primeiras situações serão determinadas pelas datas da turma.

O cancelamento administrativo terá prioridade sobre a situação calculada.

### RF-009 — Alteração de turmas

O diretor poderá alterar as datas de turmas planejadas ou em andamento.

Turmas concluídas ou canceladas não poderão ter suas datas alteradas pelas operações normais do sistema.

### RF-010 — Cancelamento de turmas

O diretor poderá cancelar uma turma.

O cancelamento será definitivo e preservará o histórico.

As matrículas ativas da turma serão canceladas como consequência da operação.

### RF-011 — Exclusão de turmas cadastradas incorretamente

O diretor poderá excluir uma turma cadastrada incorretamente quando ela não possuir matrículas.

Turmas com histórico de matrículas deverão ser preservadas.

## 6. Gestão de alunos

### RF-012 — Cadastro de alunos

O diretor poderá cadastrar alunos com:

- Identificador único.
- Nome.
- CPF.
- E-mail.
- Situação de atividade.
- Data de cadastro.

CPF e e-mail serão obrigatórios e únicos entre os alunos.

O CPF deverá possuir formato e dígitos verificadores válidos.

O e-mail deverá ser único independentemente de diferenças entre letras maiúsculas e minúsculas.

A data de cadastro será registrada automaticamente.

O aluno será cadastrado inicialmente como ativo.

### RF-013 — Alteração e desativação de alunos

O diretor poderá alterar os dados cadastrais e desativar ou reativar alunos.

Alunos inativos não poderão receber novas matrículas.

A desativação de um aluno não cancelará automaticamente suas matrículas existentes.

## 7. Gestão de professores

### RF-014 — Cadastro de professores

O diretor poderá cadastrar professores com:

- Identificador único.
- Nome.
- CPF.
- E-mail.
- Data de cadastro.

CPF e e-mail serão obrigatórios e únicos entre os professores.

### RF-015 — Vínculo de professores com cursos

Um professor poderá estar vinculado a um curso por vez.

O vínculo será opcional.

Um curso poderá possuir vários professores.

O diretor poderá desvincular ou transferir um professor para outro curso.

Na V1, não haverá controle histórico de professores por turma.

## 8. Gestão de coordenadores e diretores

### RF-016 — Cadastro de coordenadores

O diretor poderá cadastrar coordenadores com identificador único, nome, CPF, e-mail e data de cadastro.

CPF e e-mail serão obrigatórios.

O CPF será único dentro do cadastro de coordenadores e armazenado como string de exatamente 11 dígitos numéricos, preservando zeros iniciais.

A API deverá aceitar CPF com ou sem máscara e normalizá-lo antes da persistência. A aplicação deverá validar os dígitos verificadores do CPF. O banco de dados deverá garantir o formato de 11 dígitos numéricos e a unicidade entre coordenadores.

O e-mail será único entre coordenadores, independentemente de diferenças entre letras maiúsculas e minúsculas. A aplicação deverá remover espaços externos e armazenar o e-mail em minúsculas. O banco de dados deverá garantir a unicidade sem diferenciar maiúsculas e minúsculas.

O cadastro de coordenador deverá permitir a criação de uma conta de acesso para esse profissional.

Os dados cadastrais publicados no evento `CoordenadorCadastrado` deverão seguir a normalização definida acima, preservando os campos e tipos do contrato da ADR-003.

Na V1, uma pessoa não poderá exercer simultaneamente os papéis de coordenador e diretor. Essa exclusividade deverá ser respeitada nos cadastros de ambos os profissionais e no provisionamento das contas de acesso.

### RF-017 — Cadastro de diretores

O sistema deverá permitir a criação segura do primeiro diretor.

Diretores poderão cadastrar outros diretores.

O cadastro de diretores deverá respeitar a exclusividade entre os papéis de diretor e coordenador definida no RF-016.

Deverá existir pelo menos um diretor ativo com acesso ao sistema.

### RF-018 — Alteração de dados cadastrais

Somente o diretor poderá alterar os dados cadastrais de diretores e coordenadores.

As alterações deverão respeitar as regras de validação e unicidade aplicáveis.

## 9. Gestão de matrículas

### RF-019 — Registro de matrículas

O diretor poderá matricular alunos em turmas planejadas ou em andamento.

O aluno deverá estar ativo.

Cada matrícula possuirá um identificador próprio, referência ao aluno, referência à turma, data de matrícula e situação.

### RF-020 — Restrição de matrícula ativa

Um aluno poderá possuir várias matrículas históricas, mas somente uma matrícula ativa por vez em todo o sistema.

### RF-021 — Situações das matrículas

As situações previstas são:

- **ATIVA:** matrícula vigente.
- **CONCLUIDA:** matrícula encerrada pela conclusão da turma.
- **CANCELADA:** matrícula cancelada administrativamente.

A conclusão de uma matrícula não representa necessariamente aprovação acadêmica.

### RF-022 — Conclusão de matrículas

Quando uma turma atingir a situação de concluída, suas matrículas ativas deverão ser consideradas concluídas automaticamente.

O histórico deverá ser preservado.

### RF-023 — Cancelamento individual

O diretor poderá cancelar uma matrícula ativa.

Uma matrícula cancelada não poderá ser reativada.

### RF-024 — Nova matrícula após cancelamento

Um aluno poderá ser matriculado novamente na mesma turma após o cancelamento de uma matrícula anterior, desde que:

- A turma esteja planejada ou em andamento.
- O aluno esteja ativo.
- O aluno não possua outra matrícula ativa.

A nova matrícula será registrada separadamente, preservando o histórico anterior.

### RF-025 — Transferência entre turmas

Na V1, não haverá uma operação específica de transferência.

Quando necessário, o diretor deverá cancelar a matrícula anterior e realizar uma nova matrícula.

## 10. Autenticação e gerenciamento de usuários

### RF-026 — Contas de acesso

As contas de acesso serão gerenciadas independentemente dos cadastros acadêmicos.

Na V1, somente diretores e coordenadores possuirão contas de usuário.

### RF-027 — Autenticação

Os usuários deverão realizar login utilizando CPF e senha.

### RF-028 — Ativação de contas

A criação de uma conta de coordenador deverá permitir o envio de um link temporário e de uso único para definição da senha inicial.

### RF-029 — Recuperação de senha

O sistema deverá permitir recuperação de senha por e-mail, mediante solicitação identificada pelo CPF.

O processo deverá utilizar mecanismos seguros e não revelar indevidamente a existência de contas.

### RF-030 — Desativação de usuários

O diretor poderá desativar e reativar contas de usuários.

Usuários inativos não poderão acessar o sistema.

O diretor não poderá desativar sua própria conta nem o último diretor ativo.

### RF-031 — Alteração de senha

Diretores e coordenadores poderão alterar suas próprias senhas.

## 11. Auditoria

### RF-032 — Registro de operações administrativas

O sistema deverá registrar operações administrativas relevantes, incluindo:

- Usuário responsável.
- Tipo de operação.
- Recurso afetado.
- Identificador do recurso.
- Data e hora.
- Informações pertinentes às alterações realizadas.

Dados sensíveis, como senhas e tokens, não deverão ser registrados na auditoria.

### RF-033 — Consulta de auditoria

Somente diretores poderão consultar os registros de auditoria.

Os registros não poderão ser alterados ou excluídos pelas operações normais do sistema.

Na V1, não haverá exclusão automática por prazo. Uma política de retenção e proteção de dados deverá ser estabelecida antes de uma implantação institucional real.

## 12. Consultas e relatórios

### RF-034 — Consultas acadêmicas

Diretores e coordenadores poderão consultar informações sobre:

- Cursos.
- Turmas.
- Alunos.
- Professores.
- Coordenadores.
- Matrículas.

As consultas deverão respeitar os perfis de acesso.

### RF-035 — Relatórios administrativos

O sistema deverá disponibilizar relatórios administrativos baseados nos dados acadêmicos.

O conjunto específico de indicadores e formatos será definido durante o detalhamento dessa funcionalidade.

## 13. Restrições e limites da V1

A primeira versão não contemplará:

- Login de professores e alunos.
- Lançamento de notas.
- Controle de frequência.
- Emissão de certificados.
- Controle de disciplinas.
- Controle de vagas por turma.
- Histórico de atuação docente por turma.
- Transferência de matrícula em uma única operação.
- Portal web ou aplicativo frontend.

## 14. Observações de implementação

Este documento descreve requisitos funcionais, não decisões técnicas.

As estratégias de persistência, comunicação entre microsserviços, autenticação, processamento assíncrono e infraestrutura deverão seguir a documentação arquitetural.

Os requisitos ainda não detalhados, como indicadores específicos de relatórios, deverão ser refinados quando sua implementação for planejada, sem introduzir funcionalidades por suposição.
