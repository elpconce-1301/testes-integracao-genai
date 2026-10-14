# Prompt 2 — Chain-of-Thought (CoT) para explorar cenários de falha

> Pense passo a passo sobre todos os cenários de falha possíveis na integração entre o Serviço de Autenticação e o Serviço de Catálogo de Produtos.
>
> 1. Identifique todas as regras de validação do token JWT.
> 2. Para cada regra, gere um caso de teste que viole **somente aquela regra**.
> 3. Considere casos de borda, como:
>    - Token com assinatura incorreta
>    - Token expirado por poucos segundos
>    - Token sem claims obrigatórias
>    - Token emitido para outro público (audience)
> 4. Para cada cenário, descreva:
>    - Pré-condições
>    - Requisição HTTP
>    - Header enviado
>    - Status HTTP esperado
>    - Mensagem de erro esperada
>
> Gere a resposta como uma lista de casos de teste prontos para implementação em REST Assured, sem comentários adicionais.
