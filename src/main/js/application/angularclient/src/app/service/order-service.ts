import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

import {Order} from "../model/order";
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
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

  save(order: Order) {
    let message = this.orderUrl + '/' + order.userId;
    return this.http.post<Order>(message, order);
  }

  cancelBooking(id: string) {
    let message = this.orderUrl + '/' + id+'/cancel';
    return this.http.put<Order>(message, '');
  }
}
