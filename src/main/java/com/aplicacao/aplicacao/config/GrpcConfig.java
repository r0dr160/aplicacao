package com.aplicacao.aplicacao.config;

import com.aplicacao.aplicacao.integration.EuroMilClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Bean
    public EuroMilClient euroMilClient() {
        String host = "localhost";
        int port = 50051;
        return new EuroMilClient(host, port);
    }
}
