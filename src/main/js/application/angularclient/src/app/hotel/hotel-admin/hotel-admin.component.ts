import { Component, OnInit } from '@angular/core';
import {Hotel} from "../../model/hotel";
import {Room} from "../../model/room";
import {HotelService} from "../../service/hotel-service";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {RoomService} from "../../service/room-service";

@Component({
  selector: 'app-hotel-admin',
  templateUrl: './hotel-admin.component.html',
  styleUrls: ['./hotel-admin.component.css']
})
export class HotelAdminComponent implements OnInit {
  hotel: Hotel;
  rooms: Room[];
  id: string;

  constructor(private hotelService: HotelService, private rout: ActivatedRoute, private roomService: RoomService, private router: Router) { }

  ngOnInit(): void {
    this.id = this.rout.snapshot.paramMap.get('hotelId');
    this.roomService.findAll(this.id).subscribe(data => this.rooms = data);
    this.hotelService.getHotelById(parseInt(this.id)).subscribe(data => this.hotel = data);

  }

  addRoom(id: string) {
    this.router.navigate(['hotel/'+id+'/add-room']);
  }

  deleteRoom(room: Room) {
    this.roomService.delete(room).subscribe(result => {
      this.rooms = this.rooms.filter(h => h != room);
    });
  }

  updateRoom(room: Room) {
    this.router.navigate(['/rooms/'+room.hotelId+'/edit/'+room.id]);
  }
}
