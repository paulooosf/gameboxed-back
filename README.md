<div align="center">
    <h1>GameboXed <i>(Back-end)</i></h1>
    <img alt="Logo do projeto" src="https://i.imgur.com/KXc8kaX.png">
    <h3>Back-end do projeto GameboXed, uma plataforma de avaliação de jogos.</h3>
    <img alt="Java" src="https://img.shields.io/badge/JAVA-%23f89820?style=for-the-badge&logo=openjdk&logoColor=f89820&labelColor=%23000000">
    <img alt="Spring" src="https://img.shields.io/badge/SPRING-%236DB33F?style=for-the-badge&logo=spring&logoColor=%236DB33F&labelColor=black">
    <img alt="JWT" src="https://img.shields.io/badge/jwt-000000?style=for-the-badge&logo=jsonwebtokens&labelColor=000000">
    <img alt="PostgreSQL" src="https://img.shields.io/badge/POSTGRESQL-%234169E1?style=for-the-badge&logo=POSTGRESQL&logoColor=%234169E1&labelColor=black">
    <img alt="Docker" src="https://img.shields.io/badge/docker-%232496ED?style=for-the-badge&logo=docker&logoColor=%232496ED&labelColor=black">
    <img alt="Redis" src="https://img.shields.io/badge/redis-%23FF4438?style=for-the-badge&logo=redis&logoColor=%23FF4438&labelColor=black">
    <img alt="AWS" src="https://img.shields.io/badge/AWS-%23FF9900?style=for-the-badge&logo=amazonwebservices&logoColor=%23FF9900&labelColor=black">
</div>

<p align="center">
  <a href="#funcionalidades">Funcionalidades</a> •
  <a href="#infraestrutura">Infraestrutura</a> •
  <a href="#documentacao">Documentação</a> •
  <a href="#como-rodar">Como rodar</a> •
  <a href="#créditos">Créditos</a>
</p>

## Funcionalidades
- CRUD Completo de Usuários, Jogos e Avaliações;
- Autorização com Roles;
- Cadastro e Login utilizando autenticação via tokens JWT;
- Criptografia de senhas no banco de dados;
- Possibilidade de listar, filtrar e buscar jogos, contendo paginação;
- Dinâmica de avaliação de um jogo, sendo de 1 a 5 pontos e podendo incluir um comentário;
- Atualização dinâmica da nota de um jogo ao avaliar ou editar/deletar uma avaliação;
- Envio de e-mails ao cadastrar;
- Redefinição de senha por e-mail utilizando tokens JWT;
- Login via Google utilizando OAuth 2.0;
- Conteinerização com Docker;
- Cache com Redis.

## Infraestrutura

O projeto está hospedado na AWS com a seguinte arquitetura:

- **Back-end:** instância EC2 (Ubuntu) na região `sa-east-1`, rodando os containers via Docker Compose.
- **Banco de dados:** PostgreSQL em container Docker na própria EC2, com volume persistente.
- **Cache:** Redis em container Docker na própria EC2.
- **Registro de imagens:** Amazon ECR, utilizado para armazenar e distribuir a imagem Docker do back-end.
- **Front-end:** hospedado no Amazon S3 com distribuição via CloudFront.

Todas as variáveis sensíveis (credenciais de banco, JWT secret, OAuth, e-mail) são injetadas via variáveis de ambiente em tempo de execução, sem nenhum valor hardcoded no código.

## Documentação
Todos os endpoints estão documentados via Swagger. Ao rodar o projeto localmente, acesse a documentação em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## Como rodar

Certifique-se de ter o [Docker](https://docs.docker.com/get-started/get-docker/) e Docker Compose instalados.

1. Clone o repositório;
2. Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:
```
GOOGLE_CLIENT_ID=seu-client-id
GOOGLE_CLIENT_SECRET=seu-client-secret
MAIL_USERNAME=seu-email@gmail.com
MAIL_PASSWORD=sua-app-password
JWT_SECRET=uma-string-longa-e-aleatoria
```
3. Crie a imagem do back-end:
```
docker build -t gameboxed-back:1.0 .
```
4. Siga os passos para criar a imagem do front-end no seu [repositório](https://github.com/paulooosf/gameboxed-front).
5. Suba os containers:
```
docker-compose up -d
```

Após a inicialização, a API estará disponível em http://localhost:8080

_O sistema será inicializado com dois logins disponíveis, com o apelido/senha sendo o mesmo: `admin` e `usuario`.
O sistema também será populado com alguns jogos de início._

_O login via Google OAuth 2.0 requer a configuração das variáveis `GOOGLE_CLIENT_ID` e `GOOGLE_CLIENT_SECRET`, obtidas via Google Cloud Console. Sem elas, essa funcionalidade retornará erro._

## Créditos
- Paulo Henrique - [paulooosf](http://github.com/paulooosf)
