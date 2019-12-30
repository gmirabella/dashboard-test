CREATE DATABASE empatica;

\connect empatica;

CREATE EXTENSION btree_gist;

CREATE ROLE empatica_rw LOGIN PASSWORD 'test';

GRANT connect on database "empatica" to empatica_rw;