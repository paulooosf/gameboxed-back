package io.github.paulooosf.gameboxed.service;

import io.github.paulooosf.gameboxed.dto.SolicitarRedefinicaoSenhaDTO;
import io.github.paulooosf.gameboxed.dto.RedefinirSenhaDTO;
import io.github.paulooosf.gameboxed.model.Usuario;
import io.github.paulooosf.gameboxed.repository.UsuarioRepository;
import io.github.paulooosf.gameboxed.validation.ValidarUsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SenhaService {

    private final UsuarioRepository repository;

    private final EmailService emailService;

    private final TokenService tokenService;

    @Autowired
    public SenhaService(UsuarioRepository repository, EmailService emailService, TokenService tokenService) {
        this.repository = repository;
        this.emailService = emailService;
        this.tokenService = tokenService;
    }

    public void solicitarRedefinicaoSenha(SolicitarRedefinicaoSenhaDTO dto) {
        Optional<Usuario> usuarioOpt = repository.findByEmail(dto.email());
        ValidarUsuarioExistente.validar(usuarioOpt);

        String token = tokenService.gerarTokenSenha(dto.email());
        emailService.enviarEmailRedefinirSenha(dto.email(), token);
    }

    public void redefinirSenha(RedefinirSenhaDTO dto) {
        String email = tokenService.validarTokenSenha(dto.token());

        Optional<Usuario> usuarioOpt = repository.findByEmail(email);
        ValidarUsuarioExistente.validar(usuarioOpt);

        var usuario = usuarioOpt.get();
        usuario.setSenha(new BCryptPasswordEncoder().encode(dto.senha()));
        repository.save(usuario);
    }
}
