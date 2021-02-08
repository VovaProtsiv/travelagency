import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Hotel} from "../model/hotel";

@Injectable({
  providedIn: 'root'
})
export class HotelService {
  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/hotels';
  }
  public findAll(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(this.usersUrl+"/all");
  }
}
