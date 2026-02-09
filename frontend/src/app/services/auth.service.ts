import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap, catchError, of } from 'rxjs';
import { Router } from '@angular/router';

export interface User {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  role: string;
}

export interface AuthResponse {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  role: string;
  message: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  
  currentUser$ = this.currentUserSubject.asObservable();
  
  constructor(private http: HttpClient, private router: Router) {
    this.checkAuthStatus();
  }
  
  get currentUser(): User | null {
    return this.currentUserSubject.value;
  }
  
  get isLoggedIn(): boolean {
    return this.currentUserSubject.value !== null;
  }
  
  login(credentials: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, credentials, {
      withCredentials: true
    }).pipe(
      tap(response => {
        const user: User = {
          id: response.id,
          email: response.email,
          firstName: response.firstName,
          lastName: response.lastName,
          role: response.role
        };
        this.currentUserSubject.next(user);
      })
    );
  }
  
  register(data: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, data, {
      withCredentials: true
    });
  }
  
  logout(): Observable<any> {
    return this.http.post(`${this.apiUrl}/logout`, {}, {
      withCredentials: true
    }).pipe(
      tap(() => {
        this.currentUserSubject.next(null);
        this.router.navigate(['/login']);
      })
    );
  }
  
  checkAuthStatus(): void {
    this.http.get<AuthResponse>(`${this.apiUrl}/me`, {
      withCredentials: true
    }).pipe(
      catchError(() => of(null))
    ).subscribe(response => {
      if (response) {
        const user: User = {
          id: response.id,
          email: response.email,
          firstName: response.firstName,
          lastName: response.lastName,
          role: response.role
        };
        this.currentUserSubject.next(user);
      }
    });
  }
}
