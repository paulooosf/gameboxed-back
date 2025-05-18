package io.github.paulooosf.gameboxed.controller;

import io.github.paulooosf.gameboxed.dto.AvaliacaoEntradaDTO;
import io.github.paulooosf.gameboxed.dto.AvaliacaoSaidaDTO;
import io.github.paulooosf.gameboxed.model.Usuario;
import io.github.paulooosf.gameboxed.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Avaliações", description = "Endpoints para avaliação de jogos")
public class AvaliacaoController {

    private final AvaliacaoService service;

    @Autowired
    public AvaliacaoController(AvaliacaoService service) {
        this.service = service;
    }

    @Operation(summary = "Listar avaliações com paginação")
    @GetMapping("/lista")
    public ResponseEntity<Page<AvaliacaoSaidaDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @Operation(summary = "Buscar uma avaliação por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação encontrada"),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoSaidaDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @Operation(summary = "Criar uma nova avaliação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Avaliação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Sem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor")
    })
    @PostMapping
    public ResponseEntity<AvaliacaoSaidaDTO> avaliar(@Valid @RequestBody AvaliacaoEntradaDTO avaliacao,
                                                     @Parameter(hidden = true) @AuthenticationPrincipal Usuario usuario) {
        AvaliacaoSaidaDTO avaliacaoDTO = service.avaliar(avaliacao, usuario.getId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(avaliacaoDTO.id()).toUri();
        return ResponseEntity.created(uri).body(avaliacaoDTO);
    }

    @Operation(summary = "Editar uma avaliação existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação editada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
            @ApiResponse(responseCode = "403", description = "Sem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor")
    })
    @PutMapping(value = "/editar", params = "id")
    public ResponseEntity<AvaliacaoSaidaDTO> editar(@RequestParam Long id,
                                                    @Valid @RequestBody AvaliacaoEntradaDTO avaliacao,
                                                    @Parameter(hidden = true) @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(service.editar(id, avaliacao, usuario));
    }

    @Operation(summary = "Deletar uma avaliação por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Avaliação deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
            @ApiResponse(responseCode = "403", description = "Sem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor")
    })
    @DeleteMapping(value = "/deletar", params = "id")
    public ResponseEntity<Void> deletar(@RequestParam Long id,
                                        @Parameter(hidden = true) @AuthenticationPrincipal Usuario usuario) {
        service.deletar(id, usuario);
        return ResponseEntity.noContent().build();
    }
}
