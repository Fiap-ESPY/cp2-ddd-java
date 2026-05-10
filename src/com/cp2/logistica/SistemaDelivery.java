package com.cp2.logistica;

import com.cp2.logistica.domain.Entrega;
import com.cp2.logistica.domain.Entregador;
import com.cp2.logistica.domain.EntregadorFactory;

import java.util.ArrayList;
import java.util.List;
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
        if (entregadores.isEmpty()) {
            System.out.println(
                    "É obrigatório ter pelo menos um entregador cadastrado para criar uma entrega.");
            return;
        }

        System.out.println("Digite o endereço da Entrega: ");
        String enderecoDestino = scanner.nextLine().trim();

        Entrega entrega = new Entrega(enderecoDestino);

        System.out.println("======= Entregadores disponíveis ==========");
        for (Entregador e : entregadores) {
            System.out.println(e.getId() + " — " + e.getNome() + " (" + e.getDescricaoDoTipo() + ")");
        }

        System.out.println("Digite o ID do entregador (obrigatório):");
        long idEntregador;
        try {
            idEntregador = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Entrega não cadastrada.");
            return;
        }

        Entregador entregador = this.buscarEntregadorPorId(idEntregador);
        if (entregador == null) {
            System.out.println("Entregador não encontrado com esse ID. Entrega não cadastrada.");
            return;
        }

        if (entrega.registrarEntrega(entregador)) {
            System.out.println("Não foi possível associar o entregador à entrega.");
            return;
        }

        entregadores.remove(entregador);
        entregas.add(entrega);

        System.out.println("Entrega cadastrada com entregador vinculado.");
        System.out.println(entrega);
    }

    private Entregador buscarEntregadorPorId(long id) {
        for (Entregador entregador : entregadores) {
            if (entregador.getId() == id) {
                return entregador;
            }
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

    public void atualizarStatusEntrega() {
        if (entregas.isEmpty()) {
            System.out.println("Nenhuma entrega cadastrada.");
            return;
        }

        System.out.println("======= Entregas cadastradas =========");
        for (Entrega e : entregas) {
            System.out.println(
                    e.getId() + " — " + e.getEnderecoDestino() + " (" + e.getStatus() + ")");
        }

        System.out.println("Digite o ID da entrega:");
        long idEntrega;
        try {
            idEntrega = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Entrega entrega = this.buscarEntregaPorId(idEntrega);
        if (entrega == null) {
            System.out.println("Entrega não encontrada com esse ID.");
            return;
        }

        System.out.println("""
            Digite o novo status:
            PENDENTE
            ENTREGUE
            CANCELADO
            """);

        String novoStatus = scanner.nextLine().trim().toUpperCase();

        boolean atualizado = entrega.atualizarStatus(novoStatus);

        if (atualizado) {
            System.out.println("Status atualizado com sucesso!");
        }
    }

    private Entrega buscarEntregaPorId(long id) {
        for (Entrega entregador : entregas) {
            if (entregador.getId() == id) {
                return entregador;
            }
        }

        return null;
    }

}
