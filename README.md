# 👤 Microserviço — User API (Autenticação & Autorização)

Este microserviço é responsável pela gestão de usuários do sistema, incluindo:
- Registro de novos usuários
- Login com autenticação JWT
- Validação de e-mail via código de verificação
- Envio de mensagens assíncronas através de **RabbitMQ**

O fluxo de autenticação é totalmente seguro utilizando **Spring Security** e **token JWT**.

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Security + JWT
- Spring Data JPA
- Flyway
- RabbitMQ
- Maven
- MySQL/PostgreSQL

---

## 🔐 Funcionalidades

✔ Cadastro de usuários  
✔ Login com geração de token JWT  
✔ Confirmação de e-mail com código  
✔ Segurança baseada em Roles (Admin / User)  
✔ Comunicação assíncrona via RabbitMQ  
✔ Persistência de tokens e status de verificação  

---

## 🔄 Fluxo de Cadastro e Verificação

Usuário → (POST /auth/registrar)
↓
RabbitMQ envia código → EmailService
↓
Usuário envia código → (POST /auth/verification)
↓
Conta ativada → Login permitido

---

## 📌 Endpoints

### 🔸 Autenticação

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/auth/registrar` | Criar usuário e enviar código |
| POST | `/auth/login` | Autenticar e gerar JWT |
| POST | `/auth/verification` | Verificar código enviado ao e-mail |

### 🔸 Usuários

| Método | Endpoint | Proteção | Descrição |
|--------|----------|---------|-----------|
| GET | `/users/me` | JWT | Buscar dados do usuário logado |
| GET | `/users` | Admin only | Listar usuários |

---

## 🧩 Segurança - JWT

O token retorna no login e deve ser enviado em requisições seguras via:

```http
Authorization: Bearer <seu_token_aqui>
📨 RabbitMQ
Fila utilizada para envio do código de validação:


🔒 Roles definidas
ROLE_USER

ROLE_ADMIN
