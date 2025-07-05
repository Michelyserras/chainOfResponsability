import React, { useState } from "react";
import LoginForm from "./components/LoginForm";
import RegisterForm from "./components/RegisterForm";

function App() {
  const [currentView, setCurrentView] = useState("login");

  const showRegister = () => setCurrentView("register");
  const showLogin = () => setCurrentView("login");

  return (
    <div className="App">
      {currentView === "login" ? (
        <LoginForm onShowRegister={showRegister} />
      ) : (
        <RegisterForm onBackToLogin={showLogin} />
      )}
    </div>
  );
}

export default App;
