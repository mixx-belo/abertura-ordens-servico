package br.com.ordensservico.aberturaordensservico.service;

import br.com.ordensservico.aberturaordensservico.repository.OrdemServicoRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;

@Service 
public class OrdemServicoService {
    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, EquipamentoRepository equipamentoRepository) {
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


}
