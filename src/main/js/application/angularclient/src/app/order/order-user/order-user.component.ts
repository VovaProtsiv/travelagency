import {Component, OnInit} from '@angular/core';
import {Order} from "../../model/order";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../../service/order-service";
import {HotelService} from "../../service/hotel-service";
import {TokenStorage} from "../../service/token-storage";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-order-user',
  templateUrl: './order-user.component.html',
  styleUrls: ['./order-user.component.css']
})
export class OrderUserComponent implements OnInit {
  orders: Order[];

  constructor(private token: TokenStorage, private route: ActivatedRoute, private router: Router, private orderService: OrderService, private hotelService: HotelService) {
  }

  ngOnInit(): void {

    let userId = this.token.getUser().id;
    this.orderService.findByUserId(userId).subscribe(data => this.orders = data);
  }

  getRooms(order: Order) {

  }

  cancelOrder(id: string) {
    console.log(id)
    this.orderService.cancelBooking(id).pipe(first()).subscribe(result => location.reload());
  }
}
