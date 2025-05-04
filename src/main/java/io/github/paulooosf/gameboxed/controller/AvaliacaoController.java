package io.github.paulooosf.gameboxed.controller;

import io.github.paulooosf.gameboxed.dto.AvaliacaoEntradaDTO;
import io.github.paulooosf.gameboxed.dto.AvaliacaoSaidaDTO;
import io.github.paulooosf.gameboxed.model.Usuario;
import io.github.paulooosf.gameboxed.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService service;

    @Autowired
    public AvaliacaoController(AvaliacaoService service) {
        this.service = service;
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<AvaliacaoSaidaDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoSaidaDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoSaidaDTO> avaliar(@Valid @RequestBody AvaliacaoEntradaDTO avaliacao,
                                                    @AuthenticationPrincipal Usuario usuario) {
        AvaliacaoSaidaDTO avaliacaoDTO = service.avaliar(avaliacao, usuario.getId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(avaliacaoDTO.id()).toUri();
        return ResponseEntity.created(uri).body(avaliacaoDTO);
    }

    @PutMapping(value = "/editar", params = "id")
    public ResponseEntity<AvaliacaoSaidaDTO> editar(@RequestParam Long id,
                                                    @Valid @RequestBody AvaliacaoEntradaDTO avaliacao,
                                                    @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(service.editar(id, avaliacao, usuario));
    }

    @DeleteMapping(value = "/deletar", params = "id")
    public ResponseEntity<Void> deletar(@RequestParam Long id, @AuthenticationPrincipal Usuario usuario) {
        service.deletar(id, usuario);
        return ResponseEntity.noContent().build();
    }
}
