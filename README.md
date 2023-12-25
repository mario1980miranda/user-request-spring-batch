# User Request Spring Batch Application

## How to start

1. Run docker compose
2. Create database/schema and DDL:
```sql
CREATE DATABASE app;
DROP TABLE IF EXISTS tb_user;
CREATE TABLE tb_user(login VARCHAR(30), name VARCHAR(60), avatar_url VARCHAR(100), PRIMARY KEY(login));
```
3. Run application [User Request Service App](https://github.com/mario1980miranda/user-request-service.git).
4. Run this application
5. Use PhpMyAdmin console on localhost:5050 to verify
