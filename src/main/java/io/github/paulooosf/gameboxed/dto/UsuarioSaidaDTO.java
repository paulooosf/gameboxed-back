package io.github.paulooosf.gameboxed.dto;

import io.github.paulooosf.gameboxed.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioSaidaDTO(@NotNull(message = "ID não preenchido!") Long id,
                              @NotBlank(message = "Apelido não preenchido!") String apelido
                              ) {
    public UsuarioSaidaDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getApelido());
    }
}
