package com.cp2.logistica.domain;

public abstract class Entregador implements TarifaPorDistancia {

    private final String id;
    private final String nome;

    protected Entregador(String identificador, String nome) {
        this.id = identificador;
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
            System.out.println("Distância deve ser positiva.");
            return 0.0;
        }
        return quilometros * this.taxaKm() + this.custoFixoOperacional();
    }

    public abstract double capacidadeKg();

    public abstract String tipoDescricao();

    protected abstract double taxaKm();

    protected abstract double custoFixoOperacional();

    protected double velocidadeMediaKmPorHora() {
        return 24.0;
    }

    public double tempoEstimadoHorasPara(double quilometros) {
        if (quilometros <= 0) {
            System.out.println("Distância deve ser positiva para estimativa.");
            return 0.0;
        }
        double velocidadeKmPorHora = this.velocidadeMediaKmPorHora();
        if (velocidadeKmPorHora <= 0) {
            System.out.println("Velocidade média inválida.");
            return 0.0;
        }
        return quilometros / velocidadeKmPorHora;
    }

    @Override
    public String toString() {
        return this.tipoDescricao() + " — " + this.nome + " (" + this.id + ")";
    }
}
