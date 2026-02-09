import { Component } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './login.html'
})
export class Login {
  email = '';
  password = '';
  rememberMe = false;
  isLoading = false;
  errorMessage = '';
  
  constructor(private authService: AuthService, private router: Router) {}
  
  onSubmit(): void {
    if (!this.email || !this.password) {
      this.errorMessage = 'Please enter email and password';
      return;
    }
    
    this.isLoading = true;
    this.errorMessage = '';
    
    this.authService.login({ email: this.email, password: this.password }).subscribe({
      next: () => {
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        this.isLoading = false;
        this.errorMessage = err.error?.error || 'Login failed. Please try again.';
      }
    });
  }
}
