package com.aplicacao.aplicacao.controller;

import com.aplicacao.aplicacao.integration.CrediBankClient;
import com.aplicacao.aplicacao.integration.EuroMilClient;
import com.aplicacao.aplicacao.integration.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApostaController {

    @Autowired
    private CrediBankClient crediBankClient;

    @Autowired
    private EuroMilClient euroMilClient;

    // ======================================
    // Exibe o formulário
    // ======================================
    @GetMapping("/")
    public String getForm(Model model) {
        model.addAttribute("form", new ApostaForm());
        return "form";  // Nome do seu template Thymeleaf, ex.: form.html
    }

    // ======================================
    // Recebe o POST do formulário
    // ======================================
    @PostMapping("/registrarAposta")
    public String registrarAposta(@ModelAttribute("form") ApostaForm form, Model model) {
        try {
            // 1) Contata o CrediBank para obter cheque de 10 créditos
            String checkId = crediBankClient.getCheckID(form.getCreditAccount());

            // 2) Contata o EuroMilRegister (via gRPC) para registrar a aposta
            String response = euroMilClient.registerEuroMil(form.getKey(), checkId);

            // 3) Mensagem de sucesso
            model.addAttribute("message",
                    "Aposta registrada com sucesso! Resposta do EuroMil: " + response);

        } catch (IntegrationException e) {
            // Falha de integração (sistema externo indisponível, etc.)
            model.addAttribute("message",
                    "Erro ao integrar com serviço externo: " + e.getMessage());
        } catch (Exception e) {
            // Qualquer outro erro inesperado
            model.addAttribute("message",
                    "Erro inesperado: " + e.getMessage());
        }

        // Renderiza o resultado em templates/resultado.html
        return "resultado";
    }
}
