package br.com.ordensservico.aberturaordensservico.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity 
@Table (name = "ordem_servico")
public class OrdemServico {
     @Id 
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "A descrição da ordem de serviço é obrigatória")
    private String descricao;


    private LocalDateTime dataAbertura;

    @ManyToOne (optional = false)
    @JoinColumn (name = "equipamento_id", nullable = false)
    private Equipamento equipamento;

    public OrdemServico() {
    }

    public OrdemServico(String descricao, LocalDateTime dataAbertura, Equipamento equipamento) {
        this.descricao = descricao;
        this.dataAbertura = dataAbertura;
        this.equipamento = equipamento;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
}
