package io.github.paulooosf.gameboxed.dto;

import jakarta.validation.constraints.NotBlank;

public record RedefinirSenhaDTO(@NotBlank(message = "Preencha o token!") String token,
                                @NotBlank(message = "Preencha a senha!") String senha) {
}
