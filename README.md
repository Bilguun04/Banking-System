# 🏦 Banking System - Full Stack Application

A complete full-stack banking application with Spring Boot backend and Angular frontend, fully containerized with Docker.

## ✨ What's Included

### Backend (Spring Boot)
✅ Spring Boot 3.2.0 REST API
✅ Spring Data JPA with Hibernate ORM
✅ Spring Data Redis for Caching
✅ H2 In-Memory Database (Development)
✅ MySQL 8.0 Support (Production/Docker)
✅ Spring Security Configuration
✅ CORS Support for Frontend
✅ Complete REST Controllers
✅ Service Layer Architecture
✅ Repository Pattern for Data Access

### Frontend (Angular)
✅ Angular 17 with Standalone Components

### Option: Local Development (Without Docker)

#### 1. Start Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```
**Server:** http://localhost:8080

#### 2. Start Frontend (New Terminal)
```bash
cd frontend
npm install
npm start
```
**App:** http://localhost:4200

## 📁 Project Structure

```
Banking-System/
├── backend/
│   ├── src/main/java/com/banking/
│   │   ├── BankingSystemApplication.java
│   │   ├── controller/
│   │   │   ├── BranchController.java
│   │   │   └── BankAccountController.java
│   │   ├── entity/
│   │   │   ├── Branch.java
│   │   │   └── BankAccount.java
│   │   ├── service/
│   │   │   ├── BranchService.java
│   │   │   └── BankAccountService.java
│   │   └── repository/
│   │       ├── BranchRepository.java
│   │       └── BankAccountRepository.java
│   ├── src/main/resources/
│   │   ├── application.properties
│   │   ├── application-docker.properties
│   │   └── ...
│   ├── src/main/java/com/banking/config/
│   │   └── RedisConfig.java
│   ├── pom.xml
│   ├── Dockerfile
│   ├── .gitignore
│   └── README.md
├── frontend/
│   ├── src/
│   │   ├── app/
│   │   │   ├── models/
│   │   │   │   ├── branch.model.ts
│   │   │   │   └── bank-account.model.ts
│   │   │   ├── services/
│   │   │   │   ├── branch.service.ts
│   │   │   │   └── bank-account.service.ts
│   │   │   ├── app.component.ts
│   │   │   ├── app.component.html
│   │   │   ├── app.component.css
│   │   │   ├── app.routes.ts
│   │   ├── main.ts
│   │   ├── index.html
│   │   ├── styles.css
│   ├── package.json
│   ├── angular.json
│   ├── tsconfig.json
│   ├── Dockerfile
│   ├── nginx.conf
│   ├── .gitignore
│   └── README.md
├── compose.yml         # Multi-container orchestration
├── DOCKER.md                  # Docker deployment guide
├── SETUP.md                   # Local development setup
└── README.md                  # This file
```

## 🐳 Docker Setup

The entire application is containerized with all services:

**Services included:**
- **Backend**: Spring Boot on Java 17
- **Frontend**: Angular with Nginx
- **MySQL**: Database (8.0-alpine)
- **Redis**: Cache layer (7-alpine)

**Quick start:**
```bash
docker compose up --build
```

For detailed Docker documentation, see [DOCKER.md](DOCKER.md)

## 🔌 API Endpoints

### Branches
- `GET /api/branches` - Get all branches
- `GET /api/branches/{id}` - Get specific branch
- `POST /api/branches` - Create branch
- `PUT /api/branches/{id}` - Update branch
- `DELETE /api/branches/{id}` - Delete branch

### Bank Accounts
- `GET /api/accounts` - Get all accounts
- `GET /api/accounts/{id}` - Get specific account
- `GET /api/accounts/branch/{branchId}` - Get accounts by branch
- `POST /api/accounts` - Create account
- `PUT /api/accounts/{id}` - Update account
- `DELETE /api/accounts/{id}` - Delete account


## 📦 Tech Stack

### 🐳 Containerization
- **Docker** 20.10+
- **Docker Compose** 2.0+
- **Nginx** (for frontend serving)

### 🗄️ Database
- **MySQL 8.0** (Production)
- **H2** (In-memory, Development)
- **Redis 7** (Cache Layer)

### 🔙 Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA** (with Hibernate ORM)
- **Spring Data Redis**
- **Spring Security**
- **Lombok**
- **JUnit 5** & **Cucumber** (Testing)

### 🔜 Frontend
- **Angular 17**
- **TypeScript 5.2**
- **RxJS 7.8**
- **HTML5/CSS3**
- **npm**

