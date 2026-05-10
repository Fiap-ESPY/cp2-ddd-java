package com.cp2.logistica.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SistemaDelivery {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Entregador> entregadores = new ArrayList<>();
    private final List<Entrega> entregas = new ArrayList<>();

    public void cadastrarEntregador() {
        System.out.println("Digite o nome do Entregador:");
        String nome = scanner.nextLine().trim();

        System.out.println("Qual o veiculo de entrega?");
        String tipoVeiculo = scanner.nextLine().trim();

        Entregador entregador = EntregadorFactory.criarEntregador(tipoVeiculo, nome);

        entregadores.add(entregador);

        System.out.println(entregador);
    }

    public void cadastrarEntrega() {
        System.out.println("Digite o endereço da Entrega");
        String enderecoDestino = scanner.nextLine().trim();

        Entrega entrega = new Entrega(enderecoDestino);

        entregas.add(entrega);

        if (!entregadores.isEmpty()) {
            entrega.registrarEntrega(atribuirEntregador());
        }

        System.out.println(entrega);

    }

    public void atribuirEntregadorEntrega() {
        if (entregas.isEmpty()) {
            System.out.println("Nenhuma entrega cadastrada.");
            return;
        }

        if (entregadores.isEmpty()) {
            System.out.println("Nenhum entregador disponível.");
            return;
        }

        System.out.println("=======Entregas Disponíveis ==========");

        for (int i = 0; i < entregas.size(); i++) {
            System.out.println(i + " - " + entregas.get(i));
        }

        System.out.println("Escolha a entrega:");
        int indiceEntrega = Integer.parseInt(scanner.nextLine());

        Entrega entrega = entregas.get(indiceEntrega);

        Entregador entregador = atribuirEntregador();

        entrega.registrarEntrega(entregador);

        System.out.println("Entregador atribuído com sucesso!");
        System.out.println(entrega);
    }

    private Entregador atribuirEntregador() {
        if (!entregadores.isEmpty()) {
            Random random = new Random();

            int indice = random.nextInt(entregadores.size());

            return entregadores.remove(indice);
        }

        return null;
    }

    public void listarEntregas() {
        if (entregas.isEmpty()) {
            System.out.println("Nenhuma Entrega Cadastrada");
            return;
        }

        System.out.println("========= Entregas Cadastradas =========\n");
        for (Entrega e : entregas) {
            System.out.println(e);
        }
    }

}
