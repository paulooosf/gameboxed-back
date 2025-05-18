package io.github.paulooosf.gameboxed.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.github.paulooosf.gameboxed.exception.GeracaoTokenException;
import io.github.paulooosf.gameboxed.exception.TokenInvalidoException;
import io.github.paulooosf.gameboxed.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String chave;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(chave);
            return JWT.create()
                    .withIssuer("GameboXed")
                    .withSubject(usuario.getApelido())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new GeracaoTokenException("Erro gerando o token: " + exception);
        }
    }

    public String gerarTokenSenha(String email) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(chave);
            return JWT.create()
                    .withIssuer("GameboXed-redefinir-senha")
                    .withSubject(email)
                    .withExpiresAt(gerarDataExpiracaoCurta())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new GeracaoTokenException("Erro gerando o token de senha: " + exception);
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(chave);
            return JWT.require(algorithm)
                    .withIssuer("GameboXed")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new TokenInvalidoException("Token invalido: " + exception);
        }
    }

    public String validarTokenSenha(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(chave);
            return JWT.require(algorithm)
                    .withIssuer("GameboXed-redefinir-senha")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new TokenInvalidoException("Token de senha invalido: " + exception);
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant gerarDataExpiracaoCurta() {
        return LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00"));
    }
}
