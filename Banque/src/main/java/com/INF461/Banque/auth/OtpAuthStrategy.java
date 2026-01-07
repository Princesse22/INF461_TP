package com.inf461.Banque.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.inf461.Banque.sms.SmsService;

/**
 * OtpAuthStrategy
 * ----------------
 * Implémentation de l'authentification par OTP.
 */
public class OtpAuthStrategy implements AuthStrategy {

    private Map<String, String> otpStorage = new HashMap<>();

    @Override
    public void requestAuthentication(String phoneNumber) {

        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        otpStorage.put(phoneNumber, otp);

        // Envoi du SMS via le service Singleton
        SmsService smsService = SmsService.getInstance();
        smsService.sendSms(phoneNumber, "Votre code OTP est : " + otp);
    }

    @Override
    public boolean verifyAuthentication(String phoneNumber, String code) {
        String storedOtp = otpStorage.get(phoneNumber);
        return storedOtp != null && storedOtp.equals(code);
    }
}
