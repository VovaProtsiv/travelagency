import {Component, OnInit} from '@angular/core';
import {Hotel} from "../../model/hotel";
import {HotelService} from "../../service/hotel-service";
import {ActivatedRoute, Router} from "@angular/router";
import {TokenStorage} from "../../service/token-storage";
import {RoomService} from "../../service/room-service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  text: string = '';
  hotels: Hotel[];
  checkIn: Date;
  checkOut: Date;

  constructor(private hotelService: HotelService, private rout: ActivatedRoute, private route: Router, private tokenStorage: TokenStorage, private roomService: RoomService) {
  }

  ngOnInit(): void {
    this.hotelService.findAll().subscribe(data => this.hotels = data);
  }

  onChange(name: string) {
    this.hotelService.findHotelByName(name).subscribe(data => this.hotels = data);

  }

  getAvailability(id: string) {
    this.route.navigate(['/home/' + this.tokenStorage.getUser().id + '/hotel/' + id],
      {
        relativeTo: this.rout, queryParams: {
          check_in: this.checkIn,
          check_out: this.checkOut
        },
      })
  }



  onSubmit() {

  }
}
