
# Pior Filme API

Este projeto é uma API construída com **Java 11** e **Spring Boot 2.6.2**. O gerenciamento de dependências e a construção do projeto são feitos com o **Maven**.

## Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:

- **Java 11** (JDK 11)
- **Maven** (versão 3.3.5 ou superior)

## Configuração e Uso

O arquivo que será utilizado para realizar a carga dos filmes, pode ser configurado no arquivo de configuração application.properties.
Está pré-configurado no caminho resources/data/movielist.csv 

### Compilar o Projeto

Para compilar o projeto e resolver as dependências, execute:

```bash
mvn clean install
```

### Executar os Testes de Integração

Para rodar os testes de integração, utilize:

```bash
mvn test
```

### Subir o Projeto Localmente

Para iniciar o projeto em um servidor local, execute:

```bash
mvn spring-boot:run
```

O servidor estará disponível em `http://localhost:8080`.

### Testar o Endpoint

Após subir o projeto, teste o endpoint principal com uma requisição **GET**:

```plaintext
GET http://localhost:8080/filmes
```
