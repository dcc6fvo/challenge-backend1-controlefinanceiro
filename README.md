# controlefinanceiro
Desafio Alura Challenge Back-end v2 - API Rest de controle financeiro doméstico escrita em Java.

Tecnologias:
- Java Spring Boot (2.5.8)
- Maven
- Database H2 ou MySQL
- Modelagem das entidades utilizando herança (conta -> despesa, conta -> receita)
- Modelagem CRUD utilizando classe YearMonth ou LocalDate
- Criação de classe de deserializer de LocalDate para utilizar apenas formato YYYY-mm
- Adicionados controles de exceção para autenticação e badrequests
- Separação de funcionalidade de controllers em classes service
- Autenticação com token JWT
- Definição de 27 testes automatizados com/sem autenticação
- Configurações personalizadas de deploy DEV, TEST e PROD
- Configurações de ROLE (ex. ROLE_ADMIN, ROLE,GUEST..)

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)
