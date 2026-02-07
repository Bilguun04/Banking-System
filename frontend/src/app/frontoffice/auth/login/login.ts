import { HttpClient } from '@angular/common/http';
import { NgIf } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

type ToastType = 'success' | 'error';

type AuthResponse = {
  success?: boolean;
  token?: string;
  accessToken?: string;
  message?: string;
  error?: string;
};

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule, NgIf],
  templateUrl: './login.html'
})
export class Login {
  private http = inject(HttpClient);
  private router = inject(Router);

  protected readonly apiUrl = '/api/auth/login';
  protected readonly credentials = {
    username: '',
    password: '',
    rememberMe: false
  };
  protected isSubmitting = false;
  protected toastMessage = '';
  protected toastType: ToastType = 'success';
  private toastTimer: ReturnType<typeof setTimeout> | null = null;

  protected onSubmit(): void {
    if (this.isSubmitting) {
      return;
    }

    this.isSubmitting = true;
    const payload = {
      username: this.credentials.username,
      password: this.credentials.password,
      rememberMe: this.credentials.rememberMe
    };

    this.http.post<AuthResponse>(this.apiUrl, payload).subscribe({
      next: (response) => {
        const token = response?.token ?? response?.accessToken ?? '';
        const isSuccess = response?.success !== false && Boolean(token);

        if (isSuccess) {
          this.storeToken(token);
          this.showToast(response?.message ?? 'Login successful', 'success');
          this.router.navigateByUrl('/frontoffice');
        } else {
          this.showToast(
            response?.message ?? response?.error ?? 'Login failed',
            'error'
          );
        }

        this.isSubmitting = false;
      },
      error: (error) => {
        const message =
          error?.error?.message ?? error?.message ?? 'Login failed';
        this.showToast(message, 'error');
        this.isSubmitting = false;
      }
    });
  }

  private storeToken(token: string): void {
    localStorage.removeItem('auth_token');
    sessionStorage.removeItem('auth_token');

    const storage = this.credentials.rememberMe ? localStorage : sessionStorage;
    storage.setItem('auth_token', token);
  }

  private showToast(message: string, type: ToastType): void {
    this.toastMessage = message;
    this.toastType = type;

    if (this.toastTimer) {
      clearTimeout(this.toastTimer);
    }

    this.toastTimer = setTimeout(() => {
      this.toastMessage = '';
    }, 3000);
  }
}
