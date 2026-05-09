package com.cp2.logistica.console;

import com.cp2.logistica.domain.Entregador;
import com.cp2.logistica.domain.EntregadorFactory;
import com.cp2.logistica.domain.SistemaDelivery;

import java.util.Scanner;

public final class MenuConsole {

    private final Scanner scanner = new Scanner(System.in);
    private final SistemaDelivery sistemaDelivery = new SistemaDelivery();

    public void executar() {
        System.out.println("Sistema de Logística — CP2 (base)");
        boolean continuar = true;
        while (continuar) {
            this.exibirOpcoes();
            String linhaLida = this.scanner.nextLine().trim();
            continuar = this.processar(linhaLida);
        }
    }

    private void exibirOpcoes() {
        System.out.println();
        System.out.println("1 — Cadastrar entregador");
        System.out.println("2 — Criar entrega");
        System.out.println("3 — Listar entregas");
        System.out.println("4 — Atribuir entrega a entregador");
        System.out.println("5 — Atualizar status da entrega");
        System.out.println("0 — Sair");
        System.out.print("Escolha: ");
    }

    private boolean processar(String opcaoDoMenu) {
        switch (opcaoDoMenu) {
            case "1":
                this.sistemaDelivery.cadastrarEntregador();
                return true;
            case "2":
                this.informarImplementacaoFutura("Criar entrega");
                return true;
            case "3":
                this.informarImplementacaoFutura("Listar entregas");
                return true;
            case "4":
                this.informarImplementacaoFutura("Atribuir entrega");
                return true;
            case "5":
                this.informarImplementacaoFutura("Atualizar status");
                return true;
            case "0":
                System.out.println("Encerrando.");
                return false;
            default:
                System.out.println("Opção inválida.");
                return true;
        }
    }




    private void informarImplementacaoFutura(String textoDaAcao) {
        System.out.println("[Em breve] " + textoDaAcao + ".");
    }
}
