package com.cp2.logistica.console;

import java.util.Scanner;

public final class MenuConsole {

    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("Sistema de Logística — CP2 (base)");
        boolean continuar = true;
        while (continuar) {
            exibirOpcoes();
            String linha = scanner.nextLine().trim();
            continuar = processar(linha);
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

    private boolean processar(String opcao) {
        switch (opcao) {
            case "1":
                emBreve("Cadastrar entregador");
                return true;
            case "2":
                emBreve("Criar entrega");
                return true;
            case "3":
                emBreve("Listar entregas");
                return true;
            case "4":
                emBreve("Atribuir entrega");
                return true;
            case "5":
                emBreve("Atualizar status");
                return true;
            case "0":
                System.out.println("Encerrando.");
                return false;
            default:
                System.out.println("Opção inválida.");
                return true;
        }
    }

    private static void emBreve(String nome) {
        System.out.println("[Em breve] " + nome + ".");
    }
}
