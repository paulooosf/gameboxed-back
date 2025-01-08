package io.github.paulooosf.gameboxed.dto;

import io.github.paulooosf.gameboxed.model.Jogo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JogoEntradaDTO(@NotBlank(message = "Preencha o nome!") String nome,
                           @NotBlank(message = "Preencha a descrição!") String descricao,
                           @NotBlank(message = "Preencha a empresa!") String empresa,
                           @NotNull(message = "Preencha o ano!") Integer ano,
                           @NotBlank(message = "Preencha o link da capa!") String linkCapa,
                           @NotBlank(message = "Preencha o link do banner!") String linkBanner,
                           @NotBlank(message = "Preencha o link do trailer!") String linkTrailer
) {
    public JogoEntradaDTO(Jogo jogo) {
        this(jogo.getNome(), jogo.getDescricao(), jogo.getEmpresa(), jogo.getAno(), jogo.getLinkCapa(),
                jogo.getLinkBanner(), jogo.getLinkTrailer());
    }

    public Jogo converter() {
        return new Jogo(this);
    }
}
