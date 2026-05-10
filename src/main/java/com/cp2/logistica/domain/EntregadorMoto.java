package com.cp2.logistica.domain;

public final class EntregadorMoto extends Entregador {

    public EntregadorMoto(String nome) {
        super(nome);
    }

    @Override
    public double getCapacidadeKg() {
        return 20.0;
    }

    @Override
    public String getDescricaoDoTipo() {
        return "Moto";
    }

    @Override
    protected double getTaxaPorKm() {
        return 2.8;
    }

    @Override
    protected double getCustoFixoOperacional() {
        return 4.0;
    }

    @Override
    protected double getVelocidadeMediaKmPorHora() {
        return 35.0;
    }
}
