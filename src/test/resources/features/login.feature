# language: pt

Funcionalidade: Apenas usuários

  Cenário: Um usuário válido consegue se logar
    Dado o usuário válido
    Quando realiza login
    Então é redirecionado para a página de leilões

  Cenário: Um usuário inválido não consegue se logar
    Dado o usuário inválido
    Quando tenta se logar
    Então continua na página de login