package io.github.paulooosf.gameboxed.repository;

import io.github.paulooosf.gameboxed.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Page<Usuario> findAll(Pageable pageable);

    public Optional<Usuario> findById(Long id);

    public UserDetails findByApelido(String apelido);

    public boolean existsByApelido(String apelido);
}
