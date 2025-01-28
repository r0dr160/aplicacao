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

    public CrediBankCheckResponse() {
    }

    public String getDate() {
        return date;
    }

    public String getCheckID() {
        return checkID;
    }
}
