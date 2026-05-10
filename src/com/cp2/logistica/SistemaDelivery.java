package com.cp2.logistica;

import com.cp2.logistica.domain.Entrega;
import com.cp2.logistica.domain.Entregador;
import com.cp2.logistica.domain.EntregadorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class SistemaDelivery {

    private final Scanner scanner = new Scanner(System.in);
    private final List<Entregador> entregadores = new ArrayList<>();
    private final List<Entrega> entregas = new ArrayList<>();

    public void cadastrarEntregador() {
        System.out.println("Digite o nome do Entregador:");
        String nome = scanner.nextLine().trim();

        System.out.println("Digite o veículo de entrega (moto, carro ou bicicleta):");
        String tipoVeiculo = scanner.nextLine().trim();

        try {
            Entregador entregador = EntregadorFactory.criarEntregador(tipoVeiculo, nome);
            entregadores.add(entregador);
            System.out.println(entregador);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de veículo inválido. Use: moto, carro ou bicicleta.");
        }
    }

    public void cadastrarEntrega() {
        System.out.println("Digite o endereço da Entrega: ");
        String enderecoDestino = scanner.nextLine().trim();

        if (enderecoDestino.isBlank()) {
            System.out.println("Endereço obrigatório. Entrega não cadastrada.");
            return;
        }

        System.out.println("Digite a distância da entrega em quilômetros:");
        double distanciaKm;
        try {
            distanciaKm = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Distância inválida. Entrega não cadastrada.");
            return;
        }

        if (distanciaKm <= 0) {
            System.out.println("A distância deve ser maior que zero. Entrega não cadastrada.");
            return;
        }

        Entrega entrega = new Entrega(enderecoDestino, distanciaKm);
        if (entrega.getEnderecoDestino().isBlank()) {
            System.out.println("Entrega não cadastrada.");
            return;
        }

        entregas.add(entrega);

        System.out.println("Entrega cadastrada como PENDENTE, associe um entregador.");
        System.out.println(entrega);
    }

    public void associarEntregadorAEntrega() {
        if (entregas.isEmpty()) {
            System.out.println("Nenhuma entrega cadastrada.");
            return;
        }
        if (entregadores.isEmpty()) {
            System.out.println("Não há entregadores disponíveis para associar.");
            return;
        }

        List<Entrega> pendentes = this.listarEntregasPendentes();
        if (pendentes.isEmpty()) {
            System.out.println("Não há entregas com status PENDENTE para associar.");
            return;
        }

        System.out.println("======= Entregas PENDENTES =========");
        for (Entrega e : pendentes) {
            System.out.println(
                    e.getId() + " — " + e.getEnderecoDestino() + " (" + e.getDistanciaKm() + " km)");
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
        if (entrega == null || !"PENDENTE".equals(entrega.getStatus())) {
            System.out.println("Entrega não encontrada ou não está PENDENTE.");
            return;
        }

        System.out.println("======= Entregadores disponíveis ==========");
        for (Entregador e : entregadores) {
            System.out.println(e.getId() + " — " + e.getNome() + " (" + e.getDescricaoDoTipo() + ")");
        }

        System.out.println("Digite o ID do entregador:");
        long idEntregador;
        try {
            idEntregador = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Entregador entregador = this.buscarEntregadorPorId(idEntregador);
        if (entregador == null) {
            System.out.println("Entregador não encontrado com esse ID.");
            return;
        }

        System.out.println("Digite uma observação para a entrega (opcional, pressione Enter para pular):");
        String observacao = scanner.nextLine().trim();

        boolean foiAssociadoComSucesso;
        if (observacao.isBlank()) {
            foiAssociadoComSucesso = entrega.registrarEntrega(entregador);
        } else {
            foiAssociadoComSucesso = entrega.registrarEntrega(entregador, observacao);
        }

        if (!foiAssociadoComSucesso) {
            System.out.println("Não foi possível associar o entregador à entrega.");
            return;
        }

        double distanciaKm = entrega.getDistanciaKm();
        double tempoEstimadoHoras = entregador.calcularTempoEstimado(distanciaKm);
        double tempoEstimadoMinutos = tempoEstimadoHoras * 60.0;
        double valorEstimadoEntrega = entregador.calcularEntrega(distanciaKm);
        System.out.printf(
                Locale.forLanguageTag("pt-BR"),
                "Tempo estimado de entrega: %.2f minuto(s).%n",
                tempoEstimadoMinutos);
        System.out.printf(
                Locale.forLanguageTag("pt-BR"),
                "Valor estimado da entrega: R$ %.2f%n",
                valorEstimadoEntrega);

        entregadores.remove(entregador);

        System.out.println("Entregador associado. Status: EM_ROTA.");
        System.out.println(entrega);
    }

    private List<Entrega> listarEntregasPendentes() {
        List<Entrega> pendentes = new ArrayList<>();
        for (Entrega entrega : entregas) {
            if ("PENDENTE".equals(entrega.getStatus())) {
                pendentes.add(entrega);
            }
        }
        return pendentes;
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
            EM_ROTA
            ENTREGUE
            CANCELADO
            """);

        String novoStatus = scanner.nextLine().trim().toUpperCase();

        boolean atualizado = entrega.atualizarStatus(novoStatus);
        if (!atualizado) {
            System.out.println("Não foi possível atualizar o status da entrega.");
            return;
        }

        System.out.println("Status atualizado com sucesso!");

        String status = entrega.getStatus();
        if (!"ENTREGUE".equals(status) && !"CANCELADO".equals(status)) {
            return;
        }

        Entregador entregador = entrega.getEntregador();
        if (entregador == null) {
            return;
        }

        if (this.buscarEntregadorPorId(entregador.getId()) != null) {
            return;
        }

        entregadores.add(entregador);
    }

    private Entrega buscarEntregaPorId(long id) {
        for (Entrega item : entregas) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

}
