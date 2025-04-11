<h1 align="center">GameboXed <i>(Back-end)</i></h1>
<h4 align="center">Back-end do projeto GameboXed, uma plataforma de avaliação de jogos.</h4>
<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/JAVA-%23f89820?style=for-the-badge&logo=openjdk&logoColor=f89820&labelColor=%23000000">
  <img alt="Spring" src="https://img.shields.io/badge/SPRING-%236DB33F?style=for-the-badge&logo=spring&logoColor=%236DB33F&labelColor=black">
  <img alt="JWT" src="https://img.shields.io/badge/jwt-000000?style=for-the-badge&logo=jsonwebtokens&labelColor=000000">
  <img alt="Docker" src="https://img.shields.io/badge/docker-%232496ED?style=for-the-badge&logo=docker&logoColor=%232496ED&labelColor=black">
</p>
<p align="center">
  <a href="#funcionalidades">Funcionalidades</a> •
  <a href="#como-rodar">Como rodar</a> •
  <a href="#créditos">Créditos</a>
</p>

## Funcionalidades
- Cadastro e Login utilizando autenticação via tokens JWT;
- Possibilidade de listar, filtrar e buscar jogos, contendo paginação;
- Dinâmica de avaliação de um jogo, sendo de 1 a 5 pontos e podendo incluir um comentário;
- Atualização dinâmica da nota de um jogo;
- Método de redefinir senha via e-mail _W.I.P_
## Como rodar
Certifique-se de ter o [Docker](https://docs.docker.com/get-started/get-docker/) e Docker Compose instalados.
1. Clone o repositório;
2. No terminal da pasta raiz do projeto, crie a imagem do back-end:
```
docker build -t gameboxed-backend .
```
4. Suba os containers:
```
docker-compose up -d
```
Após a inicialização, a API estará disponível em http://localhost:8080
## Créditos
- Paulo Henrique - [paulooosf](http://github.com/paulooosf)
