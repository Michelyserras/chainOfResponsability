package com.example.chainofresponsibility.dto;

import com.example.chainofresponsibility.model.User;

public class ValidationResult {
    private boolean success;
    private String errorCode;
    private String message;
    private User user;

    public ValidationResult(boolean success) {
        this.success = success;
    }

    public ValidationResult(boolean success, String errorCode, String message) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
    }

    public ValidationResult(boolean success, User user) {
        this.success = success;
        this.user = user;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
