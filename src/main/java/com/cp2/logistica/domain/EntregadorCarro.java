package com.cp2.logistica.domain;

public final class EntregadorCarro extends Entregador {

    public EntregadorCarro(String id, String nome) {
        super(id, nome);
    }

    @Override
    public double capacidadeKg() {
        return 200.0;
    }

    @Override
    public String tipoDescricao() {
        return "Carro";
    }

    @Override
    protected double taxaKm() {
        return 5.5;
    }

    @Override
    protected double custoFixoOperacional() {
        return 12.0;
    }

    @Override
    protected double velocidadeMediaKmPorHora() {
        return 40.0;
    }
}
