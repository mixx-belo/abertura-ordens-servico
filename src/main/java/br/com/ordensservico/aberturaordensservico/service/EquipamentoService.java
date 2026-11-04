package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;
import br.com.ordensservico.aberturaordensservico.dto.EquipamentoRequest;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final SetorRepository setorRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository, SetorRepository setorRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }


    public Optional<Equipamento> cadastrar(EquipamentoRequest dto) {
    if (dto.getSetorId() == null) {
        return Optional.empty();
    }

    Optional<Setor> setorEncontrado = setorRepository.findById(dto.getSetorId());

    if (setorEncontrado.isEmpty()) {
        return Optional.empty();
    }

    Equipamento equipamento = new Equipamento();
    
    equipamento.setSetor(setorEncontrado.get());
    equipamento.setNome(dto.getNome());
    equipamento.setNumeroPatrimonio(dto.getNumeroPatrimonio());

    return Optional.of(equipamentoRepository.save(equipamento));
}

public Optional<Equipamento> atualizar(Integer id, EquipamentoRequest dto) {
    Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(id);

    if (equipamentoEncontrado.isEmpty() || dto.getSetorId() == null) {
        return Optional.empty();
    }

    Optional<Setor> setorEncontrado = setorRepository.findById(dto.getSetorId());

    if (setorEncontrado.isEmpty()) {
        return Optional.empty();
    }

    Equipamento equipamento = equipamentoEncontrado.get();
    equipamento.setSetor(setorEncontrado.get());
    equipamento.setNome(dto.getNome());
    equipamento.setNumeroPatrimonio(dto.getNumeroPatrimonio());

    return Optional.of(equipamentoRepository.save(equipamento));
}
    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public boolean excluir(Integer id) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(id);

        if (equipamentoEncontrado.isEmpty()) {
            return false;
        }

        equipamentoRepository.delete(equipamentoEncontrado.get());
        return true;
    }

}
