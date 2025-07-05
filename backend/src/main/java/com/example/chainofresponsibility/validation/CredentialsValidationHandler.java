package com.example.chainofresponsibility.validation;

import com.example.chainofresponsibility.dto.LoginRequest;
import com.example.chainofresponsibility.dto.ValidationResult;
import com.example.chainofresponsibility.model.User;
import com.example.chainofresponsibility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CredentialsValidationHandler extends LoginValidationHandler {

    @Autowired
    private UserService userService;

    @Override
    public ValidationResult handle(LoginRequest request) {
        System.out.println("Validando credenciais do usuário");
        
        User user = userService.findByEmail(request.getEmail());
        
        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return new ValidationResult(false, "INVALID_CREDENTIALS", 
                "Email ou senha incorretos");
        }
        
        System.out.println("Credenciais válidas para usuário: " + user.getName());
        return new ValidationResult(true, user);
    }
}
