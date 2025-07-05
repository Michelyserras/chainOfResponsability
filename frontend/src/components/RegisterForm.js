import React, { useState } from "react";
import { useRegister } from "../hooks/useRegister";

const RegisterForm = ({ onBackToLogin }) => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const { loading, error, success, user, registerUser, clearError } =
    useRegister();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    if (error) {
      clearError();
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await registerUser(formData);
  };

  // Se registro foi bem-sucedido
  if (success && user) {
    return (
      <div className="container">
        <div className="login-card">
          <div className="login-header">
            <h1>Usuário Criado com Sucesso</h1>
            <p>Bem-vindo ao sistema!</p>
          </div>

          <div className="success-message">
            Conta criada com sucesso! Todas as validações da cadeia passaram.
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

          <button onClick={onBackToLogin} className="login-button">
            Fazer Login
          </button>

          <div className="test-users">
            <h4>Cadeia de Validação de Criação Executada:</h4>
            <ul>
              <li>1. Verificação de unicidade do email</li>
              <li>2. Validação de complexidade da senha</li>
              <li>3. Confirmação de senha</li>
              <li>4. Validação do nome</li>
              <li>5. Criação do usuário</li>
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
          <h1>Criar Nova Conta</h1>
          <p>Sistema de Registro com Validação em Cadeia</p>
        </div>

        {error && <div className="error-message">{error}</div>}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="name">Nome Completo:</label>
            <input
              type="text"
              id="name"
              name="name"
              value={formData.name}
              onChange={handleChange}
              placeholder="Digite seu nome completo"
              required
            />
          </div>

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

          <div className="form-group">
            <label htmlFor="confirmPassword">Confirmar Senha:</label>
            <input
              type="password"
              id="confirmPassword"
              name="confirmPassword"
              value={formData.confirmPassword}
              onChange={handleChange}
              placeholder="Confirme sua senha"
              required
            />
          </div>

          <button type="submit" className="login-button" disabled={loading}>
            {loading ? (
              <>
                <span className="loading"></span>
                <span style={{ marginLeft: "10px" }}>Criando...</span>
              </>
            ) : (
              "Criar Conta"
            )}
          </button>
        </form>

        <button onClick={onBackToLogin} className="logout-button">
          Voltar para Login
        </button>

        <div className="test-users">
          <h4>Validações da Cadeia de Criação:</h4>
          <ul>
            <li>1. Email único (não pode estar em uso)</li>
            <li>
              2. Senha complexa (8+ chars, maiúscula, minúscula, número,
              especial)
            </li>
            <li>3. Confirmação de senha (deve coincidir)</li>
            <li>4. Nome válido (2-50 chars, apenas letras)</li>
            <li>5. Criação efetiva do usuário</li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default RegisterForm;
