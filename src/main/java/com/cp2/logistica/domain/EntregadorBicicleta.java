package com.cp2.logistica.domain;

public final class EntregadorBicicleta extends Entregador {

    public EntregadorBicicleta(String nome) {
        super(nome);
    }

    @Override
    public double getCapacidadeKg() {
        return 8.0;
    }

    @Override
    public String getDescricaoDoTipo() {
        return "Bicicleta";
    }

    @Override
    protected double getTaxaPorKm() {
        return 1.2;
    }

    @Override
    protected double getCustoFixoOperacional() {
        return 2.5;
    }

    @Override
    protected double getVelocidadeMediaKmPorHora() {
        return 12.0;
    }
}
