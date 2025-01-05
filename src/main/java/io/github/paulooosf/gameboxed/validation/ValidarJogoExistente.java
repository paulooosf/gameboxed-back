package io.github.paulooosf.gameboxed.validation;

import io.github.paulooosf.gameboxed.exception.NaoEncontradoException;
import io.github.paulooosf.gameboxed.model.Jogo;
import io.github.paulooosf.gameboxed.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidarJogoExistente {

    @Autowired
    private JogoRepository repository;

    public static void validar(Optional<Jogo> jogoOpt) {
        if (jogoOpt.isEmpty()) {
            throw new NaoEncontradoException("Jogo não encontrado!");
        }
    }
}
