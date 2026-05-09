package com.cp2.logistica.domain;

public final class LogDominio {

    private LogDominio() {
    }

    public static void registrar(String mensagem) {
        System.err.println("[logistica] " + mensagem);
    }
}
