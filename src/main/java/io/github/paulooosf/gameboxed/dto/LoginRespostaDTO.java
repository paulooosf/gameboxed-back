package io.github.paulooosf.gameboxed.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRespostaDTO(@NotBlank(message = "Token não preenchido!") String token) {
}
