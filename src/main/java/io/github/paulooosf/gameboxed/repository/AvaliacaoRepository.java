package io.github.paulooosf.gameboxed.repository;

import io.github.paulooosf.gameboxed.model.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    public Page<Avaliacao> findAll(Pageable pageable);

    public Optional<Avaliacao> findById(Long id);
}
