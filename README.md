# Desafio Dock - Software Engineering I

> Repositório referente a API Rest de operações bancárias, desenvolvida em Java junto ao framework Spring Boot.


## Diretórios

- `src/main/java/com/desafio/dock` Nesse caminho estão todas as pastas nescessárias para o funcionamento do sistema.
  - `/business` Na busca por um alto acoplamento do projeto, foi utilizado o padrão Business delegate com intuito de separar a camada de apresentação, da lógica de negócio. Nesse diretório encontra-se o arquivo AccountBusinessDelegate.java
    - `/AccountBusinessDelegate` Responsável por delegar funções ao accountService usando AccountDTO.
  - `/config` Diretório onde encontra-se o arquivo SwaggerConfig.java.
    - `/SwaggerConfig` Responsável por documentar a API.
  - `/controllers`  Diretório onde encontra-se os arquivos AccountController.java e TransactionController.java. É no controller onde ficam as rotas para a comunicação com o client-side. 
    - `/AccountController` Responsável por criar uma conta, consultar saldo, bloquear conta e recuperar extrato.
    - `/TransactionController`  Responsável pelas operações de depósito e saque em uma conta.
  - `/dto` Para encapsular e reunir os dados de forma coesa, e transportá-los entre as camadas da aplicação. Foram criados os arquivos AccountDTO.java, PersonDTO.java.
    - `/AccountDTO` Responsável por criar o DTO de uma conta.
    - `/PersonDTO` Responsável por criar o DTO de uma pessoa.
  - `/exceptions` Diretório onde encontram-se os pacotes com as exceções para cada entidade do sistema e seus atributos.
    - `/account` Pacote que contém as exceções para situações na entidade Account.
    - `/transaction` Pacote que contém as exceções para situações na entidade Transaction.
    - `EntityNotExistsException.java` Exceção lançada quando a entidade procurada não existe.
  - `/models` Diretório onde encontra-se os arquivos Account.java, Person.java, Transaction.java. É nesse pacote que estão as entidades usadas no sistema junto ao OperationType.java que é um enum. 
    - `/Account` Entidade responsável pela Conta.
    - `/Person` Entidade responsável pela Pessoa.
    - `/Transaction` Entidade responsável pela Transação.
    - `/OperationType` Enum para o tipo de operação de uma transação.
  - `/repositories` Diretório onde encontra-se os arquivos AccountRepository.java, PersonRepository.java, TransactionRepository.java.
    - `/AccountRepository`  Responsável pelo DAO de conta com as funcionalidades de buscar uma conta a partir do id.
    - `/PersonRepository`  Responsável pelo DAO de pessoa com as funcionalidades de buscar uma pessoa a partir do id.
    - `/TransactionRepository`  Responsável pelo DAO de transação.
  - `/services` Diretório onde encontram-se os arquivos AccountService.java/ AccountBean.java, PersonService.java/ PersonBean.java, TransactionService.java/ TransactionBean.java. Sendo os arquivos Service interfaces com a assinatura do metodos e o bean implementando suas respectivas interfaces. 
    - `/account`  Service responsável pela implementação de metódos como: criar uma conta, consultar saldo, bloquear conta, encontrar conta e atualizar conta.
    - `/person`  Service responsável pela implementação de metódos como: criar uma pessoa e buscar uma ou todas as pessoas do sistema.
    - `/transaction` Service responsável pela implementação do método de operação (deposito ou saque).
  - `/validators` Diretório onde encontra-se os arquivos AccountValidator.java, TransactionValidator.java
    - `/AccountValidator` Responsável por validar uma conta.
    - `/TransactionValidator` Responsável por validar uma transação.

## Dependências 

- `JPA:` Leve framework que descreve uma interface comum para a persistência de dados. Auxiliando na criação dos repositórios(DAO) e definindo um meio de mapeamento objeto-relacional para objetos Java.
- `Swagger:` Framework que auxilia na descrição, consumo e visualização de serviços de uma API REST.
- `H2:` Banco de dados relacional que persiste os dados em memória. (Escolhido por apresentar uma configuração fácil com o Spring) 

## Iniciar o projeto

