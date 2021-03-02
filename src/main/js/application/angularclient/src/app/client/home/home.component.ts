import {Component, OnInit} from '@angular/core';
import {Hotel} from "../../model/hotel";
import {HotelService} from "../../service/hotel-service";
import {Router} from "@angular/router";
import {TokenStorage} from "../../service/token-storage";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  text: string = '';
  hotels: Hotel[];

  constructor(private hotelService: HotelService, private route : Router, private tokenStorage: TokenStorage) {
  }

  ngOnInit(): void {
    this.hotelService.findAll().subscribe(data => this.hotels = data);
  }

  onChange(name: string) {
    this.hotelService.findHotelByName(name).subscribe(data => this.hotels = data);

  }

  getAvailability(id: string) {
    this.route.navigate(['/home/'+this.tokenStorage.getUser().id+'/hotel/'+id])
  }
}
