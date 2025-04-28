package io.github.paulooosf.gameboxed.controller;

import io.github.paulooosf.gameboxed.dto.JogoEntradaDTO;
import io.github.paulooosf.gameboxed.dto.JogoListarDTO;
import io.github.paulooosf.gameboxed.dto.JogoSaidaDTO;
import io.github.paulooosf.gameboxed.service.JogoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    private final JogoService service;

    @Autowired
    public JogoController(JogoService service) {
        this.service = service;
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<JogoListarDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoSaidaDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<JogoSaidaDTO> cadastrar(@Valid @RequestBody JogoEntradaDTO jogo) {
        JogoSaidaDTO jogoDTO = service.cadastrar(jogo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(jogoDTO.id()).toUri();
        return ResponseEntity.created(uri).body(jogoDTO);
    }

    @PutMapping(value = "/editar", params = "id")
    public ResponseEntity<JogoSaidaDTO> editar(@RequestParam Long id, @Valid @RequestBody JogoEntradaDTO jogo) {
        return ResponseEntity.ok(service.editar(id, jogo.converter()));
    }

    @DeleteMapping(value = "/deletar", params = "id")
    public ResponseEntity<Void> deletar(@RequestParam Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
