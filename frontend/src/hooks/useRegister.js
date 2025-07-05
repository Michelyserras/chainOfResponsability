import { useState } from "react";
import { authService } from "../services/authService";

export const useRegister = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);
  const [user, setUser] = useState(null);

  const registerUser = async (userData) => {
    setLoading(true);
    setError("");
    setSuccess(false);

    try {
      const result = await authService.register(userData);

      if (result.success) {
        setSuccess(true);
        setUser(result.data.user);
      } else {
        setError(result.error.message || "Erro no registro");
      }
    } catch (err) {
      setError("Erro inesperado durante o registro");
    } finally {
      setLoading(false);
    }
  };

  const clearError = () => {
    setError("");
  };

  const reset = () => {
    setLoading(false);
    setError("");
    setSuccess(false);
    setUser(null);
  };

  return {
    loading,
    error,
    success,
    user,
    registerUser,
    clearError,
    reset,
  };
};
