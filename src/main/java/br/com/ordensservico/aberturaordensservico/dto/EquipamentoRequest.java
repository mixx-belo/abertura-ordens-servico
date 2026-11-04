package br.com.ordensservico.aberturaordensservico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EquipamentoRequest {

    @NotBlank (message = "O nome é obrigatório.")
    private String nome;

    @NotBlank (message = "O número do patrimônio é obrigatório.")
    private String numeroPatrimonio;

    @NotNull (message = "O setor é obrigatório.")
    private Integer setorId;

    public EquipamentoRequest(String nome, String numeroPatrimonio, Integer setorId) {
        this.nome = nome;
        this.numeroPatrimonio = numeroPatrimonio;
        this.setorId = setorId;
    }

    public EquipamentoRequest() {
    }

    public void setSetorId(Integer setorId) {
        this.setorId = setorId;
    }

    public Integer getSetorId() {
        return setorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }


}
