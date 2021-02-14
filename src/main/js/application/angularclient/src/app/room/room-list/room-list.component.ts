import {Component, OnInit} from '@angular/core';
import {Room} from "../../model/room";
import {ActivatedRoute, Router} from "@angular/router";
import {RoomService} from "../../service/room-service";

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {
  rooms: Room[];

  constructor(private route: ActivatedRoute, private router: Router, private roomService: RoomService) {
  }

  ngOnInit(): void {
   let id = this.route.snapshot.paramMap.get('id');
    this.roomService.findAll(id).subscribe(data => {
      this.rooms = data;

    });
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
