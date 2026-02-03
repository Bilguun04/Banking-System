import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BankAccount } from '../models/bank-account.model';

@Injectable({
  providedIn: 'root'
})
export class BankAccountService {
  private apiUrl = 'http://localhost:8080/api/accounts';

  constructor(private http: HttpClient) { }

  getAllAccounts(): Observable<BankAccount[]> {
    return this.http.get<BankAccount[]>(this.apiUrl);
  }

  getAccountById(id: number): Observable<BankAccount> {
    return this.http.get<BankAccount>(`${this.apiUrl}/${id}`);
  }

  getAccountsByBranch(branchId: number): Observable<BankAccount[]> {
    return this.http.get<BankAccount[]>(`${this.apiUrl}/branch/${branchId}`);
  }

  createAccount(account: BankAccount): Observable<BankAccount> {
    return this.http.post<BankAccount>(this.apiUrl, account);
  }

  updateAccount(id: number, account: BankAccount): Observable<BankAccount> {
    return this.http.put<BankAccount>(`${this.apiUrl}/${id}`, account);
  }

  deleteAccount(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
