package com.example.chainofresponsibility.controller;

import com.example.chainofresponsibility.dto.*;
import com.example.chainofresponsibility.service.LoginValidationService;
import com.example.chainofresponsibility.service.UserCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000") // Permite requests do React
public class AuthController {

    @Autowired
    private LoginValidationService loginValidationService;

    @Autowired
    private UserCreationService userCreationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        ValidationResult validationResult = loginValidationService.validateLogin(request);
        
        if (validationResult.isSuccess()) {
            // Gera um token simples (em produção usaria JWT)
            String token = "token_" + UUID.randomUUID().toString().substring(0, 8);
            
            LoginResponse response = new LoginResponse(true, token, validationResult.getUser());
            return ResponseEntity.ok(response);
        } else {
            LoginResponse response = new LoginResponse(false, 
                validationResult.getErrorCode(), 
                validationResult.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody CreateUserRequest request) {
        ValidationResult validationResult = userCreationService.createUser(request);
        
        if (validationResult.isSuccess()) {
            RegisterResponse response = new RegisterResponse(true, 
                "Usuário criado com sucesso", 
                validationResult.getUser());
            return ResponseEntity.ok(response);
        } else {
            RegisterResponse response = new RegisterResponse(false, 
                validationResult.getErrorCode(), 
                validationResult.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API está funcionando!");
    }
}
