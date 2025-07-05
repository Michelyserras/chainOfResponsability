package com.example.chainofresponsibility.validation;

import com.example.chainofresponsibility.dto.LoginRequest;
import com.example.chainofresponsibility.dto.ValidationResult;

public abstract class LoginValidationHandler {
    protected LoginValidationHandler nextHandler;

    public void setNext(LoginValidationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract ValidationResult handle(LoginRequest request);

    protected ValidationResult passToNext(LoginRequest request) {
        if (nextHandler != null) {
            return nextHandler.handle(request);
        }
        return new ValidationResult(true);
    }
}
