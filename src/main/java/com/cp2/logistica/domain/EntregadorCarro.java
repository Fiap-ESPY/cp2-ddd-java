package com.cp2.logistica.domain;

public final class EntregadorCarro extends Entregador {

    public EntregadorCarro(String identificador, String nome) {
        super(identificador, nome);
    }

    @Override
    public double getCapacidadeKg() {
        return 200.0;
    }

    @Override
    public String getDescricaoDoTipo() {
        return "Carro";
    }

    @Override
    protected double getTaxaPorKm() {
        return 5.5;
    }

    @Override
    protected double getCustoFixoOperacional() {
        return 12.0;
    }

    @Override
    protected double getVelocidadeMediaKmPorHora() {
        return 40.0;
    }
}
