package io.github.paulooosf.gameboxed.dto;

import io.github.paulooosf.gameboxed.model.Jogo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JogoListarDTO(@NotNull(message = "ID Não preenchido!") Long id,
                            @NotBlank(message = "Nome não preenchido!") String nome,
                            @NotNull(message = "Ano não preenchido!") Integer ano,
                            Double nota,
                            @NotBlank(message = "Link da capa não preenchido!") String linkCapa
                            ) {
    public JogoListarDTO(Jogo jogo) {
        this(jogo.getId(), jogo.getNome(), jogo.getAno(), jogo.getNota(), jogo.getLinkCapa());
    }
}
