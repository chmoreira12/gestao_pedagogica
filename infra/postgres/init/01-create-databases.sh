#!/usr/bin/env bash
set -euo pipefail

# Executado pelo entrypoint oficial somente ao inicializar um volume vazio.
# O heredoc literal e as variáveis psql preservam caracteres especiais das senhas.
psql --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" --set ON_ERROR_STOP=1 <<'SQL'
\getenv academico_password ACADEMICO_DB_PASSWORD
\getenv identidade_password IDENTIDADE_DB_PASSWORD

CREATE ROLE academico_user WITH LOGIN NOSUPERUSER NOCREATEDB NOCREATEROLE NOREPLICATION PASSWORD :'academico_password';
CREATE ROLE identidade_user WITH LOGIN NOSUPERUSER NOCREATEDB NOCREATEROLE NOREPLICATION PASSWORD :'identidade_password';

CREATE DATABASE academico_db OWNER academico_user;
CREATE DATABASE identidade_db OWNER identidade_user;

REVOKE ALL ON DATABASE academico_db FROM PUBLIC;
REVOKE ALL ON DATABASE identidade_db FROM PUBLIC;
GRANT CONNECT ON DATABASE academico_db TO academico_user;
GRANT CONNECT ON DATABASE identidade_db TO identidade_user;
SQL
