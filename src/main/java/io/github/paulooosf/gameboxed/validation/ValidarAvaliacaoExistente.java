package io.github.paulooosf.gameboxed.validation;

import io.github.paulooosf.gameboxed.exception.NaoEncontradoException;
import io.github.paulooosf.gameboxed.model.Avaliacao;

import java.util.Optional;

public class ValidarAvaliacaoExistente {

    private ValidarAvaliacaoExistente() {
    }

    public static void validar(Optional<Avaliacao> avaliacaoOpt) {
        if (avaliacaoOpt.isEmpty()) {
            throw new NaoEncontradoException("Avaliação não encontrada!");
        }
    }
}
