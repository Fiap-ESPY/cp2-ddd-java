package com.cp2.logistica.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class Entrega {

    private final String id;
    private final String enderecoDestino;
    private String status;
    private Entregador entregador;

    private Entrega(String id, String enderecoDestino) {
        this.id = id;
        this.enderecoDestino = enderecoDestino;
        this.status = "PENDENTE";
    }

    public static Optional<Entrega> nova(String enderecoDestino) {
        return comIdentificador(UUID.randomUUID().toString(), enderecoDestino);
    }

    public static Optional<Entrega> comIdentificador(String id, String enderecoDestino) {
        if (enderecoDestino == null || enderecoDestino.isBlank()) {
            System.out.println("Endereço obrigatório.");
            return Optional.empty();
        }
        if (id == null || id.isBlank()) {
            System.out.println("Identificador obrigatório.");
            return Optional.empty();
        }
        return Optional.of(new Entrega(id, enderecoDestino.trim()));
    }

    public String getId() {
        return id;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public String getStatus() {
        return status;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public boolean registrarEntrega(Entregador entregadorAssociado, String statusInicial) {
        List<String> statusValidos = List.of("PENDENTE", "EM_ROTA", "ENTREGUE", "CANCELADO");

        if (statusInicial == null || !statusValidos.contains(statusInicial)) {
            System.out.println("Status inválido. Os status válidos são: " + statusValidos);
            return false;
        }

        if (!associarEntregadorSePermitido(entregadorAssociado)) {
            return false;
        }

        this.status = statusInicial;
        return true;
    }

    public boolean registrarEntrega(Entregador entregadorAssociado) {
        return registrarEntrega(entregadorAssociado, "EM_ROTA");
    }

    public boolean atualizarStatus(String novoStatus) {
        List<String> statusValidos = List.of("PENDENTE", "EM_ROTA", "ENTREGUE", "CANCELADO");

        if (novoStatus == null || !statusValidos.contains(novoStatus)) {
            System.out.println("Status inválido. Os status válidos são: " + statusValidos);
            return false;
        }

        this.status = novoStatus;
        return true;
    }

    public boolean definirEntregador(Entregador entregadorAssociado) {
        return associarEntregadorSePermitido(entregadorAssociado);
    }

    private boolean associarEntregadorSePermitido(Entregador entregadorAssociado) {
        if (entregadorAssociado == null) {
            System.out.println("Entregador obrigatório.");
            return false;
        }

        if ("CANCELADO".equals(status) || "ENTREGUE".equals(status)) {
            System.out.println(
                    "Não é permitido alterar entregador neste estado: " + status);
            return false;
        }

        this.entregador = entregadorAssociado;
        return true;
    }

    @Override
    public String toString() {
        String ent =
                entregador != null ? entregador.toString() : "sem entregador";
        return "Entrega " + id + " → " + enderecoDestino + " [" + status + "] " + ent;
    }
}
