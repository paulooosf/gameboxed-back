package io.github.paulooosf.gameboxed.service;

import io.github.paulooosf.gameboxed.dto.UsuarioEntradaDTO;
import io.github.paulooosf.gameboxed.dto.UsuarioSaidaDTO;
import io.github.paulooosf.gameboxed.model.Usuario;
import io.github.paulooosf.gameboxed.repository.UsuarioRepository;
import io.github.paulooosf.gameboxed.validation.ValidarUsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
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
        Usuario usuario = repository.save(usuarioDTO.converter());
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
