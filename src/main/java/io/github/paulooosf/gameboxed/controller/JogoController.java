package io.github.paulooosf.gameboxed.controller;

import io.github.paulooosf.gameboxed.dto.JogoEntradaDTO;
import io.github.paulooosf.gameboxed.dto.JogoListarDTO;
import io.github.paulooosf.gameboxed.dto.JogoSaidaDTO;
import io.github.paulooosf.gameboxed.service.JogoService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/jogos")
@Tag(name = "Jogos", description = "Endpoints para manipulação e exibição de jogos")
public class JogoController {

    private final JogoService service;

    @Autowired
    public JogoController(JogoService service) {
        this.service = service;
    }

    @Operation(summary = "Listar jogos com filtro e paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogos listados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor")
    })
    @GetMapping("/lista")
    public ResponseEntity<Page<JogoListarDTO>> listar(
            @Parameter(description = "Texto de busca opcional") @RequestParam(required = false) String busca,
            @Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(service.listar(busca, pageable));
    }

    @Operation(summary = "Buscar jogo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogo encontrado"),
            @ApiResponse(responseCode = "404", description = "Jogo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<JogoSaidaDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @Operation(summary = "Cadastrar novo jogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Jogo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor")
    })
    @PostMapping
    public ResponseEntity<JogoSaidaDTO> cadastrar(@Valid @RequestBody JogoEntradaDTO jogo) {
        JogoSaidaDTO jogoDTO = service.cadastrar(jogo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(jogoDTO.id()).toUri();
        return ResponseEntity.created(uri).body(jogoDTO);
    }

    @Operation(summary = "Editar um jogo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogo editado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Jogo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor")
    })
    @PutMapping(value = "/editar", params = "id")
    public ResponseEntity<JogoSaidaDTO> editar(@RequestParam Long id, @Valid @RequestBody JogoEntradaDTO jogo) {
        return ResponseEntity.ok(service.editar(id, jogo.converter()));
    }

    @Operation(summary = "Deletar um jogo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Jogo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Jogo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor")
    })
    @DeleteMapping(value = "/deletar", params = "id")
    public ResponseEntity<Void> deletar(@RequestParam Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
