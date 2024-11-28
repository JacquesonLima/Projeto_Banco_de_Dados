import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../types/login-response.type';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  apiUrl: string = 'http://localhost:8080/auth';

  constructor(private httpClient: HttpClient) {}

  login(username: string, password: string) {
    return this.httpClient
      .post<LoginResponse>(this.apiUrl + '/login', { username, password })
      .pipe(
        tap((value) => {
          localStorage.setItem('auth-token', value.token);
          localStorage.setItem('username', value.username);
        })
      );
  }

  signup(username: string, password: string) {
    return this.httpClient
      .post<LoginResponse>(this.apiUrl + '/register', { username, password })
      .pipe(
        tap((value) => {
          localStorage.setItem('auth-token', value.token);
          localStorage.setItem('username', value.username);
        })
      );
  }
}
