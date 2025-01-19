package io.github.paulooosf.gameboxed.dto;

import io.github.paulooosf.gameboxed.model.Avaliacao;
import io.github.paulooosf.gameboxed.model.Jogo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoSaidaDTO(@NotNull(message = "ID não preenchido!") Long id,
                                @NotBlank(message = "Comentário não preenchido!") String comentario,
                                @NotNull(message = "Nota não preenchida!") Double nota,
                                @NotBlank(message = "Usuario não preenchido!") String usuario,
                                @NotNull(message = "Jogo não preenchido!") Jogo jogo
                                ) {
    public AvaliacaoSaidaDTO(Avaliacao avaliacao) {
        this(avaliacao.getId(), avaliacao.getComentario(), avaliacao.getNota(), avaliacao.getUsuario().getApelido(), avaliacao.getJogo());
    }
}
