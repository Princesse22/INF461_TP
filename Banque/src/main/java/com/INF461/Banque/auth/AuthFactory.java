package com.inf461.Banque.auth;

/**
 * AuthFactory
 * -----------
 * Cette classe applique le patron Factory Method.
 * Elle est responsable de la création des stratégies
 * d'authentification.
 */
public class AuthFactory {

    /**
     * Crée une stratégie d'authentification selon le type demandé.
     *
     * @param type type d'authentification (OTP, EMAIL, etc.)
     * @return une implémentation de AuthStrategy
     */
    public static AuthStrategy createAuthStrategy(String type) {

        if ("OTP".equalsIgnoreCase(type)) {
            return new OtpAuthStrategy();
        }

        // Plus tard :
        // if ("EMAIL".equalsIgnoreCase(type)) { ... }

        throw new IllegalArgumentException("Type d'authentification non supporté");
    }
}

