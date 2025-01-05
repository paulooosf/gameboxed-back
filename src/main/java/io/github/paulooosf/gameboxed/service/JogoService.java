package io.github.paulooosf.gameboxed.service;

import io.github.paulooosf.gameboxed.dto.JogoListarDTO;
import io.github.paulooosf.gameboxed.dto.JogoSaidaDTO;
import io.github.paulooosf.gameboxed.model.Jogo;
import io.github.paulooosf.gameboxed.repository.JogoRepository;
import io.github.paulooosf.gameboxed.validation.ValidarJogoExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JogoService {

    @Autowired
    private JogoRepository repository;

    public Page<JogoListarDTO> listar(Pageable pageable) {
        Page<Jogo> jogos = repository.findAll(pageable);
        return jogos.map(JogoListarDTO::new);
    }

    public Optional<JogoSaidaDTO> buscar(Long id) {
        Optional<Jogo> jogoOpt = repository.findById(id);
        ValidarJogoExistente.validar(jogoOpt);
        var dto = new JogoSaidaDTO(jogoOpt.get());
        return Optional.of(dto);
    }

    public Optional<JogoSaidaDTO> editar(Long id, Jogo jogo) {
        Optional<Jogo> jogoOpt = repository.findById(id);
        ValidarJogoExistente.validar(jogoOpt);

        Jogo jogoExistente = jogoOpt.get();
        jogoExistente.editarJogo(jogo);
        jogoExistente = repository.save(jogoExistente);

        return Optional.of(new JogoSaidaDTO(jogoExistente));
    }

    public void deletar(Long id) {
        ValidarJogoExistente.validar(repository.findById(id));
        repository.deleteById(id);
    }
}
