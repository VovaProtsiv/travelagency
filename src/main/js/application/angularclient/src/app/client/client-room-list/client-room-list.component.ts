import {Component, OnInit} from '@angular/core';
import {Hotel} from "../../model/hotel";
import {HotelService} from "../../service/hotel-service";
import {ActivatedRoute} from "@angular/router";
import {Room} from "../../model/room";
import {RoomService} from "../../service/room-service";
import {Order} from "../../model/order";
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-client-room-list',
  templateUrl: './client-room-list.component.html',
  styleUrls: ['./client-room-list.component.css']
})
export class ClientRoomListComponent implements OnInit {
  hotel: Hotel;
  rooms: Room[];
  order: Order;
  id: string;
  form: FormGroup;
  roomsId: string[];


  constructor(private hotelService: HotelService, private rout: ActivatedRoute, private roomService: RoomService, private fb: FormBuilder) {
    this.order = new Order();
    this.form = this.fb.group({
      checkArray: this.fb.array([])
    })

  }

  ngOnInit(): void {
    this.id = this.rout.snapshot.paramMap.get('hotelId');
    this.hotelService.getHotelById(parseInt(this.id)).subscribe(data => this.hotel = data);
    this.roomService.findAll(this.id).subscribe(data => this.rooms = data);
    this.order.hotelId = parseInt(this.id);
    this.order.state = 'NEW';

  }

  onCheckboxChange(e) {
    const checkArray: FormArray = this.form.get('checkArray') as FormArray;

    if (e.target.checked) {
      checkArray.push(new FormControl(e.target.value));

    } else {
      let i: number = 0;
      checkArray.controls.forEach((item: FormControl) => {
        if (item.value == e.target.value) {
          checkArray.removeAt(i);
          return;
        }
        i++;
      });
    }

  }

  submitForm() {
    console.log(this.form.value);
    this.order.rooms= this.form.value.checkArray;
    console.log(this.order);
  }
}








