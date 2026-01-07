package com.inf461.Banque.sms;

/**
 * SmsService
 * ----------
 * Cette classe simule l'envoi de SMS.
 * Elle utilise le patron Singleton.
 */
public class SmsService{

    // Instance unique
    private static SmsService instance;

    // Constructeur privé (empêche new)
    private SmsService() {}

    /**
     * Retourne l'unique instance du service.
     */
    public static SmsService getInstance() {
        if (instance == null) {
            instance = new SmsService();
        }
        return instance;
    }

    /**
     * Simule l'envoi d'un SMS.
     */
    public void sendSms(String phoneNumber, String message) {
        System.out.println("SMS envoyé à " + phoneNumber + " : " + message);
    }
}
