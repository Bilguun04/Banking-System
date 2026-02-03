# Banking System - Spring Boot Backend

A Spring Boot REST API for managing banking operations including branches and bank accounts.

## Features

- **Branch Management**: Create, read, update, and delete bank branches
- **Bank Account Management**: Manage bank accounts with associated branches
- **RESTful API**: Complete REST endpoints for all operations
- **CORS Support**: Configured for Angular frontend communication
- **JPA/Hibernate**: Database operations with ORM
- **H2 Database**: In-memory database for development (can be switched to MySQL)

## Project Structure

```
backend/
├── src/main/java/com/banking/
│   ├── BankingSystemApplication.java    # Main Spring Boot application
│   ├── controller/                       # REST Controllers
│   │   ├── BranchController.java
│   │   └── BankAccountController.java
│   ├── entity/                           # JPA Entities
│   │   ├── Branch.java
│   │   └── BankAccount.java
│   ├── service/                          # Business Logic
│   │   ├── BranchService.java
│   │   └── BankAccountService.java
│   └── repository/                       # Data Access Layer
│       ├── BranchRepository.java
│       └── BankAccountRepository.java
├── src/main/resources/
│   └── application.properties            # Application Configuration
├── pom.xml                               # Maven Configuration
└── README.md                             # This file
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MySQL 8.0 (optional - H2 included for development)

## Installation

### 1. Navigate to Backend Directory
```bash
cd backend
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The server will start on `http://localhost:8080`

### 4. Access H2 Console (Development)
```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:bankingdb
Username: sa
Password: (leave blank)
```

## API Endpoints

### Branches

- **GET** `/api/branches` - Get all branches
- **GET** `/api/branches/{id}` - Get a specific branch
- **POST** `/api/branches` - Create a new branch
- **PUT** `/api/branches/{id}` - Update a branch
- **DELETE** `/api/branches/{id}` - Delete a branch

### Bank Accounts

- **GET** `/api/accounts` - Get all accounts
- **GET** `/api/accounts/{id}` - Get a specific account
- **GET** `/api/accounts/branch/{branchId}` - Get accounts by branch
- **POST** `/api/accounts` - Create a new account
- **PUT** `/api/accounts/{id}` - Update an account
- **DELETE** `/api/accounts/{id}` - Delete an account

## Example Requests

### Create a Branch
```bash
curl -X POST http://localhost:8080/api/branches \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Main Branch",
    "address": "123 Main St",
    "phone": "555-1234",
    "code": "MAIN001"
  }'
```

### Create a Bank Account
```bash
curl -X POST http://localhost:8080/api/accounts \
  -H "Content-Type: application/json" \
  -d '{
    "accountNumber": "ACC123456",
    "accountHolder": "John Doe",
    "balance": 5000.00,
    "accountType": "SAVINGS",
    "currency": "USD",
    "active": true,
    "branchId": 1
  }'
```

## Database Configuration

### Development (H2)
Default configuration in `application.properties` uses H2 in-memory database.

### Production (MySQL)
To use MySQL, update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## Building for Production

```bash
mvn clean package -DskipTests
java -jar target/banking-system-backend-1.0.0.jar
```

## Technologies Used

- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Security**
- **Hibernate ORM**
- **H2 Database**
- **Lombok**
- **Maven**
- **Java 17**

## License

This project is licensed under the MIT License.
