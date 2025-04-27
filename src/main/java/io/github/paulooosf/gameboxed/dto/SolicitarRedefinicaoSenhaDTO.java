package io.github.paulooosf.gameboxed.dto;

import jakarta.validation.constraints.NotBlank;

public record SolicitarRedefinicaoSenhaDTO(@NotBlank(message = "Preencha o e-mail!") String email) {
}
