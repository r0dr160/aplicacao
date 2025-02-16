package com.aplicacao.aplicacao.integration;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mapeia a resposta do CrediBank, que vem no formato:
 * [
 *   {
 *     "date": "yyyy-mm-ddThh:mm:ss",
 *     "checkID": "################"
 *   }
 * ]
 */
public class CrediBankCheckResponse {

    @JsonProperty("date")
    private String date;

    @JsonProperty("checkID")
    private String checkID;

    /**
     * Construtor padrão.
     */
    public CrediBankCheckResponse() {
    }

    /**
     * Retorna a data presente na resposta.
     *
     * @return data no formato especificado.
     */
    public String getDate() {
        return date;
    }

    /**
     * Retorna o identificador do cheque.
     *
     * @return identificador do cheque.
     */
    public String getCheckID() {
        return checkID;
    }
}