---

## 📁 Project Structure

```
Banking-System/
├── ARCHITECTURE.md
├── bankingsystem.ump
├── compose.yml
├── index.html
├── LICENSE
├── package.json
├── pom.xml
├── README.md
├── backend/
│   ├── build.gradle
│   ├── Dockerfile
│   ├── gradle.properties
│   ├── gradlew
│   ├── gradlew.bat
│   ├── README.md
│   ├── settings.gradle
│   ├── build/
│   ├── gradle/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── banking/
│   │   │   │           ├── BankingSystemApplication.java
│   │   │   │           ├── config/
│   │   │   │           ├── controller/
│   │   │   │           ├── dto/
│   │   │   │           ├── entity/
│   │   │   │           ├── repository/
│   │   │   │           └── service/
│   │   │   └── resources/
│   │   │       ├── application-docker.properties
│   │   │       └── application.properties
│   │   └── test/
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── banking/
│   │       │           ├── CucumberSpringConfiguration.java
│   │       │           ├── CucumberTest.java
│   │       │           └── steps/
│   │       └── resources/
│   │           └── features/
│   │               ├── customer.feature
│   │               ├── moderator.feature
│   │               └── staff.feature
├── frontend/
│   ├── angular.json
│   ├── Dockerfile
│   ├── Dockerfile.dev
│   ├── nginx.conf
│   ├── package.json
│   ├── README.md
│   ├── tsconfig.app.json
│   ├── tsconfig.json
│   ├── tsconfig.spec.json
│   ├── public/
│   └── src/
│       ├── index.html
│       ├── main.ts
│       ├── styles.css
│       └── app/
│           ├── app.config.ts
│           ├── app.css
│           ├── app.html
│           ├── app.routes.ts
│           ├── app.spec.ts
│           ├── app.ts
│           ├── backoffice/
│           │   ├── accounts/
│           │   ├── dashboard/
│           │   └── transactions/
│           ├── frontoffice/
│           │   ├── about/
│           │   ├── auth/
│           │   ├── features/
│           │   └── home/
│           ├── services/
│           │   └── auth.service.ts
│           └── shared/
│               ├── footer/
│               ├── header/
│               ├── navbar/
│               └── sidebar/
```

## 🛠️ Installation Requirements

**For Docker (Recommended):**
- Docker 20.10+
- Docker Compose 2.0+
- 4GB RAM minimum

**For Local Development:**
- **Backend**: Java 17+, Maven 3.6+
- **Frontend**: Node.js 18.x+, npm 9.x+
- **Database**: MySQL 8.0 (optional, H2 for dev)
- **Cache**: Redis 7 (optional for local dev)

- Angular CLI 17.x

## 📖 Documentation

- **[SETUP.md](SETUP.md)** - Detailed setup and deployment guide
- **[backend/README.md](backend/README.md)** - Backend documentation
- **[frontend/README.md](frontend/README.md)** - Frontend documentation

## 🎯 Next Steps

1. **Run Both Applications** - Follow Quick Start section
2. **Test APIs** - Use curl, Postman, or the Angular frontend
3. **Create Components** - Add branch and account list components
4. **Add Features** - Implement transactions, reports, etc.
5. **Deploy** - Follow deployment guides in SETUP.md

## 🔒 Security Features

- CORS Configuration
- Spring Security Ready
- Input Validation Ready
- Password Encryption Ready

## 📝 Example Usage

### Create a Branch via cURL
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

### Create an Account via cURL
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

## 🐛 Troubleshooting

See **[SETUP.md](SETUP.md#troubleshooting)** for common issues and solutions.

## 📱 Development Tips

1. Use Chrome DevTools for frontend debugging
2. Use IntelliJ IDEA for backend development
3. Enable hot reload for faster development
4. Use H2 Console to inspect database
5. Check browser console for API errors

## 🤝 Contributing

1. Create feature branches
2. Write clear commit messages
3. Update documentation
4. Test thoroughly
5. Create pull requests

## 📄 License

MIT License - See LICENSE file

## 🎓 Learning Resources

- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Angular Docs](https://angular.io/docs)
- [REST API Guide](https://restfulapi.net/)
- [TypeScript Handbook](https://www.typescriptlang.org/docs/)

---

**Ready to start?** Follow the [Quick Start](#-quick-start) section above!

For detailed setup instructions, see **[SETUP.md](SETUP.md)**

Happy Coding! 🚀
