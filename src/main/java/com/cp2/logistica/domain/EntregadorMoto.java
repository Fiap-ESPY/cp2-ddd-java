package com.cp2.logistica.domain;

public final class EntregadorMoto extends Entregador {

    public EntregadorMoto(String identificador, String nome) {
        super(identificador, nome);
    }

    @Override
    public double obterCapacidadeKg() {
        return 20.0;
    }

    @Override
    public String obterDescricaoDoTipo() {
        return "Moto";
    }

    @Override
    protected double obterTaxaPorKm() {
        return 2.8;
    }

    @Override
    protected double obterCustoFixoOperacional() {
        return 4.0;
    }

    @Override
    protected double obterVelocidadeMediaKmPorHora() {
        return 35.0;
    }
}
