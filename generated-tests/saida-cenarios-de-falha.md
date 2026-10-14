[
  {
    "id": "JWT-001",
    "cenario": "Rejeitar acesso sem token",
    "preCondicoes": [
      "O Serviço de Catálogo está disponível."
    ],
    "requisicaoHttp": {
      "metodo": "GET",
      "endpoint": "http://localhost:8080/products/list",
      "header": null,
      "payload": null
    },
    "headerEnviado": "Nenhum header Authorization",
    "statusHttpEsperado": 401,
    "mensagemErroEsperada": "Token JWT ausente."
  },
  {
    "id": "JWT-002",
    "cenario": "Rejeitar token malformado",
    "preCondicoes": [
      "O Serviço de Catálogo está disponível.",
      "O token enviado possui uma estrutura que não corresponde ao formato JWT; nenhuma outra regra pode ser avaliada."
    ],
    "requisicaoHttp": {
      "metodo": "GET",
      "endpoint": "http://localhost:8080/products/list",
      "header": {
        "Authorization": "Bearer abc.def"
      },
      "payload": null
    },
    "headerEnviado": "Authorization: Bearer abc.def",
    "statusHttpEsperado": 403,
    "mensagemErroEsperada": "Token JWT malformado."
  },
  {
    "id": "JWT-003",
    "cenario": "Rejeitar token com assinatura inválida",
    "preCondicoes": [
      "O Serviço de Catálogo está disponível.",
      "O token tem formato válido, algoritmo permitido, claims obrigatórias corretas, issuer e audience corretos e expiração futura.",
      "O token foi assinado com uma chave diferente da chave confiável pelo serviço."
    ],
    "requisicaoHttp": {
      "metodo": "GET",
      "endpoint": "http://localhost:8080/products/list",
      "header": {
        "Authorization": "Bearer ${invalidSignatureToken}"
      },
      "payload": null
    },
    "headerEnviado": "Authorization: Bearer ${invalidSignatureToken}",
    "statusHttpEsperado": 403,
    "mensagemErroEsperada": "Assinatura do token JWT inválida."
  },
  {
    "id": "JWT-004",
    "cenario": "Rejeitar token expirado por poucos segundos",
    "preCondicoes": [
      "O Serviço de Catálogo está disponível.",
      "O token tem assinatura válida e todas as claims corretas, exceto exp.",
      "A claim exp indica um horário alguns segundos anterior ao relógio do serviço."
    ],
    "requisicaoHttp": {
      "metodo": "GET",
      "endpoint": "http://localhost:8080/products/list",
      "header": {
        "Authorization": "Bearer ${recentlyExpiredToken}"
      },
      "payload": null
    },
    "headerEnviado": "Authorization: Bearer ${recentlyExpiredToken}",
    "statusHttpEsperado": 403,
    "mensagemErroEsperada": "Token JWT expirado."
  },
  {
    "id": "JWT-005",
    "cenario": "Rejeitar token sem claim obrigatória",
    "preCondicoes": [
      "O Serviço de Catálogo está disponível.",
      "${requiredClaim} é uma claim obrigatória definida pela configuração do serviço.",
      "O token tem assinatura válida, expiração futura e demais claims obrigatórias válidas; somente ${requiredClaim} foi removida."
    ],
    "requisicaoHttp": {
      "metodo": "GET",
      "endpoint": "http://localhost:8080/products/list",
      "header": {
        "Authorization": "Bearer ${tokenMissingRequiredClaim}"
      },
      "payload": null
    },
    "headerEnviado": "Authorization: Bearer ${tokenMissingRequiredClaim}",
    "statusHttpEsperado": 403,
    "mensagemErroEsperada": "Claim obrigatória ausente: ${requiredClaim}."
  },
  {
    "id": "JWT-006",
    "cenario": "Rejeitar token com audience incorreto",
    "preCondicoes": [
      "O Serviço de Catálogo está disponível.",
      "O token tem assinatura válida, expiração futura, issuer correto e claims obrigatórias presentes.",
      "Somente a claim aud contém um público diferente do Serviço de Catálogo."
    ],
    "requisicaoHttp": {
      "metodo": "GET",
      "endpoint": "http://localhost:8080/products/list",
      "header": {
        "Authorization": "Bearer ${wrongAudienceToken}"
      },
      "payload": null
    },
    "headerEnviado": "Authorization: Bearer ${wrongAudienceToken}",
    "statusHttpEsperado": 403,
    "mensagemErroEsperada": "Audience do token JWT inválida."
  },
  {
    "id": "JWT-007",
    "cenario": "Rejeitar token com issuer incorreto",
    "preCondicoes": [
      "O Serviço de Catálogo está disponível.",
      "O token tem assinatura válida, expiração futura, audience correto e claims obrigatórias presentes.",
      "Somente a claim iss contém um emissor diferente do Serviço de Autenticação confiável."
    ],
    "requisicaoHttp": {
      "metodo": "GET",
      "endpoint": "http://localhost:8080/products/list",
      "header": {
        "Authorization": "Bearer ${wrongIssuerToken}"
      },
      "payload": null
    },
    "headerEnviado": "Authorization: Bearer ${wrongIssuerToken}",
    "statusHttpEsperado": 403,
    "mensagemErroEsperada": "Issuer do token JWT inválido."
  },
  {
    "id": "JWT-008",
    "cenario": "Rejeitar token ainda não válido",
    "preCondicoes": [
      "O Serviço de Catálogo está disponível.",
      "O token tem assinatura válida, expiração futura, issuer e audience corretos e claims obrigatórias presentes.",
      "Somente a claim nbf indica um horário posterior ao relógio do serviço."
    ],
    "requisicaoHttp": {
      "metodo": "GET",
      "endpoint": "http://localhost:8080/products/list",
      "header": {
        "Authorization": "Bearer ${notYetValidToken}"
      },
      "payload": null
    },
    "headerEnviado": "Authorization: Bearer ${notYetValidToken}",
    "statusHttpEsperado": 403,
    "mensagemErroEsperada": "Token JWT ainda não é válido."
  },
  {
    "id": "AUTH-001",
    "cenario": "Rejeitar credenciais inválidas na autenticação",
    "preCondicoes": [
      "O Serviço de Autenticação está disponível.",
      "O usuário user existe, mas a senha informada está incorreta."
    ],
    "requisicaoHttp": {
      "metodo": "POST",
      "endpoint": "http://localhost:8080/auth/login",
      "header": {
        "Content-Type": "application/json"
      },
      "payload": {
        "username": "user",
        "password": "errado"
      }
    },
    "headerEnviado": "Content-Type: application/json",
    "statusHttpEsperado": 401,
    "mensagemErroEsperada": "Credenciais inválidas."
  }
]
