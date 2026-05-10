package com.cp2.logistica.domain;

import java.util.List;
import java.util.Random;

public final class Entrega {

    private final Long id = Math.abs(new Random().nextLong());

    private final String enderecoDestino;
    private final double distanciaKm;
    private String status;
    private String observacao;
    private Entregador entregador;


    public Entrega(String enderecoDestino, double distanciaKm) {
        this(enderecoDestino, distanciaKm, "");
    }

    public Entrega(String enderecoDestino, double distanciaKm, String observacaoInicial) {
        if (enderecoDestino == null || enderecoDestino.isBlank()) {
            System.out.println("Endereço obrigatório.");
            this.enderecoDestino = "";
        } else {
            this.enderecoDestino = enderecoDestino.trim();
        }
        this.distanciaKm = distanciaKm;
        this.status = "PENDENTE";
        this.observacao = observacaoInicial != null ? observacaoInicial.trim() : "";
    }

    public Long getId() {
        return this.id;
    }

    public String getEnderecoDestino() {
        return this.enderecoDestino;
    }

    public double getDistanciaKm() {
        return this.distanciaKm;
    }

    public String getStatus() {
        return this.status;
    }

    public Entregador getEntregador() {
        return this.entregador;
    }

    public String getObservacao() {
        return this.observacao;
    }

    public boolean registrarEntrega(Entregador entregadorAssociado) {
        return this.registrarEntrega(entregadorAssociado, null);
    }

    public boolean registrarEntrega(Entregador entregadorAssociado, String observacaoDaEntrega) {
        if (!"PENDENTE".equals(this.status)) {
            System.out.println("Só é possível associar entregador com entrega PENDENTE.");
            return false;
        }

        if (entregadorAssociado == null) {
            System.out.println("Entregador obrigatório.");
            return false;
        }

        if (this.enderecoDestino.isBlank()) {
            System.out.println("Endereço inválido na entrega.");
            return false;
        }

        this.entregador = entregadorAssociado;
        if (observacaoDaEntrega != null && !observacaoDaEntrega.trim().isEmpty()) {
            this.observacao = observacaoDaEntrega.trim();
        }
        this.status = "EM_ROTA";
        return true;
    }

    public boolean atualizarStatus(String novoStatus) {
        List<String> statusValidos =
                List.of("PENDENTE", "EM_ROTA", "ENTREGUE", "CANCELADO");

        if (novoStatus == null || !statusValidos.contains(novoStatus)) {
            System.out.println(
                    "Status inválido. Os status válidos são: " + statusValidos);
            return false;
        }

        String atual = this.status;
        if (atual.equals(novoStatus)) {
            return true;
        }

        if ("ENTREGUE".equals(atual) || "CANCELADO".equals(atual)) {
            System.out.println("Entrega finalizada; não é possível alterar o status.");
            return false;
        }

        if ("PENDENTE".equals(atual)) {
            if ("CANCELADO".equals(novoStatus)) {
                this.status = novoStatus;
                return true;
            }
            if ("EM_ROTA".equals(novoStatus)) {
                System.out.println(
                        "Use a opção do menu \"Associar entregador à entrega\" para colocar em EM_ROTA.");
                return false;
            }
            if ("ENTREGUE".equals(novoStatus)) {
                System.out.println(
                        "Não é possível marcar como ENTREGUE sem entregador associado (EM_ROTA).");
                return false;
            }
            return false;
        }

        if ("EM_ROTA".equals(atual)) {
            if ("ENTREGUE".equals(novoStatus) || "CANCELADO".equals(novoStatus)) {
                this.status = novoStatus;
                return true;
            }
            if ("PENDENTE".equals(novoStatus)) {
                System.out.println("Não é possível voltar de EM_ROTA para PENDENTE.");
                return false;
            }
            return false;
        }

        return false;
    }

    @Override
    public String toString() {
        String resumoFormatado = """
                ================================
                   ENTREGA
                ================================
                ID:                      %d
                Endereço de destino:     %s
                Distância (km):          %.2f
                Status:                  %s
                Observação:              %s
                ----------------------------
                Detalhes do entregador:
                %s
                """;

        String detalhesDoEntregador =
                this.entregador != null ? this.entregador.toString() : "sem entregador";

        return resumoFormatado.formatted(
                this.id,
                this.enderecoDestino,
                this.distanciaKm,
                this.status,
                this.observacao.isBlank() ? "sem observação" : this.observacao,
                detalhesDoEntregador);
    }
}
