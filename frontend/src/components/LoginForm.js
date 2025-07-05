import React, { useState } from "react";
import { useLogin } from "../hooks/useLogin";

const LoginForm = ({ onShowRegister }) => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const { loading, error, success, user, login, logout, clearError } =
    useLogin();

  const testUsers = [
    { email: "admin@teste.com", password: "admin123", name: "Administrador" },
    {
      email: "usuario@teste.com",
      password: "usuario123",
      name: "Usuário Comum",
    },
    { email: "joao@email.com", password: "joao456", name: "João Silva" },
    { email: "maria@email.com", password: "maria789", name: "Maria Santos" },
  ];

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    // Limpa erro quando usuário começa a digitar
    if (error) {
      clearError();
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await login(formData);
  };

  const fillTestUser = (testUser) => {
    setFormData({
      email: testUser.email,
      password: testUser.password,
    });
  };

  // Se logado com sucesso, mostra informações do usuário
  if (success && user) {
    return (
      <div className="container">
        <div className="login-card">
          <div className="login-header">
            <h1>Login Realizado</h1>
            <p>Bem-vindo ao sistema!</p>
          </div>

          <div className="success-message">
            Login realizado com sucesso! Todas as validações da cadeia passaram.
          </div>

          <div className="user-info">
            <h3>Informações do Usuário:</h3>
            <p>
              <strong>ID:</strong> {user.id}
            </p>
            <p>
              <strong>Nome:</strong> {user.name}
            </p>
            <p>
              <strong>Email:</strong> {user.email}
            </p>
          </div>

          <button onClick={logout} className="logout-button">
            Fazer Logout
          </button>

          <div className="test-users">
            <h4>Cadeia de Validação Executada:</h4>
            <ul>
              <li>1. Validação de formato do email</li>
              <li>2. Validação de força da senha</li>
              <li>3. Verificação se usuário existe</li>
              <li>4. Validação das credenciais</li>
            </ul>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="container">
      <div className="login-card">
        <div className="login-header">
          <h1>Chain of Responsibility</h1>
          <p>Sistema de Login com Validação em Cadeia</p>
        </div>

        {error && <div className="error-message">{error}</div>}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              id="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              placeholder="Digite seu email"
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">Senha:</label>
            <input
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              placeholder="Digite sua senha"
              required
            />
          </div>

          <button type="submit" className="login-button" disabled={loading}>
            {loading ? (
              <>
                <span className="loading"></span>
                <span style={{ marginLeft: "10px" }}>Validando...</span>
              </>
            ) : (
              "Entrar"
            )}
          </button>
        </form>

        <button
          onClick={onShowRegister}
          className="logout-button"
          style={{ marginBottom: "20px" }}
        >
          Criar Nova Conta
        </button>

        <div className="test-users">
          <h4>Usuários para Teste (clique para preencher):</h4>
          {testUsers.map((user, index) => (
            <li key={index} onClick={() => fillTestUser(user)}>
              <strong>{user.email}</strong> / {user.password} - {user.name}
            </li>
          ))}
        </div>

        <div className="test-users">
          <h4>Validações da Cadeia:</h4>
          <ul>
            <li>1. Formato do email (regex)</li>
            <li>2. Força da senha (6+ chars, letra + número)</li>
            <li>3. Usuário existe no banco</li>
            <li>4. Credenciais corretas</li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default LoginForm;
