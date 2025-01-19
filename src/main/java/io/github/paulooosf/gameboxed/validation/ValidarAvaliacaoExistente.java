package io.github.paulooosf.gameboxed.validation;

import io.github.paulooosf.gameboxed.exception.NaoEncontradoException;
import io.github.paulooosf.gameboxed.model.Avaliacao;
import io.github.paulooosf.gameboxed.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ValidarAvaliacaoExistente {

    @Autowired
    private AvaliacaoRepository repository;

    public static void validar(Optional<Avaliacao> avaliacaoOpt) {
        if (avaliacaoOpt.isEmpty()) {
            throw new NaoEncontradoException("Avaliação não encontrada!");
        }
    }
}
