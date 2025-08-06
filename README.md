# RestAPIFurb - Sistema de Comandas

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── furb/
│   │           └── restapifurb/
│   │               ├── RestApiFurbApplication.java
│   │               ├── config/
│   │               │   ├── DataLoader.java
│   │               │   ├── SecurityConfig.java
│   │               │   └── SwaggerConfig.java
│   │               ├── controller/
│   │               │   ├── AuthController.java
│   │               │   └── ComandaController.java
│   │               ├── repository/
│   │               │   ├── ComandaRepository.java
│   │               │   ├── ProdutoRepository.java
│   │               │   └── UserRepository.java
│   │               ├── dto/
│   │               │   ├── ComandaResponseDTO.java
│   │               │   ├── ComandaSimpleResponseDTO.java
│   │               │   ├── ComandaUpdateDTO.java
│   │               │   ├── ErrorResponse.java
│   │               │   ├── LoginRequestDTO.java
│   │               │   └── LoginResponseDTO.java
│   │               ├── exception/
│   │               │   ├── GlobalExceptionHandler.java
│   │               │   └── ResourceNotFoundException.java
│   │               ├── model/
│   │               │   ├── Comanda.java
│   │               │   ├── Produto.java
│   │               │   └── User.java
│   │               ├── security/
│   │               │   ├── JwtAuthenticationEntryPoint.java
│   │               │   └── JwtRequestFilter.java
│   │               ├── service/
│   │               │   ├── ComandaService.java
│   │               │   └── UserService.java
│   │               └── util/
│   │                   └── JwtUtil.java
│   └── resources/
│       └── application.properties
└── pom.xml
```

## Como Executar o Projeto

### Pré-requisitos

- Java 17+
- Maven 3.6+

### Instalação e Execução

```bash
mvn clean install
mvn spring-boot:run
```

### URLs Importantes

- **API Base**: http://localhost:8080/
- **Swagger UI**: http://localhost:8080/swagger-ui.html

## Credenciais Padrão

- **Username**: `admin`
- **Password**: `123456`

## Endpoints da API

### Autenticação (Público)

- `POST /auth/login` - Fazer login
- `POST /auth/register` - Registrar usuário

### Comandas (Requer autenticação)

- `GET /comandas` - Listar comandas
- `GET /comandas/{id}` - Buscar comanda por ID
- `POST /comandas` - Criar nova comanda
- `PUT /comandas/{id}` - Atualizar comanda
- `DELETE /comandas/{id}` - Remover comanda

## Como Testar

### 1. Fazer Login

```bash
curl -X POST http://localhost:8080/RestAPIFurb/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"123456"}'
```

### 2. Usar o Token (substitua YOUR_TOKEN)

```bash
curl -X GET http://localhost:8080/RestAPIFurb/comandas \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### 3. Criar uma Comanda

```bash
curl -X POST http://localhost:8080/RestAPIFurb/comandas \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{
    "idUsuario": 1,
    "nomeUsuario": "joao",
    "telefoneUsuario": "478888888",
    "produtos": [
      {"id": 1, "nome": "X-Salada", "preco": 30},
      {"id": 2, "nome": "X-Bacon", "preco": 35}
    ]
  }'
```

## Funcionalidades Implementadas

### ✅ 1. Web Service REST com JSON (25%)
- Endpoints conforme especificação
- Códigos de erro HTTP apropriados
- Comunicação JSON

### ✅ 2. Persistência com ORM/JPA (25%)
- Hibernate/JPA configurado
- Nomenclatura padrão (tabelas no plural, classes no singular)
- Geração automática de tabelas

### ✅ 3. Autenticação JWT (15%)
- Endpoints protegidos por token
- Sistema de autenticação completo
- Middleware de segurança

### ✅ 4. Documentação Swagger (15%)
- OpenAPI 3.0 configurado
- Interface Swagger UI disponível
- Documentação completa dos endpoints

### ✅ 5. Padrão MVC + DAO (15%)
- Controllers para requisições
- Services para lógica de negócio
- DAOs para acesso a dados
- Separação clara de responsabilidades

### ✅ 6. Validação de Atributos (5%)
- Validações Bean Validation
- Tratamento de erros de validação
- Mensagens de erro personalizadas

## Banco de Dados

O projeto usa **MySQL Database**

- **users** - Usuários do sistema
- **produtos** - Produtos disponíveis
- **comandas** - Comandas dos clientes
- **comanda_produtos** - Relacionamento Many-to-Many

## Segurança

- Senhas criptografadas com BCrypt
- Tokens JWT com expiração de 24 horas
- Endpoints protegidos por role-based security
- CORS configurado para desenvolvimento

## Observações Importantes

- O projeto está configurado para rodar na porta 8080
- Todos os endpoints de comandas requerem autenticação
- A documentação Swagger está disponível e acessível
- O banco MySQL permite acessar os dados via Workbench ou console