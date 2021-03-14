import {Component, OnInit} from '@angular/core';
import {Order} from "../../model/order";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../../service/order-service";
import {HotelService} from "../../service/hotel-service";
import {TokenStorage} from "../../service/token-storage";
import {first} from "rxjs/operators";
import {Room} from "../../model/room";
import {RoomService} from "../../service/room-service";
import {Hotel} from "../../model/hotel";

@Component({
  selector: 'app-order-user',
  templateUrl: './order-user.component.html',
  styleUrls: ['./order-user.component.css']
})
export class OrderUserComponent implements OnInit {
  orders: Order[];
  rooms: Room[];
  hotel:Hotel;
  constructor(private token: TokenStorage, private route: ActivatedRoute, private router: Router, private orderService: OrderService, private roomService: RoomService, private hotelService: HotelService) {
  }

  ngOnInit(): void {

    let userId = this.token.getUser().id;
    this.orderService.findByUserId(userId).subscribe(data => this.orders = data);
  }

  getRooms(order: Order) {
       this.roomService.findByOrderId(order.id).subscribe(data=>{this.rooms=data;

       });

       this.hotelService.getHotelById(order.hotelId).subscribe(data=>this.hotel = data);
  }

  cancelOrder(id: string) {
    this.orderService.cancelBooking(id).pipe(first()).subscribe(result => location.reload());
  }
}
