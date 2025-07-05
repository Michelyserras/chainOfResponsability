package com.example.chainofresponsibility.validation;

import com.example.chainofresponsibility.dto.LoginRequest;
import com.example.chainofresponsibility.dto.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class PasswordStrengthValidationHandler extends LoginValidationHandler {

    @Override
    public ValidationResult handle(LoginRequest request) {
        System.out.println("Validando força da senha");
        
        String password = request.getPassword();
        
        if (password == null || password.length() < 6) {
            return new ValidationResult(false, "WEAK_PASSWORD", 
                "Senha deve ter pelo menos 6 caracteres");
        }
        
        if (!password.matches(".*[A-Za-z].*")) {
            return new ValidationResult(false, "WEAK_PASSWORD", 
                "Senha deve conter pelo menos uma letra");
        }
        
        if (!password.matches(".*[0-9].*")) {
            return new ValidationResult(false, "WEAK_PASSWORD", 
                "Senha deve conter pelo menos um número");
        }
        
        System.out.println("Senha atende aos critérios de força");
        return passToNext(request);
    }
}
