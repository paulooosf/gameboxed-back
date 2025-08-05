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
</div>
<p align="center">
  <a href="#funcionalidades">Funcionalidades</a> •
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
## Documentação
Todos os endpoints estão documentados via Swagger, portanto, ao rodar o projeto, você pode conferir a documentação
de todos os endpoints acessando [este link](http://localhost:8080/swagger-ui/index.html).
## Como rodar
Certifique-se de ter o [Docker](https://docs.docker.com/get-started/get-docker/) e Docker Compose instalados.
1. Clone o repositório;
2. Caso queira configurar algum e-mail para realizar os envios, preencha os dados no application.properties;
3. No terminal da pasta raiz do projeto, crie a imagem do back-end:
```
docker build -t gameboxed-back:1.0 .
```
4. Siga os passos para criar a imagem do front-end no seu [repositório](https://github.com/paulooosf/gameboxed-front).
5. Suba os containers:
```
docker-compose up -d
```
Após a inicialização, a API estará disponível em http://localhost:8080

_O sistema será inicializado com dois logins disponíveis, com o apelido/senha sendo o mesmo: admin e usuario.
O sistema também será populado com alguns jogos de início._

_O login via Google OAuth 2.0 é opcional. Para usá-lo, é necessário configurar as variáveis de ambiente `GOOGLE_CLIENT_ID` 
e `GOOGLE_CLIENT_SECRET`, obtidas via Google Cloud Console. Caso não configuradas, essa funcionalidade apresentará erro._
## Créditos
- Paulo Henrique - [paulooosf](http://github.com/paulooosf)
