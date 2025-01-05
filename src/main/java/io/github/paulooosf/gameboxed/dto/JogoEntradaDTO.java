package io.github.paulooosf.gameboxed.dto;

import io.github.paulooosf.gameboxed.model.Jogo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JogoEntradaDTO(@NotBlank String nome,
                           @NotBlank String descricao,
                           @NotBlank String empresa,
                           @NotNull Integer ano,
                           @NotBlank String linkCapa,
                           @NotBlank String linkBanner,
                           @NotBlank String linkTrailer
) {
    public JogoEntradaDTO(Jogo jogo) {
        this(jogo.getNome(), jogo.getDescricao(), jogo.getEmpresa(), jogo.getAno(), jogo.getLinkCapa(),
                jogo.getLinkBanner(), jogo.getLinkTrailer());
    }

    public Jogo converter() {
        return new Jogo(this);
    }
}
