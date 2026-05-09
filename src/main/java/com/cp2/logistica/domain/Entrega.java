package com.cp2.logistica.domain;

import java.util.List;
import java.util.Random;

public final class Entrega {

    private final Long id = Math.abs(new Random().nextLong());

    private final String enderecoDestino;
    private String status;
    private Entregador entregador;

    public Entrega(String enderecoDestino) {
        if (enderecoDestino == null || enderecoDestino.isBlank()) {
            System.out.println("Endereço obrigatório.");
            this.enderecoDestino = "";
        } else {
            this.enderecoDestino = enderecoDestino.trim();
        }
        this.status = "PENDENTE";
    }

    public Long getId() {
        return this.id;
    }

    public String getEnderecoDestino() {
        return this.enderecoDestino;
    }

    public String getStatus() {
        return this.status;
    }

    public Entregador getEntregador() {
        return this.entregador;
    }

    public boolean registrarEntrega(Entregador entregadorAssociado, String statusInicial) {
        List<String> statusValidos =
                List.of("PENDENTE", "EM_ROTA", "ENTREGUE", "CANCELADO");

        if (statusInicial == null
                || !statusValidos.contains(statusInicial)) {
            System.out.println(
                    "Status inválido. Os status válidos são: " + statusValidos);
            return false;
        }

        if (!this.associarEntregadorSePermitido(entregadorAssociado)) {
            return false;
        }

        this.status = statusInicial;
        return true;
    }

    public boolean registrarEntrega(Entregador entregadorAssociado) {
        return this.registrarEntrega(entregadorAssociado, "EM_ROTA");
    }

    public boolean atualizarStatus(String novoStatus) {
        List<String> statusValidos =
                List.of("PENDENTE", "EM_ROTA", "ENTREGUE", "CANCELADO");

        if (novoStatus == null
                || !statusValidos.contains(novoStatus)) {
            System.out.println(
                    "Status inválido. Os status válidos são: " + statusValidos);
            return false;
        }

        this.status = novoStatus;
        return true;
    }

    public boolean definirEntregador(Entregador entregadorAssociado) {
        return this.associarEntregadorSePermitido(entregadorAssociado);
    }

    private boolean associarEntregadorSePermitido(Entregador entregadorAssociado) {
        if (entregadorAssociado == null) {
            System.out.println("Entregador obrigatório.");
            return false;
        }

        if ("CANCELADO".equals(this.status) || "ENTREGUE".equals(this.status)) {
            System.out.println(
                    "Não é permitido alterar entregador neste estado: " + this.status);
            return false;
        }

        this.entregador = entregadorAssociado;
        return true;
    }

    @Override
    public String toString() {
        String resumoFormatado = """
                ================================
                   ENTREGA
                ================================
                Identificação:           %d
                Endereço de destino:     %s
                ----------------------------
                Status:                  %s
                ----------------------------
                Detalhes do entregador:
                %s
                ================================
                """;

        String detalhesDoEntregador =
                this.entregador != null ? this.entregador.toString() : "sem entregador";

        return resumoFormatado.formatted(
                this.id,
                this.enderecoDestino,
                this.status,
                detalhesDoEntregador);
    }
}
