package com.example.chainofresponsibility.service;

import com.example.chainofresponsibility.dto.CreateUserRequest;
import com.example.chainofresponsibility.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(5); // Próximo ID disponível

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

    public User createUser(CreateUserRequest request) {
        Long newId = idGenerator.getAndIncrement();
        User newUser = new User(newId, request.getEmail(), request.getPassword(), request.getName());
        users.add(newUser);

        System.out.println("Usuário salvo no banco de dados com ID: " + newId);
        return newUser;
    }

    public int getUserCount() {
        return users.size();
    }
}
