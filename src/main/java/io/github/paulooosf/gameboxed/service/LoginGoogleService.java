package io.github.paulooosf.gameboxed.service;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class LoginGoogleService {

    @Value("${google.oauth.client.id}")
    private String clientId;
    @Value("${google.oauth.client.secret}")
    private String clientSecret;
    private final String redirectUri = "http://localhost:8080/autenticar/google/autorizado";
    private final RestTemplate restTemplate;

    @Autowired
    public LoginGoogleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String gerarUrl() {
        return "https://accounts.google.com/o/oauth2/v2/auth" +
                "?client_id=" + clientId +
                "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8) +
                "&scope=https://www.googleapis.com/auth/userinfo.email" +
                "&response_type=code";
    }

    private String obterToken(String code) {
        String url = "https://oauth2.googleapis.com/token";

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var body = Map.of(
                "code", code,
                "client_id", clientId,
                "client_secret", clientSecret,
                "redirect_uri", redirectUri,
                "grant_type", "authorization_code"
        );

        var request = new HttpEntity<>(body, headers);

        var response = restTemplate.postForEntity(url, request, Map.class);

        var resposta = response.getBody();
        return resposta.get("id_token").toString();
    }

    public String obterEmail(String code) {
        String token = obterToken(code);

        var decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("email").asString();
    }
}