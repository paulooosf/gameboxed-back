package io.github.paulooosf.gameboxed.repository;

import io.github.paulooosf.gameboxed.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findById(Long id);
}
