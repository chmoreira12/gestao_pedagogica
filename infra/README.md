# PostgreSQL local

Infraestrutura prevista na ADR-002, com uma instância `postgres:18.6-trixie`, dois bancos lógicos e credenciais separadas. Requer Docker Engine e Docker Compose.

## Configuração e execução

Na pasta `infra`, copie o exemplo e preencha as três senhas locais distintas antes de iniciar.

```bash
cp .env.example .env
```

O arquivo `.env` é ignorado pelo `.gitignore` da raiz. As variáveis obrigatórias estão descritas em `.env.example`. O Compose rejeita valores vazios.

Para validar a configuração sem exibir senhas, execute na pasta `infra`.

```bash
docker compose config --quiet
```

Após autorização do desenvolvedor para criar containers, execute nessa pasta.

```bash
docker compose up -d --wait postgres
```

O PostgreSQL fica disponível em `127.0.0.1:5434`. O projeto Compose `gestao-pedagogica-local` cria seus próprios recursos, incluindo o volume nomeado `gestao-pedagogica-local_postgres_data`. A montagem em `/var/lib/postgresql` segue a organização da imagem oficial do PostgreSQL 18, com dados em `/var/lib/postgresql/18/docker`.

| Banco | Usuário proprietário | Variável da senha |
| --- | --- | --- |
| `academico_db` | `academico_user` | `ACADEMICO_DB_PASSWORD` |
| `identidade_db` | `identidade_user` | `IDENTIDADE_DB_PASSWORD` |
| `postgres` (administração) | `postgres` | `POSTGRES_PASSWORD` |

## Inicialização e persistência

Em um volume novo, o entrypoint oficial inicializa o cluster e executa `postgres/init/01-create-databases.sh`, montado em `/docker-entrypoint-initdb.d`. O script cria os dois usuários sem privilégios administrativos e os respectivos bancos. As senhas são lidas do ambiente e escapadas como literais pelo `psql`. Erros SQL interrompem a inicialização.

O script revoga os privilégios concedidos a `PUBLIC` nos dois bancos, permitindo que cada usuário de serviço acesse seu próprio banco. O usuário administrativo mantém acesso aos dois.

O healthcheck consulta a disponibilidade por TCP na porta interna 5432. O servidor temporário utilizado durante a inicialização da imagem atende por socket local, de modo que a checagem TCP aguarda o servidor definitivo.

Nas próximas inicializações, o volume preserva os dados e o entrypoint pula os scripts. Alterar senhas no `.env` depois dessa primeira inicialização exige atualizar as senhas no PostgreSQL por procedimento administrativo. Um volume já inicializado ou parcialmente inicializado não é reparado automaticamente pelo script. Cada novo volume vazio recebe a mesma estrutura de bancos e usuários.

As tabelas de negócio e suas migrações Flyway serão responsabilidade de cada microsserviço, conforme a ADR-002.

## Referências

- [Imagem oficial PostgreSQL e inicialização](https://github.com/docker-library/docs/blob/master/postgres/README.md).
- [Versões publicadas da imagem oficial](https://github.com/docker-library/official-images/blob/master/library/postgres).
