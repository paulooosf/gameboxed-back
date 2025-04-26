package io.github.paulooosf.gameboxed.validation;

import io.github.paulooosf.gameboxed.exception.NaoEncontradoException;
import io.github.paulooosf.gameboxed.model.Usuario;
import io.github.paulooosf.gameboxed.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidarUsuarioExistente {

    private ValidarUsuarioExistente() {
    }

    public static void validar(Optional<Usuario> usuarioOpt) {
        if (usuarioOpt.isEmpty()) {
            throw new NaoEncontradoException("Usuário não encontrado!");
        }
    }
}
