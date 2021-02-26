import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RoomService} from "../../service/room-service";
import {Room} from "../../model/room";

@Component({
  selector: 'app-room-form',
  templateUrl: './room-form.component.html',
  styleUrls: ['./room-form.component.css']
})
export class RoomFormComponent implements OnInit {

  room: Room;

  constructor(private router: Router, private activateRoute: ActivatedRoute, private roomService: RoomService) {
    this.room = new Room();
  }

  ngOnInit(): void {
    this.room.hotelId = parseInt(this.activateRoute.snapshot.paramMap.get('id'));
  }

  onSubmit() {
    this.roomService.save(this.room).subscribe(result => this.gotoRoomList());
  }

  gotoRoomList() {
    this.router.navigate(['/hotel/'+this.room.hotelId]);
  }
}
