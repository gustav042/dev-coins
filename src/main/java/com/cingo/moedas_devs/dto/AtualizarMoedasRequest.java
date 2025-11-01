package com.cingo.moedas_devs.dto;

/**
 * DTO (Data Transfer Object) - Objeto usado para transferir dados
 * Usado para receber a quantidade de moedas a adicionar/remover
 */
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

