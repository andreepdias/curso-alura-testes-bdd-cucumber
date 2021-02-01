# language: pt

Funcionalidade: Propondo lances ao leilão

Cenario: Propondo um único lance válido
  Dado um lance válido
  Quando propõe ao leilão
  Entao o lance é aceito

Cenario: Propondo vários lances  válidos
  Dado um lance de 10.0 reais da usuária "Fernanda"
  E um lance de 15.0 reais da usuária "Bruna"
  Quando propõe vários lances ao leilão
  Entao os lances são aceitos

Esquema do Cenario: Propondo um lance inválido
  Dado um lance inválido de <valor> reais
  Quando propõe ao leilão
  Entao o lance não é aceito

  Exemplos:
    | valor |
    | 0.0   |
    | -1.0  |


Cenario: Propondo uma sequência da lances
  Dado um lance de 10.0 reais da usuária "Fernanda"
  E um lance de 15.0 reais da usuária "Fernanda"
  Quando propõe vários lances ao leilão
  Entao o segundo lance não é aceito

Cenario: Propondo uma sequência da lances
  Dado dois lances
    | valor | nomeUsuario |
    | 10.0  | Bruna       |
    | 15.0  | Bruna       |
  Quando propõe vários lances ao leilão
  Entao o segundo lance não é aceito


