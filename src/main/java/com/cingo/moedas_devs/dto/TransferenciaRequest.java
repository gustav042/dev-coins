package com.cingo.moedas_devs.dto;

/**
 * DTO para transferência de moedas entre devs
 */
public class TransferenciaRequest {

    private Long devPagadorId;
    private Long devRecebedorId;

    // Construtor vazio
    public TransferenciaRequest() {
    }

    // Construtor com parâmetros
    public TransferenciaRequest(Long devPagadorId, Long devRecebedorId) {
        this.devPagadorId = devPagadorId;
        this.devRecebedorId = devRecebedorId;
    }

    // Getters e Setters
    public Long getDevPagadorId() {
        return devPagadorId;
    }

    public void setDevPagadorId(Long devPagadorId) {
        this.devPagadorId = devPagadorId;
    }

    public Long getDevRecebedorId() {
        return devRecebedorId;
    }

    public void setDevRecebedorId(Long devRecebedorId) {
        this.devRecebedorId = devRecebedorId;
    }
}