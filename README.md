# ServiceFlow API 🛠️

O **ServiceFlow** é uma API REST robusta desenvolvida para o gerenciamento de técnicos e fluxos de serviços. Este projeto demonstra a implementação de um ecossistema backend moderno, focado em alta disponibilidade e persistência de dados real.

## 🏗️ Estrutura do Projeto

A aplicação segue o padrão de arquitetura em camadas para garantir a separação de responsabilidades:

* **Camada de Entidade (`entity`)**: Mapeamento Objeto-Relacional (ORM) utilizando JPA/Hibernate para traduzir as classes Java em tabelas no PostgreSQL.
* **Camada de Repositório (`repository`)**: Interface que estende o `JpaRepository`, permitindo operações de CRUD no banco de dados sem a necessidade de escrever SQL manualmente.
* **Camada de Controle (`controller`)**: Endpoints REST que gerenciam as requisições HTTP, utilizando anotações como `@RestController` e `@RequestMapping`.
* **Banco de Dados**: Persistência real em ambiente **PostgreSQL**, garantindo que os dados não sejam perdidos ao reiniciar a aplicação.

## 🛠️ Tecnologias e Ferramentas

* **Java 17+** & **Spring Boot 3**
* **Spring Data JPA** & **Hibernate**
* **PostgreSQL** (Driver `org.postgresql`)
* **Lombok**: Utilizado para produtividade (embora os métodos acessores tenham sido explicitados para garantir compatibilidade com o ambiente).
* **SpringDoc OpenAPI**: Documentação interativa com **Swagger**.
* **Maven**: Automação de build e gerenciamento de dependências.

## 📋 Funcionalidades Implementadas

- [x] **Cadastro de Técnicos (POST)**: Registro de novos profissionais com validação de dados.
- [x] **Identidade Automática**: IDs gerados via `GenerationType.IDENTITY` no banco de dados.
- [x] **Documentação Automática**: Interface Swagger configurada.

---

## 🧪 Como Testar a Aplicação

Você pode validar o funcionamento da API de duas formas principais:

### 1. Via Swagger (Interface Gráfica)
1. Com a aplicação rodando, acesse: `http://localhost:8080/swagger-ui/index.html`
2. Localize o endpoint **POST** `/tecnicos`.
3. Clique em **Try it out**.
4. Insira o JSON de teste:
   ```json
   {
     "nome": "Layon Silveira",
     "matricula": "SFW2026",
     "especialidade": "Java Backend Developer",
     "ativo": true
   }

