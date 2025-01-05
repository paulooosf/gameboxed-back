package io.github.paulooosf.gameboxed.dto;

import io.github.paulooosf.gameboxed.model.Jogo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JogoSaidaDTO(@NotNull Long id,
                           @NotBlank String nome,
                           @NotBlank String descricao,
                           @NotBlank String empresa,
                           @NotNull Integer ano,
                           Double nota,
                           @NotNull Integer quantidadeAvaliacoes,
                           @NotBlank String linkCapa,
                           @NotBlank String linkBanner,
                           @NotBlank String linkTrailer
                           ) {
    public JogoSaidaDTO(Jogo jogo) {
        this(jogo.getId(), jogo.getNome(), jogo.getDescricao(), jogo.getEmpresa(), jogo.getAno(), jogo.getNota(),
                jogo.getQuantidadeAvaliacoes(), jogo.getLinkCapa(), jogo.getLinkBanner(), jogo.getLinkTrailer());
    }
}
