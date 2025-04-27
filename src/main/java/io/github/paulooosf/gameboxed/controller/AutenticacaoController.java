package io.github.paulooosf.gameboxed.controller;

import io.github.paulooosf.gameboxed.dto.LoginDTO;
import io.github.paulooosf.gameboxed.dto.LoginRespostaDTO;
import io.github.paulooosf.gameboxed.dto.RedefinirSenhaDTO;
import io.github.paulooosf.gameboxed.dto.SolicitarRedefinicaoSenhaDTO;
import io.github.paulooosf.gameboxed.model.Usuario;
import io.github.paulooosf.gameboxed.service.SenhaService;
import io.github.paulooosf.gameboxed.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticar")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final SenhaService senhaService;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService, SenhaService senhaService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.senhaService = senhaService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRespostaDTO> login(@Valid @RequestBody LoginDTO dto) {
        var usuarioSenha = new UsernamePasswordAuthenticationToken(dto.apelido(), dto.senha());
        var autenticacao = this.authenticationManager.authenticate(usuarioSenha);

        String token = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new LoginRespostaDTO(token));
    }

    @PostMapping("/esqueci-senha")
    public ResponseEntity<Void> solicitarRedefinicaoSenha(@Valid @RequestBody SolicitarRedefinicaoSenhaDTO dto) {
        senhaService.solicitarRedefinicaoSenha(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/redefinir-senha")
    public ResponseEntity<Void> redefinirSenha(@Valid @RequestBody RedefinirSenhaDTO dto) {
        senhaService.redefinirSenha(dto);
        return ResponseEntity.noContent().build();
    }
}
