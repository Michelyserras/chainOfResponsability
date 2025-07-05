package com.example.chainofresponsibility.dto;

import com.example.chainofresponsibility.model.User;

public class RegisterResponse {
    private boolean success;
    private String message;
    private User user;
    private String error;

    public RegisterResponse(boolean success) {
        this.success = success;
    }

    public RegisterResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public RegisterResponse(boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public RegisterResponse(boolean success, String error, String message) {
        this.success = success;
        this.error = error;
        this.message = message;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
