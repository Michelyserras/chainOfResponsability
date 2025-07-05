package com.example.chainofresponsibility.service;

import com.example.chainofresponsibility.dto.LoginRequest;
import com.example.chainofresponsibility.dto.ValidationResult;
import com.example.chainofresponsibility.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginValidationService {

    @Autowired
    private EmailFormatValidationHandler emailFormatHandler;

    @Autowired
    private PasswordStrengthValidationHandler passwordStrengthHandler;

    @Autowired
    private UserExistsValidationHandler userExistsHandler;

    @Autowired
    private CredentialsValidationHandler credentialsHandler;

    public ValidationResult validateLogin(LoginRequest request) {
        System.out.println("\nIniciando cadeia de validação para login");
        
        // Constrói a cadeia de responsabilidade
        LoginValidationHandler chain = buildValidationChain();
        
        // Executa a cadeia
        ValidationResult result = chain.handle(request);
        
        if (result.isSuccess()) {
            System.out.println("Todas as validações passaram com sucesso!");
        } else {
            System.out.println("Validação falhou: " + result.getMessage());
        }
        
        return result;
    }

    private LoginValidationHandler buildValidationChain() {
        // Configura a ordem da cadeia de validação
        emailFormatHandler.setNext(passwordStrengthHandler);
        passwordStrengthHandler.setNext(userExistsHandler);
        userExistsHandler.setNext(credentialsHandler);
        
        return emailFormatHandler; // Retorna o primeiro handler da cadeia
    }
}
