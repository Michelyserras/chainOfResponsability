package com.example.chainofresponsibility.service;

import com.example.chainofresponsibility.dto.CreateUserRequest;
import com.example.chainofresponsibility.dto.ValidationResult;
import com.example.chainofresponsibility.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreationService {

    @Autowired
    private EmailUniqueValidationHandler emailUniqueHandler;

    @Autowired
    private PasswordComplexityValidationHandler passwordComplexityHandler;

    @Autowired
    private PasswordConfirmationValidationHandler passwordConfirmationHandler;

    @Autowired
    private NameValidationHandler nameHandler;

    @Autowired
    private UserCreationHandler userCreationHandler;

    public ValidationResult createUser(CreateUserRequest request) {
        System.out.println("\nIniciando cadeia de validação para criação de usuário");
        
        // Constrói a cadeia de responsabilidade para criação
        UserCreationValidationHandler chain = buildCreationChain();
        
        // Executa a cadeia
        ValidationResult result = chain.handle(request);
        
        if (result.isSuccess()) {
            System.out.println("Todas as validações de criação passaram com sucesso!");
        } else {
            System.out.println("Validação de criação falhou: " + result.getMessage());
        }
        
        return result;
    }

    private UserCreationValidationHandler buildCreationChain() {
        // Configura a ordem da cadeia de validação para criação
        emailUniqueHandler.setNext(passwordComplexityHandler);
        passwordComplexityHandler.setNext(passwordConfirmationHandler);
        passwordConfirmationHandler.setNext(nameHandler);
        nameHandler.setNext(userCreationHandler);
        
        return emailUniqueHandler; // Retorna o primeiro handler da cadeia
    }
}
