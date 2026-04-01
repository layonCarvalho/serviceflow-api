# ServiceFlow API 🛠️

**ServiceFlow** é uma API REST desenvolvida para o gerenciamento de fluxos de serviços técnicos. O projeto demonstra o domínio de tecnologias modernas do ecossistema Java, focando em código limpo, segurança de dados e persistência profissional.

## 🚀 Status do Projeto
Atualmente, a API conta com um **CRUD completo de Técnicos**, incluindo validações de negócio, paginação e exclusão lógica.

## 🛠️ Tecnologias e Ferramentas
* **Java 18**: Linguagem utilizada no desenvolvimento.
* **Spring Boot 4.0.3**: Framework base da aplicação.
* **Spring Data JPA & Hibernate**: Para persistência e comunicação com o banco.
* **PostgreSQL**: Banco de dados relacional (versão 14.5).
* **Lombok**: Redução de código boilerplate.
* **Bean Validation (Jakarta)**: Integridade e validação de dados.
* **SpringDoc OpenAPI (Swagger)**: Documentação e testes interativos.

## 📋 Funcionalidades Implementadas

### Gestão de Técnicos
1.  **Cadastro (`POST /tecnicos`)**: Criação de técnicos com validação de campos.
2.  **Listagem Paginada (`GET /tecnicos`)**: Retorna técnicos ativos com suporte a paginação e ordenação.
3.  **Detalhamento (`GET /tecnicos/{id}`)**: Consulta individual por ID.
4.  **Atualização (`PUT /tecnicos`)**: Alteração dinâmica de dados via DTO.
5.  **Exclusão Lógica (`DELETE /tecnicos/{id}`)**: Inativação do registro no banco (Soft Delete).



## 🧪 Guia de Testes via Swagger

Para testar as funcionalidades, inicie a aplicação e acesse: `http://localhost:8080/swagger-ui/index.html`

### 1. Cadastrar Técnico (`POST /tecnicos`)
Clique em **Try it out** e envie o JSON abaixo:
````json
{
  "nome": "Layon Silveira",
  "matricula": "SFW2026",
  "especialidade": "Java Backend Developer"
}
````
Resultado: Status 201 Created. O corpo da resposta incluirá o ID gerado e o campo "ativo": true.

### 2. Listar Técnicos (`GET /tecnicos`)
   Clique em Execute para visualizar os técnicos ativos.

Paginação: A API utiliza Pageable, permitindo filtrar por page (página) e size (quantidade por página).

Ordenação: Por padrão, a lista é ordenada por nome.

### 3. Detalhar Técnico (`GET /tecnicos/{id}`)
   Informe o ID de um técnico existente (ex: 3) no campo id.
 
Resultado: Status 200 OK com os dados detalhados do profissional.

### 4. Atualizar Dados (`PUT /tecnicos`)
   Este endpoint permite a atualização dinâmica de informações. O campo id é obrigatório.

Request Body:
````json
{
"id": 3,
"nome": "Layon Carvalho Silveira",
"especialidade": "Senior Java Developer"
}
````
Resultado: Status 200 OK. Somente os campos enviados serão alterados.

### 5. Exclusão Lógica (`DELETE /tecnicos/{id}`)
   Informe o ID do técnico que deseja desativar.

Resultado: Status 204 No Content.

Regra de Negócio: O técnico não é removido do PostgreSQL; seu status é alterado para ativo: false. Ele deixará de aparecer nas listagens automáticas do GET, mas permanecerá no banco para histórico.

## ⚠️ Testes de Validação e Erros
A API está protegida com Bean Validation para garantir a qualidade dos dados.

Simular Erro 400 (Bad Request): Tente enviar o campo nome ou matricula vazio no cadastro.

Resposta Esperada:
````json
{
"timestamp": "2026-04-01T...",
"status": 400,
"error": "Bad Request",
"path": "/tecnicos"
}