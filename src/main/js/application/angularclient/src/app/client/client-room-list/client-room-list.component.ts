import {Component, OnInit} from '@angular/core';
import {Hotel} from "../../model/hotel";
import {HotelService} from "../../service/hotel-service";
import {ActivatedRoute, Router} from "@angular/router";
import {Room} from "../../model/room";
import {RoomService} from "../../service/room-service";
import {Order} from "../../model/order";
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {OrderService} from "../../service/order-service";
import {first} from "rxjs/operators";

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
  checkIn: Date;
  checkOut: Date;
  roomsOrderedID: String[];
  totalAmount: number = 0.00;

  constructor(private hotelService: HotelService, private orderService: OrderService, private rout: ActivatedRoute, private roomService: RoomService, private fb: FormBuilder, private router: Router) {

  }

  async ngOnInit(): Promise<void> {
    this.order = new Order();
    this.rout.queryParams.subscribe(params => {
      this.checkIn = params['check_in'];
      this.checkOut = params['check_out'];
    });

    this.fillForm();

    this.id = this.rout.snapshot.paramMap.get('hotelId');
    this.hotelService.getHotelById(parseInt(this.id)).subscribe(data => this.hotel = data);
    this.roomService.findAll(this.id).subscribe(data => this.rooms = data);
    this.order.hotelId = parseInt(this.id);
    this.order.state = 'NEW';
    this.order.userId = parseInt(this.rout.snapshot.paramMap.get('userId'));
    this.roomsOrderedID = await this.getOrderedRoomsId();
  }

  private fillForm() {

    this.form = this.fb.group({
      checkIn: [this.checkIn],
      checkOut: [this.checkOut],
      checkArray: this.fb.array([])
    });
  }

  private async getOrderedRoomsId(): Promise<String[]> {
    await this.roomService.getOrderedRoom(this.id, this.checkIn, this.checkOut).toPromise().then(data => {
      this.roomsOrderedID = data;
    });
    return this.roomsOrderedID;
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
    this.addOrderRoomsAndDate();
    this.orderService.getTotalAmount(this.order).pipe(first()).subscribe(data => {
      this.totalAmount = data;
    });
  }

  private addOrderRoomsAndDate() {
    this.order.rooms = this.form.value.checkArray;
    this.order.checkIn = this.form.value.checkIn;
    this.order.checkOut = this.form.value.checkOut;
  }

  submitForm() {
    this.order.rooms = this.form.value.checkArray;
    this.order.checkIn = this.form.value.checkIn;
    this.order.checkOut = this.form.value.checkOut;
    this.orderService.save(this.order).pipe(first()).subscribe(result => {
      this.gotoHome();
    });
  }

  private gotoHome() {
    this.router.navigate(['/home'])
  }


  async onChangeDate(e) {
    this.checkIn = this.form.value.checkIn;
    this.checkOut = this.form.value.checkOut;
    this.fillForm();
    this.roomsOrderedID = await this.getOrderedRoomsId();
    this.order.checkIn = this.form.value.checkIn;
    this.order.checkOut = this.form.value.checkOut;
    this.orderService.getTotalAmount(this.order).pipe(first()).subscribe(data => {
      this.totalAmount = data;
    });
  }
}








