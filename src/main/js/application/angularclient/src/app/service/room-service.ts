  import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Room} from "../model/room";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RoomService {
  private readonly roomUrl: string;


  constructor(private http: HttpClient) {
    this.roomUrl = 'http://localhost:8080/rooms';
  }

  save(room: Room) {
    return this.http.post<Room>(this.roomUrl + '/' + room.hotelId + '/add', room);
  }

  findAll(hotelId: string): Observable<Room[]> {
    return this.http.get<Room[]>(this.roomUrl + '/' + hotelId + '/all');
  }

  findByOrderId(orderId: string): Observable<Room[]> {
   return this.http.get<Room[]>(this.roomUrl + '/order/' + orderId + '/all');
  }

  getRoom(roomId: number): Observable<Room> {
    return this.http.get<Room>(this.roomUrl + "/" + roomId);
  }

  public delete(room: Room) {
    return this.http.delete(this.roomUrl + '/' + room.hotelId + '/remove/' + room.id);
  }

  public edit(room: Room) {
    return this.http.put<Room>(this.roomUrl + '/' + room.hotelId + '/edit/' + room.id, room);
  }

  public getOrderedRoom(id: string, check_in: Date, check_out: Date): Observable<String[]>{
    return this.http.get<String[]>(this.roomUrl+'/'+id+'/'+check_in+'/'+check_out);
  }
}
