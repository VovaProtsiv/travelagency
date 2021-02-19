import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

import {Order} from "../model/order";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private readonly usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/users'
  }

  public findByUserId(userId: string): Observable<Order[]> {
    return this.http.get<Order[]>(this.usersUrl + '/' +userId+'/orders');
  }
}
