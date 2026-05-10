package com.cp2.logistica.domain;

public class EntregadorFactory {

    public static Entregador criarEntregador(String tipo, String nome) {
        return switch (tipo.toLowerCase().trim()) {
            case "bicicleta" -> new EntregadorBicicleta(nome);
            case "moto" -> new EntregadorMoto(nome);
            case "carro" -> new EntregadorCarro(nome);
            default -> throw new IllegalArgumentException("Tipo inválido");
        };
    }
}
