package io.github.paulooosf.gameboxed.service;

import io.github.paulooosf.gameboxed.dto.UsuarioEntradaDTO;
import io.github.paulooosf.gameboxed.dto.UsuarioSaidaDTO;
import io.github.paulooosf.gameboxed.exception.UsuarioJaExisteException;
import io.github.paulooosf.gameboxed.model.Usuario;
import io.github.paulooosf.gameboxed.repository.UsuarioRepository;
import io.github.paulooosf.gameboxed.validation.ValidarUsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    private final EmailService emailService;

    @Autowired
    public UsuarioService(UsuarioRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public Page<UsuarioSaidaDTO> listar(Pageable pageable) {
        Page<Usuario> usuarios = repository.findAll(pageable);
        return usuarios.map(UsuarioSaidaDTO::new);
    }

    public UsuarioSaidaDTO buscar(Long id) {
        Optional<Usuario> usuarioOpt = repository.findById(id);
        ValidarUsuarioExistente.validar(usuarioOpt);
        return new UsuarioSaidaDTO(usuarioOpt.get());
    }

    public UsuarioSaidaDTO cadastrar(UsuarioEntradaDTO usuarioDTO) {
        if (repository.existsByApelido(usuarioDTO.apelido())) {
            throw new UsuarioJaExisteException("O apelido já foi escolhido por outro usuário!");
        }

        if (repository.existsByEmail(usuarioDTO.email())) {
            throw new UsuarioJaExisteException("O e-mail escolhido já está sendo utilizado!");
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());
        var usuario = new Usuario(usuarioDTO.apelido(), usuarioDTO.email(), senhaCriptografada);
        repository.save(usuario);
        emailService.enviarEmailCadastro(usuarioDTO.email(), usuarioDTO.apelido());
        return new UsuarioSaidaDTO(usuario);
    }

    public UsuarioSaidaDTO editar(Long id, Usuario usuario) {
        ValidarUsuarioExistente.validar(repository.findById(id));
        usuario.setId(id);
        return new UsuarioSaidaDTO(repository.save(usuario));
    }

    public void deletar(Long id) {
        ValidarUsuarioExistente.validar(repository.findById(id));
        repository.deleteById(id);
    }
}
