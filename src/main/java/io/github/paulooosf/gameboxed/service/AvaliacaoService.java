package io.github.paulooosf.gameboxed.service;

import io.github.paulooosf.gameboxed.dto.AvaliacaoEntradaDTO;
import io.github.paulooosf.gameboxed.dto.AvaliacaoSaidaDTO;
import io.github.paulooosf.gameboxed.model.Avaliacao;
import io.github.paulooosf.gameboxed.model.Jogo;
import io.github.paulooosf.gameboxed.model.Usuario;
import io.github.paulooosf.gameboxed.repository.AvaliacaoRepository;
import io.github.paulooosf.gameboxed.repository.JogoRepository;
import io.github.paulooosf.gameboxed.repository.UsuarioRepository;
import io.github.paulooosf.gameboxed.validation.ValidarAvaliacaoExistente;
import io.github.paulooosf.gameboxed.validation.ValidarJogoExistente;
import io.github.paulooosf.gameboxed.validation.ValidarUsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JogoRepository jogoRepository;

    public Page<AvaliacaoSaidaDTO> listar(Pageable pageable) {
        Page<Avaliacao> avaliacoes = repository.findAll(pageable);
        return avaliacoes.map(AvaliacaoSaidaDTO::new);
    }

    public AvaliacaoSaidaDTO buscar(Long id) {
        Optional<Avaliacao> avaliacaoOpt = repository.findById(id);
        ValidarAvaliacaoExistente.validar(avaliacaoOpt);
        return new AvaliacaoSaidaDTO(avaliacaoOpt.get());
    }

    public AvaliacaoSaidaDTO avaliar(AvaliacaoEntradaDTO avaliacaoDTO) {
        var usuarioOpt = usuarioRepository.findById(avaliacaoDTO.idUsuario());
        ValidarUsuarioExistente.validar(usuarioOpt);
        Usuario usuario = usuarioOpt.get();

        var jogoOpt = jogoRepository.findById(avaliacaoDTO.idJogo());
        ValidarJogoExistente.validar(jogoOpt);
        Jogo jogo = jogoOpt.get();
        jogo.atualizarNota(avaliacaoDTO.nota());
        jogoRepository.save(jogo);

        Avaliacao avaliacao = avaliacaoDTO.converter();
        avaliacao.setUsuario(usuario);
        avaliacao.setJogo(jogo);

        return new AvaliacaoSaidaDTO(repository.save(avaliacao));
    }

    public AvaliacaoSaidaDTO editar(Long id, Avaliacao avaliacao) {
        ValidarAvaliacaoExistente.validar(repository.findById(id));
        avaliacao.setId(id);
        return new AvaliacaoSaidaDTO(repository.save(avaliacao));
    }

    public void deletar(Long id) {
        ValidarAvaliacaoExistente.validar(repository.findById(id));
        repository.deleteById(id);
    }
}
