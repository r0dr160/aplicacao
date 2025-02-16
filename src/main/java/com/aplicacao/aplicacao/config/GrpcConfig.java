package com.aplicacao.aplicacao.config;

import com.aplicacao.aplicacao.integration.EuroMilClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para o cliente gRPC.
 * Configura e disponibiliza o bean do EuroMilClient para injeção de dependências.
 */
@Configuration
public class GrpcConfig {

    /**
     * Cria e regista o bean do EuroMilClient.
     *
     * @return instância configurada do EuroMilClient.
     */
    @Bean
    public EuroMilClient euroMilClient() {
        // Definição do host onde o serviço gRPC está a correr
        String host = "localhost";
        // Definição da porta para comunicação gRPC
        int port = 50051;
        // Criação e retorno de uma instância do cliente gRPC EuroMilClient
        return new EuroMilClient(host, port);
    }
}
