package io.github.paulooosf.gameboxed.dto;

import io.github.paulooosf.gameboxed.model.Avaliacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoEntradaDTO (@NotBlank(message = "Preencha o comentário!") String comentario,
                                   @NotNull(message = "Preencha a nota!") Double nota,
                                   @NotNull(message = "Preencha o id do jogo!") Long idJogo
                                    ){
    public AvaliacaoEntradaDTO(Avaliacao avaliacao) {
        this(avaliacao.getComentario(), avaliacao.getNota(), avaliacao.getJogo().getId());
    }

    public Avaliacao converter() {
        return new Avaliacao(this);
    }
}
