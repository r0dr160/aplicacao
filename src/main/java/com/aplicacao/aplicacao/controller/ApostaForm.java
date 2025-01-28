package com.aplicacao.aplicacao.controller;

/**
 * Classe que representa os campos do formulário.
 */
public class ApostaForm {

    private String creditAccount; // 16 dígitos (Conta de crédito)
    private String key;           // Chave do Euromilhões

    public ApostaForm() {
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
