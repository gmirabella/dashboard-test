\connect dashboard;

--ACCESS DB

GRANT CONNECT ON DATABASE dashboard TO dashboard_rw;

--ACCESS SCHEMA

GRANT USAGE  ON SCHEMA public TO dashboard_rw;

--ACCESS TABLES

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO dashboard_rw;

GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO dashboard_rw;