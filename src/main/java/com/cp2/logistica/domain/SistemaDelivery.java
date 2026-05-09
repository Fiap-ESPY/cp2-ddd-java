package com.cp2.logistica.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaDelivery {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Entregador> entregadores = new ArrayList();

    public void cadastrarEntregador(){
        System.out.println("Digite o nome do Entregador");
        String nome = scanner.nextLine().trim();

        System.out.println("Digite o identificador do Entregador");
        String id = scanner.nextLine().trim();

        System.out.println("Qual o veiculo de entrega?");
        String tipo = scanner.nextLine().trim();

        Entregador entregador = EntregadorFactory.criarEntregador(tipo, nome, id);

        entregadores.add(entregador);

        System.out.println(entregador);
    }


}
