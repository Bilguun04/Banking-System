# Banking System - Angular Frontend

An Angular 17 application for managing banking operations with a user-friendly interface.

## Features

- **Branch Management**: View, create, update, and delete bank branches
- **Account Management**: Manage bank accounts with full CRUD operations
- **Responsive Design**: Mobile-friendly interface
- **Real-time Updates**: Communicates with Spring Boot backend API
- **Standalone Components**: Uses Angular's latest standalone components

## Project Structure

```
frontend/
├── src/
│   ├── app/
│   │   ├── components/                   # Reusable components (to be added)
│   │   ├── models/                       # TypeScript interfaces
│   │   │   ├── branch.model.ts
│   │   │   └── bank-account.model.ts
│   │   ├── services/                     # HTTP services
│   │   │   ├── branch.service.ts
│   │   │   └── bank-account.service.ts
│   │   ├── app.component.ts              # Root component
│   │   ├── app.component.html
│   │   ├── app.component.css
│   │   └── app.routes.ts                 # Route configuration
│   ├── main.ts                           # Application entry point
│   ├── index.html                        # HTML template
│   ├── styles.css                        # Global styles
│   └── favicon.ico
├── angular.json                          # Angular configuration
├── package.json                          # NPM dependencies
├── tsconfig.json                         # TypeScript configuration
└── README.md                             # This file
```

## Prerequisites

- Node.js 18.x or higher
- npm 9.x or higher
- Angular CLI 17.x

## Installation

### 1. Navigate to Frontend Directory
```bash
cd frontend
```

### 2. Install Dependencies
```bash
npm install
```

### 3. Start Development Server
```bash
npm start
```

The application will be available at `http://localhost:4200`

### 4. Build for Production
```bash
npm run build
```

Production files will be in the `dist/` directory.

## Development

### Run Development Server
```bash
ng serve
# or
npm start
```

Open `http://localhost:4200/` in your browser.

### Build
```bash
ng build
```

### Run Tests
```bash
ng test
```

### Run Linter
```bash
ng lint
```

## Services

### BranchService
Handles all branch-related API calls.
- `getAllBranches()` - Get all branches
- `getBranchById(id)` - Get a specific branch
- `createBranch(branch)` - Create new branch
- `updateBranch(id, branch)` - Update branch
- `deleteBranch(id)` - Delete branch

### BankAccountService
Handles all bank account-related API calls.
- `getAllAccounts()` - Get all accounts
- `getAccountById(id)` - Get specific account
- `getAccountsByBranch(branchId)` - Get accounts by branch
- `createAccount(account)` - Create new account
- `updateAccount(id, account)` - Update account
- `deleteAccount(id)` - Delete account

## Models

### Branch
```typescript
interface Branch {
  id?: number;
  name: string;
  address: string;
  phone: string;
  code: string;
}
```

### BankAccount
```typescript
interface BankAccount {
  id?: number;
  accountNumber: string;
  accountHolder: string;
  balance: number;
  accountType: string;
  currency: string;
  active: boolean;
  branchId: number;
}
```

## API Configuration

The frontend is configured to communicate with the Spring Boot backend at:
```
http://localhost:8080/api
```

To change the API URL, update the service files:
- [bank-account.service.ts](src/app/services/bank-account.service.ts)
- [branch.service.ts](src/app/services/branch.service.ts)

## Environment Setup

### Development
Default configuration works with backend running on `http://localhost:8080`

### Production
Update the API base URL in service files for production deployment.

## Technologies Used

- **Angular 17**
- **TypeScript 5.2**
- **RxJS 7.8**
- **Angular Router**
- **HttpClient**
- **Bootstrap/CSS** (can be enhanced)

## Future Enhancements

- [ ] Add Branch list and management components
- [ ] Add Bank Account list and management components
- [ ] Implement user authentication
- [ ] Add transaction history
- [ ] Implement date pickers for transactions
- [ ] Add form validation and error handling
- [ ] Implement state management (NgRx)
- [ ] Add unit and integration tests
- [ ] Implement responsive tables with pagination
- [ ] Add search and filter functionality

## License

This project is licensed under the MIT License.

## Getting Started

1. **Start the Backend**:
   ```bash
   cd backend
   mvn spring-boot:run
   ```

2. **Start the Frontend**:
   ```bash
   cd frontend
   npm install
   npm start
   ```

3. **Access the Application**:
   Open `http://localhost:4200` in your browser.

## Support

For issues or questions, please refer to the project documentation or create an issue in the repository.
