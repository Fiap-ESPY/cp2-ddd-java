package com.cp2.logistica.domain;

public class EntregadorFactory {

    public static Entregador criarEntregador(
            String tipo,
            String identificador,
            String nome
    ){
        switch (tipo.toLowerCase().trim()){

            case "bicicleta":
                return new EntregadorBicicleta(identificador, nome);

            case "moto":
                return new EntregadorMoto(identificador, nome);

            case "carro":
                return new EntregadorCarro(identificador, nome);

            default:
                throw new IllegalArgumentException("Tipo inválido");
        }
    }
}
