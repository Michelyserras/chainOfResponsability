package com.example.chainofresponsibility.validation;

import com.example.chainofresponsibility.dto.CreateUserRequest;
import com.example.chainofresponsibility.dto.ValidationResult;
import com.example.chainofresponsibility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailUniqueValidationHandler extends UserCreationValidationHandler {

    @Autowired
    private UserService userService;

    @Override
    public ValidationResult handle(CreateUserRequest request) {
        System.out.println("Validando unicidade do email: " + request.getEmail());
        
        if (userService.userExists(request.getEmail())) {
            return new ValidationResult(false, "EMAIL_ALREADY_EXISTS", 
                "Este email já está em uso. Escolha outro email.");
        }
        
        System.out.println("Email disponível para uso");
        return passToNext(request);
    }
}
