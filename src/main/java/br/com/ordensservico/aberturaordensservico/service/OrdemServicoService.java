package br.com.ordensservico.aberturaordensservico.service;

import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ordensservico.aberturaordensservico.dto.EncerramentoOrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository,
            EquipamentoRepository equipamentoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Optional<OrdemServico> cadastrar(OrdemServicoRequest dto) {
        if (dto.getEquipamentoId() == null) {
            return Optional.empty();
        }

        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(dto.getEquipamentoId());

        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setEquipamento(equipamentoEncontrado.get());
        ordemServico.setDescricao(dto.getDescricao());
        ordemServico.setDataAbertura(LocalDateTime.now());

        return Optional.of(ordemServicoRepository.save(ordemServico));
    }

    public Iterable<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Integer id) {
        return ordemServicoRepository.findById(id);
    }

    public Optional<OrdemServico> encerrar(Integer id, EncerramentoOrdemServicoRequest dto) {
        Optional<OrdemServico> ordemServicoOptional = ordemServicoRepository.findById(id);

        if (ordemServicoOptional.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServico = ordemServicoOptional.get();

        if (ordemServico.getDataEncerramento() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A ordem de serviço " + id + " já foi encerrada.");
        }

        ordemServico.setDataEncerramento(LocalDateTime.now());
        ordemServico.setSolucao(dto.getSolucao());

        return Optional.of(ordemServicoRepository.save(ordemServico));
    }

    public Iterable<OrdemServico> listarAbertas() {
        return ordemServicoRepository.findByDataEncerramentoIsNull();
    }

}
