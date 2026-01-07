package com.inf461.Banque.auth;

/**
 * AuthContext
 * -----------
 * Cette classe utilise une stratégie d'authentification
 * sans connaître son implémentation concrète.
 */
public class AuthContext{

    private AuthStrategy authStrategy;

    /**
     * Constructeur
     * On injecte la stratégie à utiliser.
     */
    public AuthContext(AuthStrategy authStrategy) {
        this.authStrategy = authStrategy;
    }

    /**
     * Lance la demande d'authentification.
     */
    public void requestAuthentication(String phoneNumber) {
        authStrategy.requestAuthentication(phoneNumber);
    }

    /**
     * Vérifie le code saisi par l'utilisateur.
     */
    public boolean verifyAuthentication(String phoneNumber, String code) {
        return authStrategy.verifyAuthentication(phoneNumber, code);
    }
}
