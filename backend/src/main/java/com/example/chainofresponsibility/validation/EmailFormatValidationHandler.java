package com.example.chainofresponsibility.validation;

import com.example.chainofresponsibility.dto.LoginRequest;
import com.example.chainofresponsibility.dto.ValidationResult;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailFormatValidationHandler extends LoginValidationHandler {
    private static final String EMAIL_PATTERN = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public ValidationResult handle(LoginRequest request) {
        System.out.println("🔍 Validando formato do email: " + request.getEmail());
        
        if (request.getEmail() == null || !pattern.matcher(request.getEmail()).matches()) {
            return new ValidationResult(false, "INVALID_EMAIL_FORMAT", 
                "Email deve ter formato válido (exemplo: usuario@dominio.com)");
        }
        
        System.out.println("✅ Email com formato válido");
        return passToNext(request);
    }
}
