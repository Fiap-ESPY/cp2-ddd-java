package com.cp2.logistica.domain;

import java.util.Optional;
import java.util.UUID;

public final class Entrega {

    private final String id;
    private final String enderecoDestino;
    private StatusEntrega status;
    private Entregador entregador;

    private Entrega(String id, String enderecoDestino) {
        this.id = id;
        this.enderecoDestino = enderecoDestino;
        this.status = StatusEntrega.PENDENTE;
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

    public StatusEntrega getStatus() {
        return status;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public boolean registrarEntrega(Entregador entregadorAssociado, StatusEntrega statusInicial) {
        if (!associarEntregadorSePermitido(entregadorAssociado)) {
            return false;
        }
        if (statusInicial == null) {
            System.out.println("Status inicial obrigatório.");
            return false;
        }
        this.status = statusInicial;
        return true;
    }

    public boolean registrarEntrega(Entregador entregadorAssociado) {
        return registrarEntrega(entregadorAssociado, StatusEntrega.EM_ROTA);
    }

    public boolean atualizarStatus(StatusEntrega novoStatus) {
        if (novoStatus == null) {
            System.out.println("Status inválido.");
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
        if (status == StatusEntrega.CANCELADO || status == StatusEntrega.ENTREGUE) {
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
