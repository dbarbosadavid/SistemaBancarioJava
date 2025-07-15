Projeto feito em colaboração:
*dbarbosadavid (username)
*Brunolm (username)
*igormteles (username)

# Sistema Bancário Completo - Cliente/Servidor em Java

##  Descrição

Este projeto é um sistema bancário desenvolvido como parte de um trabalho acadêmico. Ele implementa uma arquitetura cliente-servidor completa, com um back-end responsável pela lógica de negócio e um front-end de desktop para interação com o usuário. A comunicação entre as duas partes é feita de forma segura e criptografada.

## Estrutura do Projeto

Este repositório é um **Monorepo** que contém dois projetos Maven independentes:

* **/sistemabancario-backend:** Contém a API RESTful construída com Spring Boot. É responsável por toda a lógica de negócio, como depósitos, saques e gerenciamento de contas.
* **/frontend-javafx:** Contém a aplicação cliente de desktop construída com JavaFX. É responsável pela interface gráfica com a qual o usuário interage.

## Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Back-End:** Spring Boot
* **Front-End:** JavaFX
* **Gerenciador de Dependências:** Apache Maven
* **Comunicação:** API REST com JSON
* **Segurança:** SSL/TLS (HTTPS)

## Como Executar o Projeto

É necessário ter o **JDK 21** (ou compatível) e o **Apache Maven** instalados e configurados no PATH do sistema.

**Importante:** O Back-End deve ser iniciado antes do Front-End.

### 1. Executar o Back-End

Abra um terminal na pasta `/sistemabancario-backend` e execute o comando:

```bash
mvn clean spring-boot:run
```
O servidor iniciará na porta `8443` com HTTPS. Deixe este terminal aberto.

### 2. Executar o Front-End

Abra um **novo** terminal na pasta `/frontend-javafx` e execute o comando:

```bash
mvn javafx:run
```
A janela da aplicação de desktop irá aparecer, pronta para se comunicar com o back-end.

## Autores

* [David Barbosa Araujo]
* [Bruno Loureiro Melo]
* [Igor Mateus Teles]

*Projeto desenvolvido em: 29 de junho de 2025*
