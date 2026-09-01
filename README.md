![Java 25](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot 4](https://img.shields.io/badge/Spring_Boot-4.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

# 📝 REST API Web Services com Spring Boot & MongoDB

API RESTful desenvolvida com **Spring Boot 4** e **Spring Data MongoDB** para gerenciamento de um domínio de blog/rede social (Usuários, Posts e Comentários), explorando documentos aninhados (embedded) e referências (`@DBRef`) próprias de bancos NoSQL orientados a documentos.

O projeto conta com arquitetura em camadas bem definida, tratamento global de exceções, carga inicial de dados via `CommandLineRunner` e infraestrutura de banco de dados isolada via **Docker**.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 25
- **Framework:** Spring Boot 4
- **Persistência & ODM:** Spring Data MongoDB
- **Banco de Dados:** MongoDB (via Docker)
- **Containerização:** Docker & Docker Compose
- **Gerenciador de Dependências:** Maven

---

## 🏛️ Arquitetura e Estrutura do Projeto

O projeto segue o padrão de **Arquitetura em Camadas (Layered Architecture)**, separando responsabilidades de forma clara:

```text
├── resources --> Exposição das rotas e endpoints REST
├── services  --> Regras de negócio e tratamento de exceções
├── repository --> Camada de acesso a dados (Spring Data MongoDB)
├── domain    --> Modelo de domínio (documentos MongoDB)
├── dto       --> Objetos de transferência (Author, Comments, User)
└── config    --> Configurações e carga de dados de teste (Seeding)
```

### 🔗 Modelagem de Dados (NoSQL)

Diferente de um modelo relacional, o domínio foi desenhado aproveitando os recursos de documento do MongoDB:

- **`User`** referencia seus `Post`s via `@DBRef(lazy = true)` — mantém os posts como documentos próprios na coleção, carregados sob demanda.
- **`Post`** embute o autor (`AuthorDTO`) e a lista de comentários (`CommentsDTO`) diretamente no documento, evitando *joins* e otimizando a leitura.
- **`CommentsDTO`** também referencia seu autor via `AuthorDTO`, reaproveitando o mesmo objeto de valor.

---

## 📌 Endpoints da API

| Recurso | Método | Endpoint | Descrição | Status HTTP |
| :--- | :---: | :--- | :--- | :---: |
| **Usuários** | `GET` | `/users` | Lista todos os usuários | `200 OK` |
| | `GET` | `/users/{id}` | Busca usuário por ID | `200 OK` / `404` |
| | `GET` | `/users/{id}/posts` | Lista os posts de um usuário | `200 OK` / `404` |
| | `POST` | `/users` | Cadastra um novo usuário | `201 Created` |
| | `PUT` | `/users/{id}` | Atualiza dados do usuário | `200 OK` / `404` |
| | `DELETE` | `/users/{id}` | Remove usuário por ID | `204 No Content` |
| **Posts** | `GET` | `/posts/{id}` | Busca post por ID | `200 OK` / `404` |
| | `GET` | `/posts/titlesearch?text=` | Busca posts pelo título (regex, case-insensitive) | `200 OK` |
| | `GET` | `/posts/fullsearch?text=&minDate=&maxDate=` | Busca full-text em título, corpo e comentários, com filtro de data | `200 OK` |

---

## 🔍 Queries Customizadas (MongoDB Query Language)

As buscas mais complexas foram implementadas diretamente com a anotação `@Query`, usando os operadores nativos do Mongo:

- **`titlesearch`**: aplica `$regex` com opção `i` (case-insensitive) sobre o campo `title`.
- **`fullsearch`**: combina `$and`/`$or` para filtrar por intervalo de datas (`$gte`/`$lte`) e, simultaneamente, buscar o termo em `title`, `body` **ou** dentro dos comentários (`comments.text`).

---

## 🚨 Tratamento Global de Exceções

A API utiliza o padrão `@ControllerAdvice` (`ResourceExceptionHandler`) para interceptar erros da aplicação e retornar respostas HTTP limpas e padronizadas no objeto `StandardError`:

- **`ObjectNotFoundException` (`404 Not Found`)**: Disparada ao buscar um ID (de usuário ou post) inexistente.

### Exemplo de Resposta de Erro (Payload):
```json
{
  "timestamp": 1735689600000,
  "status": 404,
  "error": "Não encontrado",
  "message": "Objeto não encontrado",
  "path": "/users/99"
}
```

---

## 🌱 Carga Inicial de Dados (Seeding)

Ao subir a aplicação, a classe `Instantiation` (`CommandLineRunner`) limpa as coleções e popula o banco com usuários, posts e comentários de exemplo, já demonstrando os relacionamentos embutidos/referenciados descritos acima.

---

## 🐳 Como Executar

```bash
# Subir o MongoDB via Docker
docker compose up -d

# Rodar a aplicação
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.
