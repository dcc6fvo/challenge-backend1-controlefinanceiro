# controlefinanceiro
Desafio Alura Challenge Back-end v2 - API Rest de controle financeiro doméstico escrita em Java.

![Badge em Desenvolvimento](https://img.shields.io/badge/STATUS-Em%20desenvolvimento-success)
![License](https://img.shields.io/badge/LICENSE-GPLv2-blue)


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


