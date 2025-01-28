package com.aplicacao.aplicacao.integration;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CrediBankClient {

    // URL base: substitua {credit_account_id} e {value} por valores reais
    private static final String CREDIBANK_URL =
            "https://credibank.intsis.utad.pt:8080/check/{credit_account_id}/ammount/{value}/";

    public String getCheckID(String creditAccountId) {
        try {
            // Faz chamada REST
            RestTemplate restTemplate = new RestTemplate();

            // A API retorna um array JSON com 1 objeto:
            // [{"date":"yyyy-mm-ddThh:mm:ss","checkID":"################"}]
            CrediBankCheckResponse[] response = restTemplate.getForObject(
                    CREDIBANK_URL,
                    CrediBankCheckResponse[].class,
                    creditAccountId,
                    10 // Valor fixo = 10 créditos
            );

            // Se veio resposta
            if (response != null && response.length > 0) {
                return response[0].getCheckID();
            }

            // Se não veio nada
            throw new RuntimeException("CrediBank retornou resposta vazia ou nula.");

        } catch (RestClientException e) {
            // Erro de conexão, timeout, etc.
            throw new RuntimeException("Falha ao conectar ao CrediBank: " + e.getMessage(), e);
        }
    }

    // Classe interna para mapear a resposta JSON
    static class CrediBankCheckResponse {
        private String date;      // yyyy-mm-ddThh:mm:ss
        private String checkID;   // 16 dígitos

        public String getDate() {
            return date;
        }
        public void setDate(String date) {
            this.date = date;
        }
        public String getCheckID() {
            return checkID;
        }
        public void setCheckID(String checkID) {
            this.checkID = checkID;
        }
    }
}
