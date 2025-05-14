package io.github.paulooosf.gameboxed.repository;

import io.github.paulooosf.gameboxed.model.Jogo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

    public Page<Jogo> findAll(Pageable pageable);

    public Page<Jogo> findByNomeContainingIgnoreCase(String titulo, Pageable pageable);

    public Optional<Jogo> findById(Long id);
}
