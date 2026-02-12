package com.cingo.moedas_devs.dto;

public class TransferenciaRequest {

    private Long devPagadorId;
    private Long devRecebedorId;

    public TransferenciaRequest() {
    }

    public TransferenciaRequest(Long devPagadorId, Long devRecebedorId) {
        this.devPagadorId = devPagadorId;
        this.devRecebedorId = devRecebedorId;
    }

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