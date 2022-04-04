#!/bin/bash

set -e
set -u

echo "*********Creating user and databases"
psql -v ON_ERROR_STOP=1 --username "postgres" <<-EOSQL

      create user automotive_selection_app with login nosuperuser nocreatedb nocreaterole inherit noreplication  password 'automotive_selection_pw';

		  CREATE DATABASE automotive_selection;

      \c automotive_selection
      alter default privileges for role "automotive_selection_app" in schema public grant select, insert, update, delete on tables to "automotive_selection_app";
      alter default privileges for role "automotive_selection_app" in schema public grant select, usage on sequences to "automotive_selection_app";

EOSQL
echo "*********Completed user and databases"
