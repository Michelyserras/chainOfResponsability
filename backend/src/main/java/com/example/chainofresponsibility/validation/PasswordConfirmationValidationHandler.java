package com.example.chainofresponsibility.validation;

import com.example.chainofresponsibility.dto.CreateUserRequest;
import com.example.chainofresponsibility.dto.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class PasswordConfirmationValidationHandler extends UserCreationValidationHandler {

    @Override
    public ValidationResult handle(CreateUserRequest request) {
        System.out.println("Validando confirmação de senha");
        
        String password = request.getPassword();
        String confirmPassword = request.getConfirmPassword();
        
        if (confirmPassword == null || !confirmPassword.equals(password)) {
            return new ValidationResult(false, "PASSWORD_MISMATCH", 
                "Senha e confirmação de senha não coincidem");
        }
        
        System.out.println("Confirmação de senha válida");
        return passToNext(request);
    }
}
