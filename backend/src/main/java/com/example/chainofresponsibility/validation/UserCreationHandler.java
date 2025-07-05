package com.example.chainofresponsibility.validation;

import com.example.chainofresponsibility.dto.CreateUserRequest;
import com.example.chainofresponsibility.dto.ValidationResult;
import com.example.chainofresponsibility.model.User;
import com.example.chainofresponsibility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCreationHandler extends UserCreationValidationHandler {

    @Autowired
    private UserService userService;

    @Override
    public ValidationResult handle(CreateUserRequest request) {
        System.out.println("Criando usuário no sistema");
        
        try {
            User newUser = userService.createUser(request);
            System.out.println("Usuário criado com sucesso: " + newUser.getName());
            return new ValidationResult(true, newUser);
        } catch (Exception e) {
            System.out.println("Erro ao criar usuário: " + e.getMessage());
            return new ValidationResult(false, "USER_CREATION_FAILED", 
                "Erro interno ao criar usuário. Tente novamente.");
        }
    }
}
