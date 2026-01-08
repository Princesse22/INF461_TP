package com.INF461.Banque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BanqueApplication {

	public static void main(String[] args) {
        SpringApplication.run(BanqueApplication.class, args);
        System.out.println("=========================================");
        System.out.println("🚀 Application Banque démarrée !");
        System.out.println("🌐 http://localhost:8080");
        System.out.println("👤 Utilisateur par défaut : admin / admin123");
        System.out.println("📱 Twilio SMS prêt");
        System.out.println("💾 MySQL : banque_db");
        System.out.println("=========================================");
    }

}
