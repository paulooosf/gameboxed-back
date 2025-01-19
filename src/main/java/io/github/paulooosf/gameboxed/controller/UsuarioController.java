package io.github.paulooosf.gameboxed.controller;

import io.github.paulooosf.gameboxed.dto.UsuarioEntradaDTO;
import io.github.paulooosf.gameboxed.dto.UsuarioSaidaDTO;
import io.github.paulooosf.gameboxed.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<UsuarioSaidaDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioSaidaDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioSaidaDTO> cadastrar(@Valid @RequestBody UsuarioEntradaDTO usuario) {
        UsuarioSaidaDTO usuarioDTO = service.cadastrar(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.id()).toUri();
        return ResponseEntity.created(uri).body(usuarioDTO);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<UsuarioSaidaDTO> editar(@PathVariable Long id, @Valid @RequestBody UsuarioEntradaDTO usuario) {
        return ResponseEntity.ok(service.editar(id, usuario.converter()));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
