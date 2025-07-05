package com.example.chainofresponsibility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChainOfResponsibilityApplication {

    public static void main(String[] args) {
        System.out.println("Iniciando aplicação Chain of Responsibility");
        SpringApplication.run(ChainOfResponsibilityApplication.class, args);
        System.out.println("Aplicação iniciada com sucesso!");
        System.out.println("Usuários disponíveis para teste:");
        System.out.println("   - admin@teste.com / admin123");
        System.out.println("   - usuario@teste.com / usuario123");
        System.out.println("   - joao@email.com / joao456");
        System.out.println("   - maria@email.com / maria789");
    }
}
