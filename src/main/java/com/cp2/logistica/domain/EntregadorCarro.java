package com.cp2.logistica.domain;

public final class EntregadorCarro extends Entregador {

    public EntregadorCarro(String identificador, String nome) {
        super(identificador, nome);
    }

    @Override
    public double obterCapacidadeKg() {
        return 200.0;
    }

    @Override
    public String obterDescricaoDoTipo() {
        return "Carro";
    }

    @Override
    protected double obterTaxaPorKm() {
        return 5.5;
    }

    @Override
    protected double obterCustoFixoOperacional() {
        return 12.0;
    }

    @Override
    protected double obterVelocidadeMediaKmPorHora() {
        return 40.0;
    }
}
