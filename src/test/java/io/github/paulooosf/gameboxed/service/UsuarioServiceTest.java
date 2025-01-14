package io.github.paulooosf.gameboxed.service;

import io.github.paulooosf.gameboxed.dto.UsuarioEntradaDTO;
import io.github.paulooosf.gameboxed.dto.UsuarioSaidaDTO;
import io.github.paulooosf.gameboxed.exception.NaoEncontradoException;
import io.github.paulooosf.gameboxed.model.Usuario;
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

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Autowired
    @InjectMocks
    private UsuarioService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve listar todos os usuários cadastrados")
    void listar() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Usuario> usuarios = List.of(this.criarUsuario("UsuarioTeste"));

        Page<Usuario> pagina = new PageImpl<>(usuarios);

        when(repository.findAll(pageable)).thenReturn(pagina);

        Page<UsuarioSaidaDTO> resultado = service.listar(pageable);

        assertEquals(1, resultado.getContent().size());
        verify(repository).findAll(pageable);
    }

    @Test
    @DisplayName("Deve encontrar um usuário com o id especificado")
    void buscarCaso1() {
        Usuario usuario = this.criarUsuario("UsuarioTeste");

        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioSaidaDTO resultado = service.buscar(1L);

        assertNotNull(resultado);
        assertEquals("UsuarioTeste", resultado.apelido());
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar uma NaoEncontradoException se for buscado um usuário inexistente")
    void buscarCaso2() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            service.buscar(1L);
        });
    }

    @Test
    @DisplayName("Deve cadastrar um usuário com sucesso")
    void cadastrar() {
        var usuarioDTO = new UsuarioEntradaDTO("UsuarioTeste", "email@email.com", "senha");
        Usuario usuario = this.criarUsuario("UsuarioTeste");

        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioSaidaDTO resultado = service.cadastrar(usuarioDTO);

        assertNotNull(resultado);
        assertEquals("UsuarioTeste", resultado.apelido());
        verify(repository).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve editar um usuário com sucesso")
    void editarCaso1() {
        Usuario usuarioExistente = this.criarUsuario("UsuarioVelho");
        Usuario usuarioAtualizado = this.criarUsuario("UsuarioAtualizado");

        when(repository.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(repository.save(any(Usuario.class))).thenReturn(usuarioAtualizado);

        UsuarioSaidaDTO resultado = service.editar(1L, usuarioExistente);

        assertEquals("UsuarioAtualizado", resultado.apelido());
        verify(repository).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve lançar uma NaoEncontradoException se for editado um usuário inexistente")
    void editarCaso2() {
        Usuario usuario = this.criarUsuario("UsuarioTeste");

        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            service.editar(99L, usuario);
        });
    }

    @Test
    @DisplayName("Deve deletar um usuário com sucesso")
    void deletarCaso1() {
        Usuario usuario = this.criarUsuario("UsuarioTeste");
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        service.deletar(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar uma NaoEncontradoException se for deletado um usuário inexistente")
    void deletarCaso2() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            service.buscar(1L);
        });
    }

    private Usuario criarUsuario(String apelido) {
        return new Usuario(1L, apelido, "teste@teste.com.br", "senha");
    }
}