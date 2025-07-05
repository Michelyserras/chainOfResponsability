package com.example.chainofresponsibility.validation;

import com.example.chainofresponsibility.dto.CreateUserRequest;
import com.example.chainofresponsibility.dto.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class NameValidationHandler extends UserCreationValidationHandler {

    @Override
    public ValidationResult handle(CreateUserRequest request) {
        System.out.println("Validando nome do usuário");
        
        String name = request.getName();
        
        if (name == null || name.trim().isEmpty()) {
            return new ValidationResult(false, "INVALID_NAME", 
                "Nome é obrigatório");
        }
        
        String trimmedName = name.trim();
        if (trimmedName.length() < 2) {
            return new ValidationResult(false, "INVALID_NAME", 
                "Nome deve ter pelo menos 2 caracteres");
        }
        
        if (trimmedName.length() > 50) {
            return new ValidationResult(false, "INVALID_NAME", 
                "Nome deve ter no máximo 50 caracteres");
        }
        
        if (!trimmedName.matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
            return new ValidationResult(false, "INVALID_NAME", 
                "Nome deve conter apenas letras e espaços");
        }
        
        // Atualiza o nome com a versão limpa
        request.setName(trimmedName);
        
        System.out.println("Nome válido: " + trimmedName);
        return passToNext(request);
    }
}
