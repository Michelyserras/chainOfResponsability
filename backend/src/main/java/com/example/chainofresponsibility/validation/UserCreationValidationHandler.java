package com.example.chainofresponsibility.validation;

import com.example.chainofresponsibility.dto.CreateUserRequest;
import com.example.chainofresponsibility.dto.ValidationResult;

public abstract class UserCreationValidationHandler {
    protected UserCreationValidationHandler nextHandler;

    public void setNext(UserCreationValidationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract ValidationResult handle(CreateUserRequest request);

    protected ValidationResult passToNext(CreateUserRequest request) {
        if (nextHandler != null) {
            return nextHandler.handle(request);
        }
        return new ValidationResult(true);
    }
}
