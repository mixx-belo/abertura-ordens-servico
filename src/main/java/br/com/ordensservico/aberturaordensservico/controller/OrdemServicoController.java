package br.com.ordensservico.aberturaordensservico.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.service.OrdemServicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public ResponseEntity<OrdemServico> cadastrarOrdemServico(
            @Valid @RequestBody OrdemServicoRequest dto) {

        Optional<OrdemServico> ordemServico = ordemServicoService.cadastrar(dto);
        if (ordemServico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (ordemServico.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ordemServico.get());
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<OrdemServico>> listarOrdensServico() {

        Iterable<OrdemServico> ordensServico = ordemServicoService.listar();

        return ResponseEntity.ok(ordensServico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(
            @PathVariable Integer id) {

        Optional<OrdemServico> ordemServico = ordemServicoService.buscarPorId(id);

        if (ordemServico.isPresent()) {
            return ResponseEntity.ok(ordemServico.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/encerramento")
    public ResponseEntity<OrdemServico> encerrarOrdemServico(
            @PathVariable Integer id,
            @Valid @RequestBody br.com.ordensservico.aberturaordensservico.dto.EncerramentoOrdemServicoRequest dto) {

        Optional<OrdemServico> ordemServico = ordemServicoService.encerrar(id, dto);

        if (ordemServico.isPresent()) {
            return ResponseEntity.ok(ordemServico.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/abertas")
    public ResponseEntity<Iterable<OrdemServico>> listarAbertas() {

        Iterable<OrdemServico> ordensServico = ordemServicoService.listarAbertas();

        return ResponseEntity.ok(ordensServico);
    }
}
