import {Component, OnInit} from '@angular/core';
import {Order} from "../../model/order";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../../service/order-service";

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  orders: Order[];


  constructor(private route: ActivatedRoute, private router: Router, private orderService: OrderService) {
  }

  ngOnInit(): void {
    let userId = this.route.snapshot.paramMap.get('userId');
    this.orderService.findByUserId(userId).subscribe(data => this.orders = data);
  }

  updateOrder(order: Order) {

  }

  deleteOrder(order: Order) {

  }

  getRooms(order: Order) {

  }
}
