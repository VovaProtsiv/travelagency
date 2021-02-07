import {Component, OnInit} from '@angular/core';
import {Hotel} from "../../model/hotel";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../service/user.service";
import {HotelService} from "../../service/hotel-service";

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.css']
})
export class HotelListComponent implements OnInit {
  hotels: Hotel[];

  constructor(private route: ActivatedRoute, private router: Router, private hotelService: HotelService) {
  }

  ngOnInit() {
    this.hotelService.findAll().subscribe(data => {
      this.hotels = data;
    });
  }

}
