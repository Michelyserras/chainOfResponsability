# Chain of Responsibility - Sistema de Login

Este projeto demonstra a implementação do padrão **Chain of Responsibility** para validação de login, com backend em Spring Boot e frontend em React.

## 📋 Sobre o Projeto

O padrão Chain of Responsibility permite que você passe requests através de uma cadeia de handlers. Cada handler decide se processa o request ou o passa para o próximo handler na cadeia.

### 🔗 Cadeia de Validação Implementada

1. **EmailFormatValidationHandler** - Valida formato do email usando regex
2. **PasswordStrengthValidationHandler** - Valida força da senha (6+ caracteres, letra + número)
3. **UserExistsValidationHandler** - Verifica se usuário existe no "banco de dados"
4. **CredentialsValidationHandler** - Valida se as credenciais estão corretas

## 🚀 Como Executar

### Backend (Spring Boot)

1. Navegue para a pasta backend:

```bash
cd backend
```

2. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

Ou no Windows:

```bash
mvnw.cmd spring-boot:run
```

A API estará disponível em: `http://localhost:8080/api`

### Frontend (React)

1. Navegue para a pasta frontend:

```bash
cd frontend
```

2. Instale as dependências:

```bash
npm install
```

3. Execute a aplicação:

```bash
npm start
```

A aplicação estará disponível em: `http://localhost:3000`

## 👥 Usuários para Teste

O sistema possui os seguintes usuários pré-cadastrados:

- **admin@teste.com** / admin123 - Administrador
- **usuario@teste.com** / usuario123 - Usuário Comum
- **joao@email.com** / joao456 - João Silva
- **maria@email.com** / maria789 - Maria Santos

## 🏗️ Estrutura do Projeto

### Backend

```
backend/
├── src/main/java/com/example/chainofresponsibility/
│   ├── controller/         # Controllers REST
│   ├── dto/               # Data Transfer Objects
│   ├── model/             # Entidades
│   ├── service/           # Lógica de negócio
│   └── validation/        # Handlers da cadeia
└── pom.xml
```

### Frontend

```
frontend/
├── src/
│   ├── components/        # Componentes React
│   ├── hooks/            # Custom hooks
│   ├── services/         # Serviços para API
│   └── App.js
└── package.json
```

## 🔍 Como Funciona

1. **Frontend**: Usuário preenche o formulário de login
2. **API Call**: React envia dados para `/api/auth/login`
3. **Chain Execution**: Spring Boot executa a cadeia de validações:
   - Se alguma validação falha, retorna erro específico
   - Se todas passam, retorna sucesso com token e dados do usuário
4. **Response**: Frontend exibe resultado ao usuário

## ✨ Funcionalidades

- ✅ Validação em tempo real
- ✅ Mensagens de erro específicas para cada tipo de falha
- ✅ Interface moderna e responsiva
- ✅ Preenchimento automático com usuários de teste
- ✅ Logs detalhados no console do backend
- ✅ Padrão Chain of Responsibility aplicado corretamente

## 🎯 Objetivos Educacionais

Este projeto demonstra:

- **Padrão Chain of Responsibility**: Implementação prática e correta
- **Separação de Responsabilidades**: Cada handler tem uma responsabilidade específica
- **Extensibilidade**: Fácil adicionar/remover validações
- **Testabilidade**: Cada handler pode ser testado isoladamente
- **Integração Frontend/Backend**: Comunicação via REST API

## 🧪 Testando Validações

Experimente os seguintes casos para ver a cadeia em ação:

1. **Email inválido**: `usuario@` → Erro na 1ª validação
2. **Senha fraca**: `123` → Erro na 2ª validação
3. **Usuário inexistente**: `inexistente@teste.com` → Erro na 3ª validação
4. **Senha incorreta**: `admin@teste.com` + `senhaerrada` → Erro na 4ª validação
5. **Login válido**: `admin@teste.com` + `admin123` → Sucesso! ✅
