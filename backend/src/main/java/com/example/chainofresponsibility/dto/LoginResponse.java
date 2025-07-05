package com.example.chainofresponsibility.dto;

import com.example.chainofresponsibility.model.User;

public class LoginResponse {
    private boolean success;
    private String token;
    private User user;
    private String error;
    private String message;

    public LoginResponse(boolean success) {
        this.success = success;
    }

    public LoginResponse(boolean success, String token, User user) {
        this.success = success;
        this.token = token;
        this.user = user;
    }

    public LoginResponse(boolean success, String error, String message) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
