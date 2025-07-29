package io.github.paulooosf.gameboxed.dto;

import io.github.paulooosf.gameboxed.model.Jogo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

public record JogoSaidaDTO(@NotNull(message = "ID Não preenchido!") Long id,
                           @NotBlank(message = "Nome não preenchido!") String nome,
                           @NotBlank(message = "Descrição não preenchida!") String descricao,
                           @NotBlank(message = "Empresa não preenchida!") String empresa,
                           @NotNull(message = "Ano não preenchido!") Integer ano,
                           Double nota,
                           @NotNull(message = "Quantidade de avaliações não preenchida!") Integer quantidadeAvaliacoes,
                           @NotBlank(message = "Link da capa não preenchido!") String linkCapa,
                           @NotBlank(message = "Link do banner não preenchido!") String linkBanner,
                           @NotBlank(message = "Link do trailer não preenchido!") String linkTrailer,
                           @NotNull(message = "Lista de avaliações não preenchida!") List<AvaliacaoSaidaDTO> avaliacoes
                           ) implements Serializable {
    public JogoSaidaDTO(Jogo jogo) {
        this(jogo.getId(), jogo.getNome(), jogo.getDescricao(), jogo.getEmpresa(), jogo.getAno(), jogo.getNota(),
                jogo.getQuantidadeAvaliacoes(), jogo.getLinkCapa(), jogo.getLinkBanner(), jogo.getLinkTrailer(),
                jogo.getAvaliacoes()
                        .stream()
                        .map(AvaliacaoSaidaDTO::new)
                        .toList());
    }
}
