package com.example.chainofresponsibility.validation;

import com.example.chainofresponsibility.dto.CreateUserRequest;
import com.example.chainofresponsibility.dto.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class PasswordComplexityValidationHandler extends UserCreationValidationHandler {

    @Override
    public ValidationResult handle(CreateUserRequest request) {
        System.out.println("Validando complexidade da senha");
        
        String password = request.getPassword();
        
        if (password == null || password.length() < 8) {
            return new ValidationResult(false, "WEAK_PASSWORD", 
                "Senha deve ter pelo menos 8 caracteres");
        }
        
        if (!password.matches(".*[A-Z].*")) {
            return new ValidationResult(false, "WEAK_PASSWORD", 
                "Senha deve conter pelo menos uma letra maiúscula");
        }
        
        if (!password.matches(".*[a-z].*")) {
            return new ValidationResult(false, "WEAK_PASSWORD", 
                "Senha deve conter pelo menos uma letra minúscula");
        }
        
        if (!password.matches(".*[0-9].*")) {
            return new ValidationResult(false, "WEAK_PASSWORD", 
                "Senha deve conter pelo menos um número");
        }
        
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            return new ValidationResult(false, "WEAK_PASSWORD", 
                "Senha deve conter pelo menos um caractere especial");
        }
        
        System.out.println("Senha atende aos critérios de complexidade");
        return passToNext(request);
    }
}