```
    * Realizar git clone do repósitorio;
    * Ter o Java instalado na maquina;
    * Abrir a aplicação em uma IDEA (Utilizo o IntelliJ);
    * Rodar o arquivo pom.xml para baixar as dependências;
    * Digitar o comando (mvn spring-boot:run) via CLI na pasta raiz;
    * Paracendo no terminal (Started DockApplication in XXX seconds) a aplicação iniciou com sucesso 
    * Utilizar o Postman para realizar as requisições a URL (localhost:8080/api);
```
## A API

- Como não é necessário realizar operações com a tabela pessoa, foi criada a sua entidade junto a um service básico, que é chamado no arquivo DockApplication.java, para a criação de pelo menos uma pessoa.

- `POST: api/account/{idPerson}` O primeiro passo a ser realizado é a criação de uma conta utilizando o idPerson que representa uma Pessoa que está cadastrada no sistema e está abrindo uma conta no banco. Como request desse metodo deve-se passa o seguinte JSON no corpo da requisição: 

Request

```
{
  "typeAccount": 1, (Inteiro maior que 0)
  "withdrawalLimit": 60 (Double maior que 0)
}
```

Response

```
{
    "flagActive": true,
    "balance": 0.0,
    "transactions": [],
    "creationDate": "2021-09-17T07:55:41.621+0000",
    "person": {
        "name": "Almir Crispiniano",
        "idPerson": 1
    },
    "withdrawalLimit": 60.0,
    "typeAccount": 1,
    "idAccount": 1
}
```
- `POST: api/account/{idPerson}/transaction` Após ter uma conta criada, um usuário pode realizar operações de saque e deposito na conta. Como request desse metodo, devem se passados no form-data parametros como: 

- type(String): Só serão aceitas as palavras deposit(deposito) ou withdraw(saque)
- value(double): Só serão aceitos valores acima de 0

Request

```

  "type": "deposit"
  "value": 20

```

Response

```
{
    "idTransaction": 1,
    "dateTransaction": "2021-09-17T08:00:34.048+0000",
    "value": 20.0,
    "operationType": "DEPOSIT"
}
```

- `GET: api/account/consultBalance/{idAccount}` Um usuário pode consultar o saldo da sua conta utilizando o idAccount como variavel da requisição. 


Request

```

  "idAccount": 1

```

Response

```
{
    "balance": 30.0,
    "message": "Account balance: 1"
}
```
- `GET: api/account/extract/{idAccount}` Um usuário pode recupera o extrato de transações de uma conta utilizando o idAccount como variavel da requisição. 

Request

```

  "idAccount": 1

```

Response

```
{
    "balance": 30.0,
    "flagActive": true,
    "message": "Transaction statement: Account 1",
    "transactions": [
        {
            "idTransaction": 1,
            "dateTransaction": "2021-09-17T08:00:34.048+0000",
            "value": 20.0,
            "operationType": "DEPOSIT"
        },
        {
            "idTransaction": 2,
            "dateTransaction": "2021-09-17T08:01:32.632+0000",
            "value": 15.0,
            "operationType": "DEPOSIT"
        },
        {
            "idTransaction": 3,
            "dateTransaction": "2021-09-17T08:01:39.716+0000",
            "value": 5.0,
            "operationType": "WITHDRAW"
        }
    ]
}
```

- `POST: api/account/lock/{idAccount}` Um usuário pode realiza o bloqueio de uma conta  utilizando o idAccount como variavel da requisição. Impossibilitando a realização de transações. 

Request

```

  "idAccount": 1

```

Response

```

{
    "message": "Account blocked successfully",
    "account": {
        "flagActive": false,
        "balance": 30.0,
        "transactions": [
            {
                "idTransaction": 1,
                "dateTransaction": "2021-09-17T08:00:34.048+0000",
                "value": 20.0,
                "operationType": "DEPOSIT"
            },
            {
                "idTransaction": 2,
                "dateTransaction": "2021-09-17T08:01:32.632+0000",
                "value": 15.0,
                "operationType": "DEPOSIT"
            },
            {
                "idTransaction": 3,
                "dateTransaction": "2021-09-17T08:01:39.716+0000",
                "value": 5.0,
                "operationType": "WITHDRAW"
            }
        ],
        "creationDate": "2021-09-17T07:55:41.621+0000",
        "person": {
            "name": "Almir Crispiniano",
            "idPerson": 1
        },
        "withdrawalLimit": 60.0,
        "typeAccount": 1,
        "idAccount": 1
    }
}

```
