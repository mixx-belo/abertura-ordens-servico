package br.com.ordensservico.aberturaordensservico.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;

@Service 
public class EquipamentoService {
    
    private final EquipamentoRepository equipamentoRepository;
    private final SetorRepository setorRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository, SetorRepository setorRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }

    public Optional<Equipamento> cadastrar(Integer setorId, String nome, String numeroPatrimonio) {

        Optional<Setor> setor = setorRepository.findById(setorId);

        if (setor.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = new Equipamento();
        equipamento.setSetor(setor.get());
        equipamento.setNome(nome);
        equipamento.setNumeroPatrimonio(numeroPatrimonio);

        return Optional.of(equipamentoRepository.save(equipamento));
    }

    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public Optional<Equipamento> atualizar(Integer id, Integer setorId, String nome, String numeroPatrimonio) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(id);

        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Optional<Setor> setor = setorRepository.findById(setorId);

        if (setor.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = equipamentoEncontrado.get();
        equipamento.setSetor(setor.get());
        equipamento.setNome(nome);
        equipamento.setNumeroPatrimonio(numeroPatrimonio);

        return Optional.of(equipamentoRepository.save(equipamento));
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
