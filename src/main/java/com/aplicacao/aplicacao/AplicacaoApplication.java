package com.aplicacao.aplicacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal que arranca a aplicação Spring Boot.
 */
@SpringBootApplication
public class AplicacaoApplication {

    /**
     * Método de entrada da aplicação.
     *
     * @param args Argumentos da linha de comandos.
     */
    public static void main(String[] args) {
        // Inicializa e arranca a aplicação Spring Boot
        SpringApplication.run(AplicacaoApplication.class, args);
    }
}
