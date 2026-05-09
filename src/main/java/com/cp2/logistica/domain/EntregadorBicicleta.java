package com.cp2.logistica.domain;

public final class EntregadorBicicleta extends Entregador {

    public EntregadorBicicleta(String identificador, String nome) {
        super(identificador, nome);
    }

    @Override
    public double obterCapacidadeKg() {
        return 8.0;
    }

    @Override
    public String obterDescricaoDoTipo() {
        return "Bicicleta";
    }

    @Override
    protected double obterTaxaPorKm() {
        return 1.2;
    }

    @Override
    protected double obterCustoFixoOperacional() {
        return 2.5;
    }

    @Override
    protected double obterVelocidadeMediaKmPorHora() {
        return 12.0;
    }
}
