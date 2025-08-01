package io.github.paulooosf.gameboxed.controller;

import io.github.paulooosf.gameboxed.dto.LoginRespostaDTO;
import io.github.paulooosf.gameboxed.repository.UsuarioRepository;
import io.github.paulooosf.gameboxed.service.LoginGoogleService;
import io.github.paulooosf.gameboxed.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/autenticar/google")
public class LoginGoogleController {

    private final LoginGoogleService loginGoogleService;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;

    @Autowired
    public LoginGoogleController(LoginGoogleService loginGoogleService, UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.loginGoogleService = loginGoogleService;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<Void> redirecionarGoogle() {
        String url = loginGoogleService.gerarUrl();

        var headers = new HttpHeaders();
        headers.setLocation(URI.create(url));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/autorizado")
    public ResponseEntity<LoginRespostaDTO> autenticarUsuarioOAuth(@RequestParam String code) {
        String email = loginGoogleService.obterEmail(code);
        var usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(autenticacao);

        String token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new LoginRespostaDTO(token));
    }
}