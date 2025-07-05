package com.example.chainofresponsibility.validation;

import com.example.chainofresponsibility.dto.LoginRequest;
import com.example.chainofresponsibility.dto.ValidationResult;
import com.example.chainofresponsibility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserExistsValidationHandler extends LoginValidationHandler {

    @Autowired
    private UserService userService;

    @Override
    public ValidationResult handle(LoginRequest request) {
        System.out.println("Verificando se usuário existe: " + request.getEmail());
        
        if (!userService.userExists(request.getEmail())) {
            return new ValidationResult(false, "USER_NOT_FOUND", 
                "Usuário não encontrado com este email");
        }
        
        System.out.println("Usuário encontrado");
        return passToNext(request);
    }
}
