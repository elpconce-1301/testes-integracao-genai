# testes-integracao-genai

## 📌 Cenário de Integração

Este projeto utiliza um cenário de integração entre dois serviços REST que compõem uma arquitetura simples de autenticação e consulta de catálogo de produtos.

### Serviços envolvidos

#### 1. Authentication Service

- **Endpoint:** `POST /auth/login`
- **Responsabilidade:** validar credenciais e emitir um token JWT
- **Entrada:**

```json
{
	"username": "user",
	"password": "pass"
}
```

- **Saída (sucesso):**

```json
{
	"token": "<jwt-token>"
}
```

#### 2. Product Catalog Service

- **Endpoint:** `GET /products/list`
- **Responsabilidade:** retornar a lista de produtos cadastrados
- **Requisito:** o cliente deve enviar um token JWT válido no header
- **Header esperado:** `Authorization: Bearer <jwt-token>`

## Fluxo de Integração

1. O cliente envia credenciais válidas para o serviço de autenticação.
2. O serviço retorna um token JWT.
3. O cliente utiliza o token para acessar o serviço de catálogo.
4. O serviço de catálogo valida o token junto ao serviço de autenticação.
5. Se o token for válido, retorna a lista de produtos.
6. Se o token for inválido, ausente ou expirado, retorna erro apropriado.

## Objetivo dos testes de integração

- Validar a comunicação entre os dois serviços.
- Garantir que o catálogo só seja acessado com tokens válidos.
- Verificar respostas corretas para cenários de erro:
	- token ausente
	- token expirado
	- token malformado
	- credenciais inválidas
	- token emitido para outro público (audience)