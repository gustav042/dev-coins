package com.cingo.moedas_devs.dto;

public class AtualizarMoedasRequest {

    private Integer quantidade;

    // Construtor vazio
    public AtualizarMoedasRequest() {
    }

    // Construtor com parâmetros
    public AtualizarMoedasRequest(Integer quantidade) {
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

