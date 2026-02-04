import { Routes } from '@angular/router';
import { Login } from './frontoffice/auth/login/login';
import { Register } from './frontoffice/auth/register/register';
import { Dashboard } from './backoffice/dashboard/dashboard';
import { Transactions } from './backoffice/transactions/transactions';
import { Accounts } from './backoffice/accounts/accounts';
import { Home } from './frontoffice/home/home';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'dashboard', component: Dashboard },
  { path: 'transactions', component: Transactions },
  { path: 'accounts', component: Accounts },
  { path: 'frontoffice', component: Home },
  { path: '**', redirectTo: '/' }
];