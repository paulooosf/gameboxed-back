package io.github.paulooosf.gameboxed.service;

import io.github.paulooosf.gameboxed.dto.JogoEntradaDTO;
import io.github.paulooosf.gameboxed.dto.JogoListarDTO;
import io.github.paulooosf.gameboxed.dto.JogoSaidaDTO;
import io.github.paulooosf.gameboxed.exception.NaoEncontradoException;
import io.github.paulooosf.gameboxed.model.Jogo;
import io.github.paulooosf.gameboxed.repository.JogoRepository;
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
import static org.mockito.Mockito.*;

class JogoServiceTest {

    @Mock
    private JogoRepository repository;

    @Autowired
    @InjectMocks
    private JogoService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve listar todos os jogos cadastrados")
    void listar() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Jogo> jogos = List.of(this.criarJogo("Jogo Teste"));

        Page<Jogo> pagina = new PageImpl<>(jogos);

        when(repository.findAll(pageable)).thenReturn(pagina);

        Page<JogoListarDTO> resultado = service.listar(pageable);

        assertEquals(1, resultado.getContent().size());
        verify(repository).findAll(pageable);
    }

    @Test
    @DisplayName("Deve encontrar um jogo com o id especificado")
    void buscarCaso1() {
        Jogo jogo = this.criarJogo("Jogo Teste");

        when(repository.findById(1L)).thenReturn(Optional.of(jogo));

        JogoSaidaDTO resultado = service.buscar(1L);

        assertNotNull(resultado);
        assertEquals("Jogo Teste", resultado.nome());
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar uma NaoEncontradoException se for buscado um jogo inexistente")
    void buscarCaso2() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            service.buscar(1L);
        });
    }

    @Test
    @DisplayName("Deve cadastrar um jogo com sucesso")
    void cadastrar() {
        var jogoDTO = new JogoEntradaDTO("Jogo Teste", "1", "1", 2025,
                "Teste", "Teste", "Teste");
        Jogo jogo = this.criarJogo("Jogo Teste");

        when(repository.save(any(Jogo.class))).thenReturn(jogo);

        JogoSaidaDTO resultado = service.cadastrar(jogoDTO);

        assertNotNull(resultado);
        assertEquals("Jogo Teste", resultado.nome());
        verify(repository).save(any(Jogo.class));
    }

    @Test
    @DisplayName("Deve editar um jogo com sucesso")
    void editarCaso1() {
        Jogo jogoExistente = this.criarJogo("Jogo Velho");
        Jogo jogoAtualizado = this.criarJogo("Jogo Atualizado");

        when(repository.findById(1L)).thenReturn(Optional.of(jogoExistente));
        when(repository.save(any(Jogo.class))).thenReturn(jogoAtualizado);

        JogoSaidaDTO resultado = service.editar(1L, jogoExistente);

        assertEquals("Jogo Atualizado", resultado.nome());
        verify(repository).save(any(Jogo.class));
    }

    @Test
    @DisplayName("Deve lançar uma NaoEncontradoException se for editado um jogo inexistente")
    void editarCaso2() {
        Jogo jogo = this.criarJogo("Jogo Teste");

        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            service.editar(99L, jogo);
        });
    }

    @Test
    @DisplayName("Deve deletar um jogo com sucesso")
    void deletarCaso1() {
        Jogo jogo = this.criarJogo("Jogo Teste");
        when(repository.findById(1L)).thenReturn(Optional.of(jogo));

        service.deletar(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar uma NaoEncontradoException se for deletado um jogo inexistente")
    void deletarCaso2() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            service.buscar(1L);
        });
    }

    private Jogo criarJogo(String nome) {
        return new Jogo(1L, nome, "1", "1", 2025,
                "Teste", "Teste", "Teste");
    }
}