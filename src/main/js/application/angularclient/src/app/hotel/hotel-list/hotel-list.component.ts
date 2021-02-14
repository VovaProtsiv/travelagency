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

  deleteHotel(hotel: Hotel) {
    this.hotelService.delete(hotel).subscribe(result=> {
      this.hotels = this.hotels.filter(h => h != hotel);
    });
  }

  updateHotel(hotel: Hotel) {
    window.localStorage.removeItem("editHotelId");
    window.localStorage.setItem("editHotelId", hotel.id.toString());
    this.router.navigate(['hotel-edit']);
  }
}
