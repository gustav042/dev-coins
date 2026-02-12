package com.cingo.moedas_devs.model;

import jakarta.persistence.*;

@Entity
@Table(name = "devs")
public class Dev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento
    private Long id;

    @Column(nullable = false) // Campo obrigatório
    private String nome;

    @Column(nullable = false)
    private Integer moedas;

    @Column(nullable = false)
    private Integer batataQuente;

    public Dev() {
        this.moedas = 0;
        this.batataQuente = 0;
    }

    public Dev(String nome, Integer moedas) {
        this.nome = nome;
        this.moedas = moedas;
        this.batataQuente = 0;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMoedas() {
        return moedas;
    }

    public void setMoedas(Integer moedas) {
        this.moedas = moedas;
    }

    public Integer getBatataQuente() {
        return batataQuente;
    }

    public void setBatataQuente(Integer batataQuente) {
        this.batataQuente = batataQuente;
    }

    public void adicionarMoeda() {
        this.moedas += 1;
    }

    public void removerMoeda() {
        this.moedas -= 1;
    }

    public void incrementarBatataQuente() {
        this.batataQuente++;
    }
}