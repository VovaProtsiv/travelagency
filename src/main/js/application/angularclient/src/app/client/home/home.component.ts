import {Component, OnInit} from '@angular/core';
import {Hotel} from "../../model/hotel";
import {HotelService} from "../../service/hotel-service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  text: string = '';
  hotels: Hotel[];

  constructor(private hotelService: HotelService) {
  }

  ngOnInit(): void {
    this.hotelService.findAll().subscribe(data => this.hotels = data);
  }

  onChange(name: string) {
    this.hotelService.findHotelByName(name).subscribe(data => this.hotels = data);

  }
}
