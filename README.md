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
    <img alt="GitHub Actions" src="https://img.shields.io/badge/github%20actions-%232088FF?style=for-the-badge&logo=githubactions&logoColor=%232088FF&labelColor=black">
    <img alt="Prometheus" src="https://img.shields.io/badge/prometheus-%23E6522C?style=for-the-badge&logo=prometheus&logoColor=%23E6522C&labelColor=black">
    <img alt="Grafana" src="https://img.shields.io/badge/grafana-%23F46800?style=for-the-badge&logo=grafana&logoColor=%23F46800&labelColor=black">
</div>

<p align="center">
  <a href="#funcionalidades">Funcionalidades</a> •
  <a href="#infraestrutura">Infraestrutura</a> •
  <a href="#observabilidade">Observabilidade</a> •
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
- **CI/CD:** pipeline GitHub Actions que, a cada push na `main`, roda os testes, builda e publica a imagem no ECR e faz o deploy automático na EC2 via SSH.

Todas as variáveis sensíveis (credenciais de banco, JWT secret, OAuth, e-mail) são injetadas via variáveis de ambiente em tempo de execução, sem nenhum valor hardcoded no código.

## Observabilidade

O projeto implementa observabilidade completa com stack Prometheus + Grafana:

<img alt="Captura de tela do Grafana" src="https://i.imgur.com/MN85HOF.png">

### Stack de Monitoramento

- **Spring Boot Actuator:** expõe métricas detalhadas da aplicação via endpoint `/actuator/prometheus`
- **Micrometer:** biblioteca de métricas que formata dados no padrão do Prometheus
- **Prometheus:** coleta e armazena métricas em time-series database, rodando na porta `9090`
- **Grafana:** visualiza métricas em dashboards interativos, rodando na porta `3001`

### Métricas Disponíveis

A aplicação expõe automaticamente:

- **JVM:** uso de memória heap/non-heap, garbage collection, threads
- **HTTP:** taxa de requisições, latência (percentis p50, p95, p99), distribuição de status codes
- **Database:** pool de conexões HikariCP (ativas, idle, tempo de espera)
- **Sistema:** uso de CPU, file descriptors
- **Negócio:** métricas customizadas (se implementadas)

### Dashboards Pré-configuradas

O Grafana vem com dashboard pré-provisionada mostrando:
- Uso de memória JVM em tempo real
- Taxa e duração de requisições HTTP
- Estado do pool de conexões do banco
- Uso de CPU do sistema e da aplicação
- Contagem de threads ativas e daemon

### Acessando

Localmente após subir com `docker compose up -d`:

- **Prometheus UI:** http://localhost:9090
- **Grafana:** http://localhost:3001 (usuário: `grafana`, senha: `grafana` ou defina a variável de ambiente `GRAFANA_ADMIN_PASSWORD`)

Na AWS, adicione as portas `9090` e `3001` no Security Group da EC2 para acesso externo.

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
