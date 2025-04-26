package io.github.paulooosf.gameboxed.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(@NotBlank(message = "Preencha o apelido!") String apelido,
                       @NotBlank(message = "Preencha a senha!") String senha
                       ) {
}
