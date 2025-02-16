package com.aplicacao.aplicacao.integration;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Cliente responsável pela comunicação com o serviço CrediBank via REST.
 */
@Service
public class CrediBankClient {

    // URL base: substitua {credit_account_id} e {value} por valores reais
    private static final String CREDIBANK_URL =
            "https://credibank.intsis.utad.pt:8080/check/{credit_account_id}/ammount/{value}/";

    /**
     * Contacta o CrediBank e retorna o checkID associado à conta de crédito.
     *
     * @param creditAccountId Identificador da conta de crédito.
     * @return checkID retornado pelo CrediBank.
     */
    public String getCheckID(String creditAccountId) {
        try {
            // Instancia um RestTemplate para efetuar a chamada REST
            RestTemplate restTemplate = new RestTemplate();

            // A API retorna um array JSON com 1 objeto:
            // [{"date":"yyyy-mm-ddThh:mm:ss","checkID":"################"}]
            CrediBankCheckResponse[] response = restTemplate.getForObject(
                    CREDIBANK_URL,
                    CrediBankCheckResponse[].class,
                    creditAccountId,
                    10 // Valor fixo = 10 créditos
            );

            // Verifica se a resposta não é nula e contém pelo menos um objeto
            if (response != null && response.length > 0) {
                return response[0].getCheckID();
            }

            // Se a resposta for nula ou vazia, lança exceção
            throw new RuntimeException("CrediBank retornou resposta vazia ou nula.");

        } catch (RestClientException e) {
            // Trata exceções relacionadas com falhas de conexão ou timeouts
            throw new RuntimeException("Falha ao conectar ao CrediBank: " + e.getMessage(), e);
        }
    }

    /**
     * Classe interna para mapear a resposta JSON do CrediBank.
     */
    static class CrediBankCheckResponse {
        private String date;      // Data no formato yyyy-mm-ddThh:mm:ss
        private String checkID;   // Identificador do cheque (16 dígitos)

        /**
         * Retorna a data da resposta.
         *
         * @return data.
         */
        public String getDate() {
            return date;
        }

        /**
         * Define a data da resposta.
         *
         * @param date data a definir.
         */
        public void setDate(String date) {
            this.date = date;
        }

        /**
         * Retorna o checkID da resposta.
         *
         * @return checkID.
         */
        public String getCheckID() {
            return checkID;
        }

        /**
         * Define o checkID da resposta.
         *
         * @param checkID checkID a definir.
         */
        public void setCheckID(String checkID) {
            this.checkID = checkID;
        }
    }
}
