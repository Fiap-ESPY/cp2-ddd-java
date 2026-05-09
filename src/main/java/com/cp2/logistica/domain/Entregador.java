package com.cp2.logistica.domain;

public abstract class Entregador implements TarifaPorDistancia {

    private final String id;
    private final String nome;

    protected Entregador(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public final String getId() {
        return id;
    }

    public final String getNome() {
        return nome;
    }

    @Override
    public final double valorPara(double quilometros) {
        if (quilometros <= 0) {
            throw new IllegalArgumentException("Distância deve ser positiva.");
        }
        return quilometros * taxaKm() + custoFixoOperacional();
    }

    public abstract double capacidadeKg();

    public abstract String tipoDescricao();

    protected abstract double taxaKm();

    protected abstract double custoFixoOperacional();

    protected double velocidadeMediaKmPorHora() {
        return 24.0;
    }

    public double tempoEstimadoHorasPara(double quilometros) {
        return quilometros / velocidadeMediaKmPorHora();
    }

    @Override
    public String toString() {
        return tipoDescricao() + " — " + nome + " (" + id + ")";
    }
}
