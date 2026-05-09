package com.cp2.logistica.domain;

public final class EntregadorMoto extends Entregador {

    public EntregadorMoto(String identificador, String nome) {
        super(identificador, nome);
    }

    @Override
    public double capacidadeKg() {
        return 20.0;
    }

    @Override
    public String tipoDescricao() {
        return "Moto";
    }

    @Override
    protected double taxaKm() {
        return 2.8;
    }

    @Override
    protected double custoFixoOperacional() {
        return 4.0;
    }

    @Override
    protected double velocidadeMediaKmPorHora() {
        return 35.0;
    }
}
