package com.inf461.Banque.auth;

/**
 * AuthStrategy
 * ------------
 * Interface qui définit le comportement commun
 * à toutes les méthodes d'authentification.
 *
 * (OTP aujourd'hui, Email ou Biométrie plus tard)
 */
public interface AuthStrategy {

    /**
     * Demande une authentification.
     * Exemple : envoyer un code OTP.
     *
     * @param phoneNumber numéro de téléphone de l'utilisateur
     */
    void requestAuthentication(String phoneNumber);

    /**
     * Vérifie l'authentification.
     *
     * @param phoneNumber numéro de téléphone
     * @param code code saisi par l'utilisateur
     * @return true si le code est correct, false sinon
     */
    boolean verifyAuthentication(String phoneNumber, String code);
}
