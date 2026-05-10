package com.cp2.logistica.domain;

import java.util.Random;

public abstract class Entregador implements TarifaPorDistancia {

    private final Long id = Math.abs(new Random().nextLong());

    private final String nome;

    protected Entregador(String nome) {
        this.nome = nome;
    }

    public final Long getId() {
        return id;
    }

    public final String getNome() {
        return nome;
    }

    @Override
    public final double calcularEntrega(double quilometros) {
        if (quilometros <= 0) {
            System.out.println("Distância deve ser positiva.");
            return 0.0;
        }
        return quilometros * this.getTaxaPorKm() + this.getCustoFixoOperacional();
    }

    public abstract double getCapacidadeKg();

    public abstract String getDescricaoDoTipo();

    protected abstract double getTaxaPorKm();

    protected abstract double getCustoFixoOperacional();

    protected double getVelocidadeMediaKmPorHora() {
        return 24.0;
    }

    public double calcularTempoEstimado(double quilometros) {
        if (quilometros <= 0) {
            System.out.println("Distância deve ser positiva para estimativa.");
            return 0.0;
        }

        double velocidadeKmPorHora = this.getVelocidadeMediaKmPorHora();
        if (velocidadeKmPorHora <= 0) {
            System.out.println("Velocidade média inválida.");
            return 0.0;
        }
        return quilometros / velocidadeKmPorHora;
    }

    @Override
    public String toString() {
        String resumoFormatado = """
                ID:                       %s
                Nome:                     %s
                Tipo:                     %s
                Capacidade (kg):          %.2f
                Velocidade média (km/h):  %.1f
                """;
        return resumoFormatado.formatted(
                this.id,
                this.nome,
                this.getDescricaoDoTipo(),
                this.getCapacidadeKg(),
                this.getVelocidadeMediaKmPorHora());
    }
}
