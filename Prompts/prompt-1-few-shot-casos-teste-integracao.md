# Prompt 1 — Few-shot para geração de casos de teste de integração

> **Papel:** Você é um testador de software sênior especializado em testes de integração entre serviços REST.
>
> **Tarefa:** Gerar casos de teste de integração para um sistema composto por dois serviços:
>
> - Serviço de Autenticação (`POST /auth/login`)
> - Serviço de Catálogo de Produtos (`GET /products/list`)
>
> O Serviço de Catálogo exige um token JWT válido emitido pelo Serviço de Autenticação.
>
> **Exemplo (few-shot):**
>
> *Exemplo de caso de teste de integração:*
>
> - Passo 1: Enviar credenciais válidas para `/auth/login`
> - Passo 2: Extrair o token JWT da resposta
> - Passo 3: Enviar requisição para `/products/list` com o header `Authorization: Bearer <token>`
> - Resultado esperado: HTTP 200 e lista de produtos não vazia
>
> **Agora gere:**
>
> - Casos de teste válidos
> - Casos de teste inválidos (token ausente, expirado, malformado, credenciais inválidas etc.)
> - Inclua pré-condições, passos, payloads e resultados esperados
> - Formate a saída em **JSON estruturado**, adequado para automação com REST Assured
> - Não inclua explicações adicionais; apenas os casos de teste
