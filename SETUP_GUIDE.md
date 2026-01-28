# Quick Setup Guide

## Prerequisites Check

Before starting, ensure you have:
- [ ] Java 21 or higher installed
- [ ] Maven 3.8+ installed
- [ ] An IDE (IntelliJ IDEA recommended)
- [ ] Git (optional)

## Verify Installation

```bash
# Check Java version
java -version
# Should show: java version "21" or higher

# Check Maven version
mvn -version
# Should show: Apache Maven 3.8.x or higher
```

## Step-by-Step Setup

### 1. Extract/Open Project

Extract the `financial-security-system` folder to your workspace.

### 2. Open in IDE

**IntelliJ IDEA:**
1. File → Open
2. Select the `financial-security-system` folder
3. Wait for Maven dependencies to download (may take 2-5 minutes)

**VS Code:**
1. Open folder in VS Code
2. Install "Extension Pack for Java"
3. Maven will auto-detect pom.xml

**Eclipse:**
1. File → Import → Existing Maven Projects
2. Select root directory
3. Finish

### 3. Build the Project

```bash
cd financial-security-system
mvn clean install
```

Expected output:
```
[INFO] BUILD SUCCESS
[INFO] Total time: XX s
```

### 4. Run the Application

**Option A: Using Maven**
```bash
mvn spring-boot:run
```

**Option B: Using IDE**
- Run `FinancialApplication.java` main method

**Option C: Using JAR**
```bash
mvn clean package
java -jar target/financial-security-1.0.0.jar
```

### 5. Verify Application is Running

You should see:
```
==============================================
Financial Security System Started Successfully
Server running on: http://localhost:8080
==============================================
```

### 6. Test the Application

Open browser and go to:
- Health Check: http://localhost:8080/actuator/health
- H2 Console: http://localhost:8080/h2-console

## Quick API Test

### Test 1: Register a User

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "jiji",
    "email": "jiji@sesame.tn",
    "password": "Secure@123",
    "fullName": "Jiji Student",
    "role": "SME_USER"
  }'
```

Expected response:
```json
{
  "accessToken": "eyJhbGc...",
  "refreshToken": "eyJhbGc...",
  "tokenType": "Bearer",
  "user": {
    "id": 1,
    "username": "jiji",
    "email": "jiji@sesame.tn",
    ...
  }
}
```

### Test 2: Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "jiji@sesame.tn",
    "password": "Secure@123"
  }'
```

### Test 3: Create Transaction (with fraud detection)

**Important:** Replace `YOUR_TOKEN` with the accessToken from login response.

```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{
    "amount": 12000.00,
    "type": "PAYMENT",
    "description": "Large payment - should trigger fraud detection",
    "merchantName": "Test Merchant",
    "location": "Tunis"
  }'
```

Expected response:
```json
{
  "id": 1,
  "type": "PAYMENT",
  "status": "FRAUD_DETECTED",  // or "COMPLETED"
  "amount": 12000.00,
  "fraudScore": 0.85,
  "isFraud": true,
  ...
}
```

## Common Issues & Solutions

### Issue 1: Port 8080 Already in Use

**Solution:** Change port in `application.properties`
```properties
server.port=8081
```

### Issue 2: Maven Dependencies Not Downloading

**Solution:**
```bash
mvn clean install -U
```

Or delete `.m2/repository` folder and rebuild.

### Issue 3: Java Version Mismatch

**Solution:** Ensure JAVA_HOME points to Java 21:
```bash
export JAVA_HOME=/path/to/java-21
```

## Using Postman

1. **Import Collection:**
   - Create new collection "Financial Security API"
   - Add requests as shown above

2. **Environment Variables:**
   - Create variable: `baseUrl` = `http://localhost:8080`
   - Create variable: `token` = (paste token after login)

3. **Authorization:**
   - Type: Bearer Token
   - Token: {{token}}

## Testing All Features

### User Management
- ✅ Register with different roles (ADMIN, SME_USER, FINANCIAL_ANALYST)
- ✅ Login and receive JWT token
- ✅ Get current user info

### Transaction Management
- ✅ Create transaction (tests fraud detection automatically)
- ✅ Get transaction by ID
- ✅ Get user's transactions
- ✅ Get all transactions (admin only)

### Fraud Detection
- ✅ Automatic fraud detection on each transaction
- ✅ Different amounts trigger different fraud scores
- ✅ High amounts (>10000) trigger higher fraud scores

### Role-Based Access
- ✅ Admin can access all endpoints
- ✅ SME_USER can only access own transactions
- ✅ Proper 403 Forbidden for unauthorized access

## Database Access


### View Tables
```sql
-- View all users
SELECT * FROM USERS;

-- View all transactions
SELECT * FROM TRANSACTIONS;

-- View transactions with fraud detected
SELECT * FROM TRANSACTIONS WHERE IS_FRAUD = TRUE;
```

## Next Steps

1. **Test all endpoints** using Postman or curl
2. **Check H2 database** to see data
3. **Try different user roles** to test authorization
4. **Create high-value transactions** to trigger fraud detection
5. **Check application logs** to see fraud detection in action

## Project Structure Quick Reference

```
financial-security-system/
├── src/main/java/com/tunisia/financial/
│   ├── FinancialApplication.java    ← Start here
│   ├── controller/                  ← REST endpoints
│   ├── service/                     ← Business logic
│   ├── repository/                  ← Database access
│   ├── entity/                      ← Data models
│   ├── security/                    ← JWT & Security
│   └── ai/                          ← AI components
├── src/main/resources/
│   └── application.properties       ← Configuration
├── pom.xml                          ← Dependencies
└── README.md                        ← Full documentation
```





---

