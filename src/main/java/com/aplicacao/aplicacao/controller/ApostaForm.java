package com.aplicacao.aplicacao.controller;

/**
 * Classe que representa os campos do formulário de aposta.
 */
public class ApostaForm {

    private String creditAccount; // Número da conta de crédito (16 dígitos)
    private String key;           // Chave do Euromilhões

    /**
     * Construtor padrão.
     */
    public ApostaForm() {
    }

    /**
     * Retorna a conta de crédito.
     *
     * @return conta de crédito.
     */
    public String getCreditAccount() {
        return creditAccount;
    }

    /**
     * Define a conta de crédito.
     *
     * @param creditAccount conta de crédito a definir.
     */
    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    /**
     * Retorna a chave do Euromilhões.
     *
     * @return chave do Euromilhões.
     */
    public String getKey() {
        return key;
    }

    /**
     * Define a chave do Euromilhões.
     *
     * @param key chave a definir.
     */
    public void setKey(String key) {
        this.key = key;
    }
}
