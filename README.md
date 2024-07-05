# Simplify Todos

Este repositório foi criado para "responder" um teste técnico da empresa Simplify, onde era necessário criar uma "
famosa" lista de tarefas.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.3
- MySQL
- Docker e Docker Compose
- Swagger
- TestContainers

## Getting Started

Para utilizar esse projeto, é necessário possuir o Docker instalado.

1. Clone este repositório e navegue com o terminal até a raiz do diretório.
2. Rode o comando "docker image build . -t simplify:1.0".
3. Após isso rode o comando "docker compose up -d".

Para visualizar todos os endpoints, basta entrar no endpoint "http://localhost:8080/swagger-ui.html"