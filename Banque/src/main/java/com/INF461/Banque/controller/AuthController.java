package com.inf461.Banque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inf461.Banque.auth.AuthContext;
import com.inf461.Banque.auth.AuthFactory;
import com.inf461.Banque.auth.AuthStrategy;

/**
 * AuthController
 * --------------
 * Contrôleur Spring Boot qui gère l'authentification OTP
 * via une interface graphique (HTML).
 */
@Controller
public class AuthController {

    private AuthContext authContext;

    /**
     * Page d'accueil : formulaire de saisie du numéro
     */
    @GetMapping("/")
    public String showPhoneForm() {
        return "request-otp";
    }

    /**
     * Traitement de la demande d'OTP
     */
    @PostMapping("/request-otp")
    public String requestOtp(@RequestParam String phoneNumber, Model model) {

        // Factory Method : création de la stratégie OTP
        AuthStrategy strategy = AuthFactory.createAuthStrategy("OTP");
        authContext = new AuthContext(strategy);

        // Demande d'authentification
        authContext.requestAuthentication(phoneNumber);

        // Envoi du numéro à la page suivante
        model.addAttribute("phoneNumber", phoneNumber);

        return "verify-otp";
    }

    /**
     * Vérification du code OTP
     */
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String phoneNumber,
                            @RequestParam String code,
                            Model model) {

        boolean success = authContext.verifyAuthentication(phoneNumber, code);

        if (success) {
            model.addAttribute("message", "Authentification réussie !");
        } else {
            model.addAttribute("message", "Code OTP incorrect.");
        }

        return "result";
    }
}
