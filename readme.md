# Projeto Java Spring Boot

Este projeto utiliza **Java 21+**, **Spring Boot**, **Maven** e um banco de dados SQL. Pode ser executado tanto localmente quanto via Docker Compose para facilitar o setup.

---

## Pré-requisitos

* JDK 21 ou superior
* Maven 3.3.2
* Docker e Docker Compose (opcional)
* VS Code (opcional) com as extensões recomendadas:

    * Java Extension Pack
    * Spring Boot Extension Pack
    * Docker

---

---

## Executando com Docker Compose

1. Verifique se o Docker e o Docker Compose estão instalados e funcionando.

2. Inicie o ambiente completo (banco + aplicação) com:

   ```bash
   docker-compose up --build
   ```
   
---

## Executando localmente

1. Clone o repositório e acesse a pasta do projeto:

   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <PASTA_DO_PROJETO>
   ```

2. Configure o banco de dados manualmente caso não utilize Docker, garantindo que as configurações em `application.properties` estejam corretas.

3. Compile e execute a aplicação:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
---

## Usando VS Code

1. Abra a pasta do projeto no VS Code:

   ```bash
   code .
   ```

2. Instale as extensões recomendadas (ver seção Pré-requisitos).

3. No terminal integrado, execute a aplicação:

   ```bash
   mvn spring-boot:run
   ```

4. Para depurar, use a aba **Run and Debug** e selecione a configuração **Spring Boot**.

---

## Variáveis de ambiente

Variáveis sensíveis como usuário e senha do banco devem ser configuradas via:

* Arquivo `.env`
* Diretamente no `docker-compose.yml`
* Arquivo `application.properties` (evite colocar informações sensíveis diretamente aqui em produção)

---

## Testes automatizados

Para executar os testes automatizados, rode:

```bash
mvn test
```