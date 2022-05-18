CREATE DATABASE dashboard;

\connect dashboard;

CREATE EXTENSION btree_gist;

CREATE ROLE dashboard_rw LOGIN PASSWORD 'test';

GRANT connect on database "dashboard" to dashboard_rw;