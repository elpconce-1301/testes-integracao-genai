{
  "baseUri": "http://localhost:8080",
  "testCases": [
    {
      "id": "TC-001",
      "name": "Acessar catálogo com credenciais e token válidos",
      "type": "positive",
      "preconditions": [
        "Os serviços de autenticação e catálogo estão disponíveis.",
        "O usuário user está cadastrado com a senha pass.",
        "O catálogo possui ao menos um produto cadastrado."
      ],
      "steps": [
        {
          "action": "POST",
          "endpoint": "/auth/login",
          "headers": {
            "Content-Type": "application/json"
          },
          "payload": {
            "username": "user",
            "password": "pass"
          },
          "expected": {
            "statusCode": 200,
            "extract": {
              "token": "$.token"
            },
            "assertions": [
              "$.token is not null"
            ]
          }
        },
        {
          "action": "GET",
          "endpoint": "/products/list",
          "headers": {
            "Authorization": "Bearer ${token}"
          },
          "expected": {
            "statusCode": 200,
            "assertions": [
              "response body is a non-empty product list"
            ]
          }
        }
      ]
    },
    {
      "id": "TC-002",
      "name": "Rejeitar credenciais inválidas",
      "type": "negative",
      "preconditions": [
        "O serviço de autenticação está disponível."
      ],
      "steps": [
        {
          "action": "POST",
          "endpoint": "/auth/login",
          "headers": {
            "Content-Type": "application/json"
          },
          "payload": {
            "username": "user",
            "password": "errado"
          },
          "expected": {
            "statusCode": 401
          }
        }
      ]
    },
    {
      "id": "TC-003",
      "name": "Rejeitar acesso ao catálogo sem token",
      "type": "negative",
      "preconditions": [
        "O serviço de catálogo está disponível."
      ],
      "steps": [
        {
          "action": "GET",
          "endpoint": "/products/list",
          "headers": {},
          "expected": {
            "statusCode": 401
          }
        }
      ]
    },
    {
      "id": "TC-004",
      "name": "Rejeitar token expirado",
      "type": "negative",
      "preconditions": [
        "O serviço de catálogo está disponível.",
        "Há um JWT assinado com a chave confiável e claims válidas, mas com expiração anterior ao horário atual."
      ],
      "steps": [
        {
          "action": "GET",
          "endpoint": "/products/list",
          "headers": {
            "Authorization": "Bearer ${expiredToken}"
          },
          "expected": {
            "statusCode": 403
          }
        }
      ]
    },
    {
      "id": "TC-005",
      "name": "Rejeitar token malformado",
      "type": "negative",
      "preconditions": [
        "O serviço de catálogo está disponível."
      ],
      "steps": [
        {
          "action": "GET",
          "endpoint": "/products/list",
          "headers": {
            "Authorization": "Bearer abc.def"
          },
          "expected": {
            "statusCode": 403
          }
        }
      ]
    },
    {
      "id": "TC-006",
      "name": "Rejeitar token com assinatura incorreta",
      "type": "negative",
      "preconditions": [
        "O serviço de catálogo está disponível.",
        "Há um JWT com formato válido, claims válidas e expiração futura, mas assinado com uma chave diferente da chave confiável."
      ],
      "steps": [
        {
          "action": "GET",
          "endpoint": "/products/list",
          "headers": {
            "Authorization": "Bearer ${invalidSignatureToken}"
          },
          "expected": {
            "statusCode": 403
          }
        }
      ]
    },
    {
      "id": "TC-007",
      "name": "Rejeitar token emitido para outro público",
      "type": "negative",
      "preconditions": [
        "O serviço de catálogo está disponível.",
        "Há um JWT assinado com a chave confiável, com issuer correto e expiração futura, mas cujo audience não corresponde ao serviço de catálogo."
      ],
      "steps": [
        {
          "action": "GET",
          "endpoint": "/products/list",
          "headers": {
            "Authorization": "Bearer ${wrongAudienceToken}"
          },
          "expected": {
            "statusCode": 403
          }
        }
      ]
    }
  ]
}
