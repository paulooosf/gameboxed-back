package io.github.paulooosf.gameboxed.controller;

import io.github.paulooosf.gameboxed.dto.LoginDTO;
import io.github.paulooosf.gameboxed.dto.LoginRespostaDTO;
import io.github.paulooosf.gameboxed.dto.RedefinirSenhaDTO;
import io.github.paulooosf.gameboxed.dto.SolicitarRedefinicaoSenhaDTO;
import io.github.paulooosf.gameboxed.model.Usuario;
import io.github.paulooosf.gameboxed.service.SenhaService;
import io.github.paulooosf.gameboxed.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Autenticação", description = "Endpoints relacionados a autenticação")
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

    @Operation(summary = "Autenticar usuário", description = "Autentica o usuário e retorna um token JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginRespostaDTO> login(@Valid @RequestBody LoginDTO dto) {

        var usuarioSenha = new UsernamePasswordAuthenticationToken(dto.apelido(), dto.senha());
        var autenticacao = this.authenticationManager.authenticate(usuarioSenha);

        String token = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new LoginRespostaDTO(token));
    }

    @Operation(summary = "Solicitar redefinição de senha", description = "Envia um e-mail com código de redefinição de senha.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Solicitação processada com sucesso"),
            @ApiResponse(responseCode = "400", description = "E-mail inválido"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor")
    })
    @PostMapping("/esqueci-senha")
    public ResponseEntity<Void> solicitarRedefinicaoSenha(@Valid @RequestBody SolicitarRedefinicaoSenhaDTO dto) {
        senhaService.solicitarRedefinicaoSenha(dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Redefinir senha", description = "Redefine a senha de um usuário com base no código enviado por e-mail.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Senha redefinida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Código inválido ou expirado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor")
    })
    @PostMapping("/redefinir-senha")
    public ResponseEntity<Void> redefinirSenha(@Valid @RequestBody RedefinirSenhaDTO dto) {
        senhaService.redefinirSenha(dto);
        return ResponseEntity.noContent().build();
    }
}
