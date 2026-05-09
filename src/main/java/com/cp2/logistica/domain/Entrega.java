package com.cp2.logistica.domain;

import java.util.UUID;

public final class Entrega {

    private final String id;
    private final String enderecoDestino;
    private StatusEntrega status;
    private Entregador entregador;

    public Entrega(String enderecoDestino) {
        this(UUID.randomUUID().toString(), enderecoDestino);
    }

    public Entrega(String id, String enderecoDestino) {
        if (enderecoDestino == null || enderecoDestino.isBlank()) {
            throw new IllegalArgumentException("Endereço obrigatório.");
        }
        this.id = id;
        this.enderecoDestino = enderecoDestino.trim();
        this.status = StatusEntrega.PENDENTE;
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

    public void registrarEntrega(Entregador entregadorAssociado, StatusEntrega statusInicial) {
        definirOuValidarEntregador(entregadorAssociado);
        if (statusInicial == null) {
            throw new IllegalArgumentException("Status inicial obrigatório.");
        }
        this.status = statusInicial;
    }

    public void registrarEntrega(Entregador entregadorAssociado) {
        registrarEntrega(entregadorAssociado, StatusEntrega.EM_ROTA);
    }

    public void atualizarStatus(StatusEntrega novoStatus) {
        if (novoStatus == null) {
            throw new IllegalArgumentException("Status inválido.");
        }
        this.status = novoStatus;
    }

    public void definirEntregador(Entregador entregadorAssociado) {
        definirOuValidarEntregador(entregadorAssociado);
    }

    private void definirOuValidarEntregador(Entregador entregadorAssociado) {
        if (entregadorAssociado == null) {
            throw new IllegalArgumentException("Entregador obrigatório.");
        }
        if (status == StatusEntrega.CANCELADO || status == StatusEntrega.ENTREGUE) {
            throw new IllegalStateException("Não é permitido alterar entregador neste estado: " + status);
        }
        this.entregador = entregadorAssociado;
    }

    @Override
    public String toString() {
        String ent =
                entregador != null ? entregador.toString() : "sem entregador";
        return "Entrega " + id + " → " + enderecoDestino + " [" + status + "] " + ent;
    }
}
