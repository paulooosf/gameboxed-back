package io.github.paulooosf.gameboxed.service;

import io.github.paulooosf.gameboxed.dto.JogoEntradaDTO;
import io.github.paulooosf.gameboxed.dto.JogoListarDTO;
import io.github.paulooosf.gameboxed.dto.JogoSaidaDTO;
import io.github.paulooosf.gameboxed.model.Jogo;
import io.github.paulooosf.gameboxed.repository.JogoRepository;
import io.github.paulooosf.gameboxed.validation.ValidarJogoExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JogoService {

    private final JogoRepository repository;

    @Autowired
    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "jogos")
    public Page<JogoListarDTO> listar(String busca, Pageable pageable) {
        Page<Jogo> jogos;
        if (busca != null && !busca.isBlank()) {
            jogos = repository.findByNomeContainingIgnoreCase(busca, pageable);
        } else {
            jogos = repository.findAll(pageable);
        }
        return jogos.map(JogoListarDTO::new);
    }

    @Cacheable(value = "jogo", key = "#id")
    public JogoSaidaDTO buscar(Long id) {
        Optional<Jogo> jogoOpt = repository.findById(id);
        ValidarJogoExistente.validar(jogoOpt);
        return new JogoSaidaDTO(jogoOpt.get());
    }

    public JogoSaidaDTO cadastrar(JogoEntradaDTO jogoDTO) {
        Jogo jogo = repository.save(jogoDTO.converter());
        return new JogoSaidaDTO(jogo);
    }

    @CacheEvict(value = "jogo", key = "#id")
    public JogoSaidaDTO editar(Long id, Jogo jogo) {
        ValidarJogoExistente.validar(repository.findById(id));
        jogo.setId(id);
        return new JogoSaidaDTO(repository.save(jogo));
    }

    @CacheEvict(value = "jogo", key = "#id")
    public void deletar(Long id) {
        ValidarJogoExistente.validar(repository.findById(id));
        repository.deleteById(id);
    }
}
