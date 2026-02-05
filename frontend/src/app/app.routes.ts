import { Routes } from '@angular/router';
import { Login } from './frontoffice/auth/login/login';
import { Register } from './frontoffice/auth/register/register';
import { Dashboard } from './backoffice/dashboard/dashboard';
import { Transactions } from './backoffice/transactions/transactions';
import { Accounts } from './backoffice/accounts/accounts';
import { Home } from './frontoffice/home/home';
import { About } from './frontoffice/about/about';
import { Features } from './frontoffice/features/features';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'about', component: About },
  { path: 'features', component: Features },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'dashboard', component: Dashboard },
  { path: 'transactions', component: Transactions },
  { path: 'accounts', component: Accounts },
  { path: '**', redirectTo: '' }
];