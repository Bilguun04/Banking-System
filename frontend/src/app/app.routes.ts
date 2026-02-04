import { Routes } from '@angular/router';
import { Login } from './features/auth/login/login';
import { Register } from './features/auth/register/register';
import { Dashboard } from './features/dashboard/dashboard';
import { Transactions } from './features/transactions/transactions';
import { Accounts } from './features/accounts/accounts';

export const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'dashboard', component: Dashboard },
  { path: 'transactions', component: Transactions },
  { path: 'accounts', component: Accounts },
  { path: '**', redirectTo: '/dashboard' }
];