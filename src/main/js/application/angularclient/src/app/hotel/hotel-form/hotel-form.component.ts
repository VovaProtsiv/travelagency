import { Component, OnInit } from '@angular/core';
import {Hotel} from "../../model/hotel";
import {ActivatedRoute, Router} from "@angular/router";
import {HotelService} from "../../service/hotel-service";

@Component({
  selector: 'app-hotel-form',
  templateUrl: './hotel-form.component.html',
  styleUrls: ['./hotel-form.component.css']
})
export class HotelFormComponent  {
  hotel: Hotel;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private hotelService: HotelService) {
    this.hotel = new Hotel();
  }


  onSubmit() {
    this.hotelService.save(this.hotel).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/hotels']);
  }
}
