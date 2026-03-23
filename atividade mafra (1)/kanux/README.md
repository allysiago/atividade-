# 🌱 Kanux - Projeto Spring Boot (Aula ADS Backend Frameworks)

Projeto completo com **Hello World**, **CRUD de Produtos** e **Autenticação JWT**.

---

## 📋 Pré-requisitos

- **Java 17+** → https://adoptium.net
- **Maven 3.8+** → https://maven.apache.org
- **VS Code** → https://code.visualstudio.com

---

## 🔌 Extensões necessárias no VS Code

Instale o pacote **"Extension Pack for Java"** da Microsoft:

1. Abra o VS Code
2. Pressione `Ctrl+Shift+X`
3. Busque por: `Extension Pack for Java`
4. Instale (instala automaticamente: Language Support, Debugger, Maven, Test Runner)

Também instale:
- **Spring Boot Extension Pack** (pesquise no marketplace)

---

## ▶️ Como rodar no VS Code

### Opção 1 — Pelo terminal integrado
```bash
# Abra o terminal (Ctrl + `)
cd kanux
./mvnw spring-boot:run       # Linux/Mac
mvnw.cmd spring-boot:run     # Windows
```

### Opção 2 — Botão Run
- Abra `KanuxApplication.java`
- Clique em **Run** (aparece acima do método `main`)

### Opção 3 — Spring Boot Dashboard
- Na barra lateral esquerda procure o ícone do Spring Boot
- Clique em ▶️ ao lado do projeto

---

## 🌐 Endpoints disponíveis

### 🔓 Públicos (sem autenticação)

| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `http://localhost:8080/api/hello` | Hello World |
| POST | `http://localhost:8080/api/auth/login` | Login |
| GET | `http://localhost:8080/h2-console` | Console do banco |

### 🔒 Protegidos (requerem token JWT)

| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/api/produtos` | Listar todos os produtos |
| GET | `/api/produtos/{id}` | Buscar produto por ID |
| GET | `/api/produtos/buscar?nome=x` | Buscar por nome |
| POST | `/api/produtos` | Criar novo produto |
| PUT | `/api/produtos/{id}` | Atualizar produto |
| DELETE | `/api/produtos/{id}` | Deletar produto |

---

## 🧪 Como testar com Postman

### Passo 1 — Hello World (público)
```
GET http://localhost:8080/api/hello
```
Retorna: `Hello World!`

---

### Passo 2 — Login para obter o token
```
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```
Resposta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "tipo": "Bearer",
  "usuario": "admin"
}
```

---

### Passo 3 — Usar o token nas requisições protegidas

No Postman, vá em **Authorization → Bearer Token** e cole o token.

Ou no header: `Authorization: Bearer SEU_TOKEN_AQUI`

---

### Passo 4 — Criar um produto
```
POST http://localhost:8080/api/produtos
Authorization: Bearer SEU_TOKEN
Content-Type: application/json

{
  "nome": "Notebook Dell",
  "descricao": "Notebook i7 16GB RAM",
  "preco": 4500.00,
  "quantidade": 10
}
```

### Passo 5 — Listar todos os produtos
```
GET http://localhost:8080/api/produtos
Authorization: Bearer SEU_TOKEN
```

### Passo 6 — Atualizar produto
```
PUT http://localhost:8080/api/produtos/1
Authorization: Bearer SEU_TOKEN
Content-Type: application/json

{
  "nome": "Notebook Dell Atualizado",
  "descricao": "Notebook i7 32GB RAM",
  "preco": 5000.00,
  "quantidade": 5
}
```

### Passo 7 — Deletar produto
```
DELETE http://localhost:8080/api/produtos/1
Authorization: Bearer SEU_TOKEN
```

---

## 🗄️ Console H2 (banco de dados)

Acesse: http://localhost:8080/h2-console

- **JDBC URL:** `jdbc:h2:mem:kanuxdb`
- **User:** `sa`
- **Password:** *(deixe em branco)*

---

## 🏗️ Estrutura do Projeto

```
kanux/
├── pom.xml
└── src/main/java/com/kanux/
    ├── KanuxApplication.java          ← Classe principal (@SpringBootApplication)
    ├── config/
    │   ├── SecurityConfig.java        ← Configuração de segurança + CORS
    │   └── JwtAuthFilter.java         ← Filtro JWT
    ├── controller/
    │   ├── HelloController.java       ← GET /api/hello
    │   ├── AuthController.java        ← POST /api/auth/login
    │   └── ProductController.java     ← CRUD /api/produtos
    ├── model/
    │   └── Product.java               ← Entidade Produto (@Entity)
    ├── repository/
    │   └── ProductRepository.java     ← JpaRepository (acesso ao banco)
    ├── service/
    │   ├── ProductService.java        ← Regras de negócio
    │   └── CustomUserDetailsService.java ← Autenticação
    └── dto/
        ├── JwtUtil.java               ← Geração/validação de tokens
        └── LoginRequest.java          ← DTO de login
```

---

## ⚠️ Bug corrigido no seu SecurityConfig.java

O arquivo original tinha o método `passwordEncoder()` duplicado fora da classe (erro de chave `}`). Isso foi corrigido neste projeto.
