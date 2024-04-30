### Teste Técnico - Back End
🚀 A API fornece recursos para gerenciar clientes e funcionários


### A API desenvolvida fornece:

    * Endpoint para adicionar um Funcionário
    * Endpoint para adicionar um Cliente
    * Endpoint para consultar um Funcionário
    * Endpoint para consultar um Cliente
    * Endpoint para alterar um Funcionário
    * Endpoint para alterar um Cliente
    * Endpoint para remover um Funcionário
    * Endpoint para remover um Cliente

### Um cliente deve possuir os seguintes dados:

    Nome
    CPF (DEVE SER INFORMADO UM CPF VÁLIDO)
    Número de telefone
    Data do último serviço
    Endereço {
        rua,
        número,
        bairro,
        cidade,
        estado,
        cep,
        complemento
    },

### Um funcionário deve possuir os seguintes dados:

    Nome
    CPF (DEVE SER INFORMADO UM CPF VÁLIDO)
    Número de telefone
    Endereço {
        rua,
        número,
        bairro,
        cidade,
        estado,
        cep,
        complemento
    },
    Função,
    Status,
    Data de contratação

### Como Executar:
Clone o repositório

    git clone https://github.com/dmsviana/desafio-estagio-rpe.git

    Execute a aplicação Spring Boot.

    Acesse a aplicação em http://localhost:8080.

### Tecnologias:

As seguintes ferramentas foram usadas na construção do projeto:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data)
- [H2 Database](https://www.baeldung.com/spring-boot-h2-database)
- [Swagger](https://swagger.io/)
- [Lombok](https://projectlombok.org/)
- [JUnit](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)
- [AssertJ](https://assertj.github.io/doc/)


### Ferramentas:

- [Coleção Insomnia](https://www.dropbox.com/scl/fi/ms8yi7jpbecx6dyz4ces9/desafio-rpe-collection.json?rlkey=thouq6pxldaamh32wm0tdr37u&st=blzxuw9c&dl=0) para testar os endpoints.

### Documentação da API:

- [Swagger UI](http://localhost:8080/swagger-ui/index.html) clique aqui para acessar documentação da API.

### < Desenvolvido por dmsviana />
