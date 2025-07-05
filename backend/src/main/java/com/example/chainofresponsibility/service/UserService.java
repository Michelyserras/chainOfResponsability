package com.example.chainofresponsibility.service;

import com.example.chainofresponsibility.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    public UserService() {
        // Inicializa o "banco de dados" em memória com usuários de exemplo
        users.add(new User(1L, "admin@teste.com", "admin123", "Administrador"));
        users.add(new User(2L, "usuario@teste.com", "usuario123", "Usuário Comum"));
        users.add(new User(3L, "joao@email.com", "joao456", "João Silva"));
        users.add(new User(4L, "maria@email.com", "maria789", "Maria Santos"));
    }

    public User findByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public boolean userExists(String email) {
        return findByEmail(email) != null;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
