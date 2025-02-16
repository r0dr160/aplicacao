package com.aplicacao.aplicacao.integration;

/**
 * Exceção personalizada para erros de integração com sistemas externos.
 */
public class IntegrationException extends RuntimeException {

    /**
     * Construtor com mensagem de erro.
     *
     * @param message Mensagem de erro.
     */
    public IntegrationException(String message) {
        super(message);
    }

    /**
     * Construtor com mensagem de erro e causa.
     *
     * @param message Mensagem de erro.
     * @param cause   Causa do erro.
     */
    public IntegrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
