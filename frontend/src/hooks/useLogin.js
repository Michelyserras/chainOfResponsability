import { useState } from "react";
import { authService } from "../services/authService";

export const useLogin = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(null);

  const login = async (credentials) => {
    setLoading(true);
    setError("");
    setSuccess(false);

    try {
      const result = await authService.login(credentials);

      if (result.success) {
        setSuccess(true);
        setUser(result.data.user);
        setToken(result.data.token);

        // Salva o token no localStorage (opcional)
        localStorage.setItem("token", result.data.token);
        localStorage.setItem("user", JSON.stringify(result.data.user));
      } else {
        setError(result.error.message || "Erro no login");
      }
    } catch (err) {
      setError("Erro inesperado durante o login");
    } finally {
      setLoading(false);
    }
  };

  const logout = () => {
    setUser(null);
    setToken(null);
    setSuccess(false);
    localStorage.removeItem("token");
    localStorage.removeItem("user");
  };

  const clearError = () => {
    setError("");
  };

  return {
    loading,
    error,
    success,
    user,
    token,
    login,
    logout,
    clearError,
  };
};
