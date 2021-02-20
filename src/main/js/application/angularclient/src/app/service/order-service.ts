import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

import {Order} from "../model/order";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private readonly orderUrl: string;

  constructor(private http: HttpClient) {
    this.orderUrl = 'http://localhost:8080/orders'
  }

  public findByUserId(userId: string): Observable<Order[]> {
    return this.http.get<Order[]>(this.orderUrl + '/' +userId+'/all');
  }

  public delete(order: Order) {
    return this.http.delete(this.orderUrl + "/remove/" + order.id);
  }
}
