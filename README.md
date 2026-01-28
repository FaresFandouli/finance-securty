# Secure AI-Powered Financial Intelligence System

## 🎯 Project Overview

A comprehensive Spring Boot application implementing an AI-powered secure financial intelligence system for Tunisian SMEs. This system includes fraud detection, risk assessment, health monitoring, and secure REST APIs.

## 📋 Features Implemented

### ✅ Core Features
- ✔️ User Management with JWT Authentication
- ✔️ Transaction Management System
- ✔️ AI-Powered Fraud Detection (DJL, ONNX Runtime)
- ✔️ Credit Risk Assessment
- ✔️ Health Monitoring with AI Agent
- ✔️ Role-Based Access Control (RBAC)

### 🔐 Security Features
- ✔️ JWT Token Authentication
- ✔️ BCrypt Password Hashing (cost factor 12)
- ✔️ Role-based authorization
- ✔️ Input validation
- ✔️ CORS configuration
- ✔️ Exception handling with secure error messages
- ✔️ Method-level security

### 🤖 AI Components
- ✔️ DJL Fraud Detector
- ✔️ ONNX Fraud Detector
- ✔️ Ensemble fraud detection
- ✔️ Credit risk assessment
- ✔️ Health monitoring agent

## 🚀 Quick Start

### Prerequisites
- Java 21 or higher
- Maven 3.8+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Installation

1. **Clone/Extract the project**
   ```bash
   cd financial-security-system
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Application: http://localhost:8080
   - Health Check: http://localhost:8080/actuator/health

## 📊 Database Configuration



## 🔑 API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login
- `GET /api/auth/me` - Get current user

### Transactions
- `POST /api/transactions/create` - Create transaction (with fraud detection)
- `GET /api/transactions/gettransaction/{id}` - Get transaction
- `GET /api/transactions/all` - Get all (Admin/Analyst only)
- `GET /api/transactions/my-transactions` - Get user's transactions

## 🧪 Testing the API

### 1. Register a User

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "Test@1234",
    "fullName": "Test User",
    "role": "SME_USER"
  }'
```

### 2. Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "Test@1234"
  }'
```

Save the returned `accessToken` for authenticated requests.

### 3. Create Transaction

```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_ACCESS_TOKEN" \
  -d '{
    "amount": 5000.00,
    "type": "PAYMENT",
    "description": "Test payment",
    "merchantName": "Test Merchant",
    "location": "Tunis"
  }'
```

## 👥 User Roles

- **ADMIN** - Full system access
- **FINANCIAL_ANALYST** - Can view all transactions and analytics
- **SME_USER** - Can create and view own transactions
- **AUDITOR** - Read-only access to audit logs

## 📁 Project Structure

```
src/main/java/com/tunisia/financial/
├── FinancialApplication.java          # Main application
├── config/                            # Configuration classes
│   ├── SecurityConfig.java
│   └── CacheConfig.java
├── controller/                        # REST controllers
│   ├── AuthController.java
│   └── TransactionController.java
├── service/                           # Service layer
│   ├── AuthService.java
│   ├── TransactionService.java
│   └── impl/                          # Implementations
├── repository/                        # Data repositories
│   ├── UserRepository.java
│   ├── TransactionRepository.java
│   └── FraudPatternRepository.java
├── entity/                            # JPA entities
│   ├── BaseEntity.java
│   ├── User.java
│   ├── Transaction.java
│   └── FraudPattern.java
├── dto/                               # Data Transfer Objects
│   ├── request/
│   └── response/
├── security/                          # Security components
│   ├── JwtTokenProvider.java
│   ├── JwtAuthenticationFilter.java
│   └── CustomUserDetailsService.java
├── ai/                                # AI components
│   ├── fraud/                         # Fraud detection
│   ├── risk/                          # Risk assessment
│   └── monitoring/                    # Health monitoring
├── enums/                             # Enumerations
├── exception/                         # Exception handling
└── validation/                        # Custom validators
```

## 🔧 Configuration

Key configuration properties in `application.properties`:

```properties
# Server
server.port=8080

# JWT
jwt.secret=YOUR_SECRET_KEY
jwt.expiration=86400000

# Logging
logging.level.com.tunisia.financial=DEBUG
```

## 🎓 Key Technologies

- **Spring Boot 3.3.0**
- **Spring Security 6.5**
- **JWT Authentication**
- **Spring Data JPA**
- **MYSQl**
- **Lombok**
- **Bean Validation**
- **AI Libraries**: DJL, ONNX Runtime
- **Java 21**

## 📝 Java Features Demonstrated

- ✔️ **Records** - Used for immutable DTOs
- ✔️ **Enumerations** - Type-safe enums with methods
- ✔️ **Interfaces** - Service layer abstraction
- ✔️ **Functional Interfaces** - For AI strategies
- ✔️ **Inheritance** - BaseEntity for common fields
- ✔️ **Polymorphism** - Service implementations
- ✔️ **Encapsulation** - Private fields with Lombok
- ✔️ **Annotations** - Custom validation annotations
- ✔️ **Exception Handling** - Hierarchical exceptions

## 🛡️ Security Best Practices Implemented

1. ✔️ JWT-based stateless authentication
2. ✔️ BCrypt password hashing (cost factor 12)
3. ✔️ Role-based access control (RBAC)
4. ✔️ Method-level security with @PreAuthorize
5. ✔️ Input validation using Bean Validation
6. ✔️ Secure error messages (no sensitive data leaks)
7. ✔️ CORS configuration
8. ✔️ SQL injection prevention (JPA with parameterized queries)
9. ✔️ Exception handling with GlobalExceptionHandler

## 📊 AI Features

### Fraud Detection
- Ensemble approach using multiple AI models
- DJL-based detection
- ONNX Runtime integration
- Confidence scoring
- Real-time transaction analysis

### Risk Assessment
- Credit risk scoring
- Risk category classification (LOW, MEDIUM, HIGH, CRITICAL)
- Intelligent recommendations
- Historical data analysis

### Health Monitoring
- System metrics collection
- AI-powered anomaly detection
- Scheduled health checks (every minute)
- Intelligent alerting



##  Author

**Fares Fandouli**
- Student at Sesame University
- Secure Programming Project
- Academic Year 2025-2026


---
