package com.cp2.logistica.domain;

public final class EntregadorBicicleta extends Entregador {

    public EntregadorBicicleta(String identificador, String nome) {
        super(identificador, nome);
    }

    @Override
    public double capacidadeKg() {
        return 8.0;
    }

    @Override
    public String tipoDescricao() {
        return "Bicicleta";
    }

    @Override
    protected double taxaKm() {
        return 1.2;
    }

    @Override
    protected double custoFixoOperacional() {
        return 2.5;
    }

    @Override
    protected double velocidadeMediaKmPorHora() {
        return 12.0;
    }
}
