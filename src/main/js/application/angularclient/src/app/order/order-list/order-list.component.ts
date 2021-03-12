import {Component, OnInit} from '@angular/core';
import {Order} from "../../model/order";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../../service/order-service";
import {HotelService} from "../../service/hotel-service";
import {first} from "rxjs/operators";
import {RoomService} from "../../service/room-service";
import {Room} from "../../model/room";
import {Hotel} from "../../model/hotel";

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  orders: Order[];
  rooms: Room[];
  hotel: Hotel;

  constructor(private route: ActivatedRoute, private router: Router, private orderService: OrderService, private hotelService: HotelService, private roomService: RoomService) {
  }

  ngOnInit(): void {
    let userId = this.route.snapshot.paramMap.get('userId');
    this.orderService.findByUserId(userId).subscribe(data => this.orders = data);

  }


  deleteOrder(order: Order) {
    this.orderService.delete(order).subscribe(result => {
      this.orders = this.orders.filter(h => h != order);
    });
  }

  cancelOrder(id: string) {
    this.orderService.cancelBooking(id).pipe(first()).subscribe(result => location.reload());
  }

  getRooms(order: Order) {
    this.roomService.findByOrderId(order.id).subscribe(data => this.rooms = data);
    this.hotelService.getHotelById(order.hotelId).subscribe(data => this.hotel = data);
  }
}
