import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Hotel} from "../model/hotel";
import {User} from "../model/user";
import {first, take} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class HotelService {
  private hotelUrl: string;

  constructor(private http: HttpClient) {
    this.hotelUrl = 'http://localhost:8080/hotels';
  }

  public findAll(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(this.hotelUrl + "/all");
  }
  public findHotelByName(name:string): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(this.hotelUrl + "/search?name="+name);
  }

  getHotelById(id: number): Observable<Hotel> {
    return this.http.get<Hotel>(this.hotelUrl + "/" + id);
  }

  public delete(hotel: Hotel) {
    return this.http.delete(this.hotelUrl + "/" + hotel.id);
  }


  save(hotel: Hotel) {
    return this.http.post<Hotel>(this.hotelUrl, hotel);
  }

  edit(hotel: Hotel) {
    return this.http.put<Hotel>(this.hotelUrl + "/" + hotel.id, hotel);
  }
}
