## Banco Rest API

Uma API Restful para serviços bancários genéricos. Na aplicação o usuário pode fazer as seguintes tarefas:
* Cadastrar um Banco (instituição financeira);
* Cadastrar uma Agência pertencente a esse Banco;
* Cadastrar um Cliente com uma conta nessa Agência.

E uma vez dentro da conta, o usuário pode:
* Fazer um saque;
* Fazer um depósito;
* Fazer uma transferência.

Para mais detalhes da utilização da API, acessar a documentação:

## Passos pra fazer o Setup

**1. Clone o repositório** 

```bash
git clone git@github.com:renatosouza/BancoApi.git
```

**2. Inicie a aplicação usando Maven**

```bash
cd BancoApi
mvn spring-boot:run
```

Pronto! A aplicação pode ser acessada a partir da URL base: `http://localhost:8080`. Acessar a documentação da API para conhecer os endpoints.
