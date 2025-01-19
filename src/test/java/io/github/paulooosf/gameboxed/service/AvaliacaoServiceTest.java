package io.github.paulooosf.gameboxed.service;

import io.github.paulooosf.gameboxed.dto.AvaliacaoEntradaDTO;
import io.github.paulooosf.gameboxed.dto.AvaliacaoSaidaDTO;
import io.github.paulooosf.gameboxed.exception.NaoEncontradoException;
import io.github.paulooosf.gameboxed.model.Avaliacao;
import io.github.paulooosf.gameboxed.model.Jogo;
import io.github.paulooosf.gameboxed.model.Usuario;
import io.github.paulooosf.gameboxed.repository.AvaliacaoRepository;
import io.github.paulooosf.gameboxed.repository.JogoRepository;
import io.github.paulooosf.gameboxed.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AvaliacaoServiceTest {

    @Mock
    private AvaliacaoRepository repository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JogoRepository jogoRepository;

    @Autowired
    @InjectMocks
    private AvaliacaoService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve listar todas as avaliações cadastradas")
    void listar() {
        Pageable pageable = PageRequest.of(0,10);
        List<Avaliacao> avaliacoes = List.of(this.criarAvaliacao("Avaliação Teste"));

        Page<Avaliacao> pagina = new PageImpl<>(avaliacoes);

        when(repository.findAll(pageable)).thenReturn(pagina);

        Page<AvaliacaoSaidaDTO> resultado = service.listar(pageable);

        assertEquals(1, resultado.getContent().size());
        verify(repository).findAll(pageable);
    }

    void listarPorJogo() {
        // Fazer depois
    }

    @Test
    @DisplayName("Deve encontrar uma avaliação com o id especificado")
    void buscarCaso1() {
        Avaliacao avaliacao = this.criarAvaliacao("Avaliação Teste");

        when(repository.findById(1L)).thenReturn(Optional.of(avaliacao));

        AvaliacaoSaidaDTO resultado = service.buscar(1L);

        assertNotNull(resultado);
        assertEquals("Avaliação Teste", resultado.comentario());
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar uma NaoEncontradoException se for buscada uma avaliação inexistente")
    void buscarCaso2() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            service.buscar(1L);
        });
    }

    @Test
    @DisplayName("Deve registrar uma avaliação com sucesso")
    void avaliarCaso1() {
        var avaliacaoDTO = new AvaliacaoEntradaDTO("Avaliação teste", 5.0, 1L, 1L);
        Avaliacao avaliacao = this.criarAvaliacao("Avaliação Teste");

        when(repository.save(any(Avaliacao.class))).thenReturn(avaliacao);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(avaliacao.getUsuario()));
        when(jogoRepository.findById(1L)).thenReturn(Optional.of(avaliacao.getJogo()));

        AvaliacaoSaidaDTO resultado = service.avaliar(avaliacaoDTO);

        assertNotNull(resultado);
        assertEquals("Avaliação Teste", resultado.comentario());

        verify(usuarioRepository).findById(avaliacaoDTO.idUsuario());
        verify(jogoRepository).findById(avaliacaoDTO.idJogo());
        verify(jogoRepository).save(any(Jogo.class));
        verify(repository).save(any(Avaliacao.class));
    }

    @Test
    @DisplayName("Deve atualizar a nota do jogo avaliado com sucesso")
    void avaliarCaso2() {
        var avaliacaoDTO = new AvaliacaoEntradaDTO("Avaliação teste", 5.0, 1L, 1L);
        Avaliacao avaliacao = this.criarAvaliacao("Avaliação Teste");
        Jogo jogo;

        when(repository.save(any(Avaliacao.class))).thenReturn(avaliacao);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(avaliacao.getUsuario()));
        when(jogoRepository.findById(1L)).thenReturn(Optional.of(avaliacao.getJogo()));

        service.avaliar(avaliacaoDTO);
        jogo = jogoRepository.findById(1L).get();
        assertEquals(5.0, jogo.getNota());

        var avaliacaoDTO2 = new AvaliacaoEntradaDTO("Avaliação teste", 2.5, 1L, 1L);
        service.avaliar(avaliacaoDTO2);
        jogo = jogoRepository.findById(1L).get();
        assertEquals(3.75, jogo.getNota());
    }

    @Test
    @DisplayName("Deve editar uma avaliação com sucesso")
    void editarCaso1() {
        Avaliacao avaliacaoExistente = this.criarAvaliacao("Avaliação Velha");
        Avaliacao avaliacaoAtualizada = this.criarAvaliacao("Avaliação Atualizada");

        when(repository.findById(1L)).thenReturn(Optional.of(avaliacaoExistente));
        when(repository.save(any(Avaliacao.class))).thenReturn(avaliacaoAtualizada);

        AvaliacaoSaidaDTO resultado = service.editar(1L, avaliacaoExistente);

        assertEquals("Avaliação Atualizada", resultado.comentario());
        verify(repository).save(any(Avaliacao.class));
    }

    @Test
    @DisplayName("Deve lançar uma NaoEncontradoException se for editada uma avaliação inexistente")
    void editarCaso2() {
        Avaliacao avaliacao = this.criarAvaliacao("Avaliação Teste");

        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            service.editar(99L, avaliacao);
        });
    }

    @Test
    @DisplayName("Deve deletar uma avaliação com sucesso")
    void deletarCaso1() {
        Avaliacao avaliacao = this.criarAvaliacao("Avaliação Teste");
        when(repository.findById(1L)).thenReturn(Optional.of(avaliacao));

        service.deletar(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar uma NaoEncontradoException se for deletada uma avaliação inexistente")
    void deletarCaso2() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            service.deletar(1L);
        });
    }

    private Avaliacao criarAvaliacao(String comentario) {
        return new Avaliacao(1L, comentario, 5.0,
                criarUsuario(), criarJogo());
    }

    private Usuario criarUsuario() {
        return new Usuario(1L, "Usuario Teste", "teste@teste.com.br", "senha");
    }

    private Jogo criarJogo() {
        return new Jogo(1L, "Jogo Teste", "1", "1", 2025,
                "Teste", "Teste", "Teste");
    }
}