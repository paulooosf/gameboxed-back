package io.github.paulooosf.gameboxed.dto;

import io.github.paulooosf.gameboxed.model.Usuario;
import jakarta.validation.constraints.NotBlank;

public record UsuarioEntradaDTO(@NotBlank(message = "Preencha o apelido!") String apelido,
                                @NotBlank(message = "Preencha o e-mail!") String email,
                                @NotBlank(message = "Preencha a senha!") String senha) {
    public UsuarioEntradaDTO(Usuario usuario) {
        this(usuario.getApelido(), usuario.getEmail(), usuario.getSenha());
    }

    public Usuario converter() {
        return new Usuario(this);
    }
}
