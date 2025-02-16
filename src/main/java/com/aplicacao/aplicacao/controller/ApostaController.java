package com.aplicacao.aplicacao.controller;

import com.aplicacao.aplicacao.integration.CrediBankClient;
import com.aplicacao.aplicacao.integration.EuroMilClient;
import com.aplicacao.aplicacao.integration.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsável por gerir as operações de aposta.
 */
@Controller
public class ApostaController {

    // Injeção do cliente para comunicação com o CrediBank
    @Autowired
    private CrediBankClient crediBankClient;

    // Injeção do cliente para comunicação com o serviço gRPC EuroMil
    @Autowired
    private EuroMilClient euroMilClient;

    // ======================================
    // Exibe o formulário
    // ======================================
    /**
     * Método responsável por apresentar o formulário de aposta.
     *
     * @param model Modelo utilizado para transportar dados para a vista.
     * @return Nome do template Thymeleaf a ser renderizado.
     */
    @GetMapping("/")
    public String getForm(Model model) {
        // Adiciona um novo objeto ApostaForm ao modelo para ser preenchido no formulário
        model.addAttribute("form", new ApostaForm());
        // Retorna o nome do template Thymeleaf (ex.: form.html)
        return "form";
    }

    // ======================================
    // Recebe o POST do formulário
    // ======================================
    /**
     * Método responsável por processar o registo de uma aposta.
     *
     * @param form  Objeto ApostaForm preenchido pelo utilizador.
     * @param model Modelo utilizado para transportar dados para a vista.
     * @return Nome do template Thymeleaf que apresenta o resultado.
     */
    @PostMapping("/registrarAposta")
    public String registrarAposta(@ModelAttribute("form") ApostaForm form, Model model) {
        try {
            // 1) Contacta o CrediBank para obter um cheque de 10 créditos
            String checkId = crediBankClient.getCheckID(form.getCreditAccount());

            // 2) Contacta o EuroMilRegister (via gRPC) para registar a aposta
            String response = euroMilClient.registerEuroMil(form.getKey(), checkId);

            // 3) Adiciona mensagem de sucesso ao modelo
            model.addAttribute("message",
                    "Aposta registrada com sucesso! Resposta do EuroMil: " + response);

        } catch (IntegrationException e) {
            // Trata exceções de integração (ex.: serviço externo indisponível)
            model.addAttribute("message",
                    "Erro ao integrar com serviço externo: " + e.getMessage());
        } catch (Exception e) {
            // Trata quaisquer outros erros inesperados
            model.addAttribute("message",
                    "Erro inesperado: " + e.getMessage());
        }

        // Renderiza a vista que apresenta o resultado (resultado.html)
        return "resultado";
    }
}
