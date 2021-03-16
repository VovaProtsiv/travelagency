import {Component, OnInit} from '@angular/core';
import {Hotel} from "../../model/hotel";
import {HotelService} from "../../service/hotel-service";
import {ActivatedRoute, Router} from "@angular/router";
import {TokenStorage} from "../../service/token-storage";
import {RoomService} from "../../service/room-service";
import {FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  text: string = '';
  hotels: Hotel[];
  myForm: FormGroup;
  minDate: any;
  maxDate: any;
  isValid: boolean;

  constructor(private hotelService: HotelService, private formBuilder: FormBuilder, private rout: ActivatedRoute, private route: Router, private tokenStorage: TokenStorage, private roomService: RoomService) {
  }

  ngOnInit(): void {
    this.hotelService.findAll().subscribe(data => this.hotels = data);
    let date = new Date();
    this.minDate = new Date();
    date.setDate(date.getDate() + 1);
    this.maxDate = date;

    this.myForm = this.formBuilder.group({
      'checkIn': ['', Validators.required],
      'checkOut': ['', Validators.required],
    });
    this.myForm.setValue({
      'checkIn': (new Date()).toISOString().substring(0, 10),
      'checkOut': (date).toISOString().substring(0, 10)
    });
    this.myForm.setValidators(this.comparisonValidator())
  }

  onChange(name: string) {
    this.hotelService.findHotelByName(name).subscribe(data => this.hotels = data);

  }

  getAvailability(id: string) {
    this.route.navigate(['/home/' + this.tokenStorage.getUser().id + '/hotel/' + id],
      {
        relativeTo: this.rout, queryParams: {
          check_in: this.myForm.value.checkIn,
          check_out: this.myForm.value.checkOut
        },
      })
  }

  onSubmit() {
  }

  public comparisonValidator(): ValidatorFn {
    return (group: FormGroup): ValidationErrors => {
      const checkIn = group.controls['checkIn'];
      const checkOut = group.controls['checkOut'];
      let date = new Date();
      date.setDate(date.getDate());

      if (checkIn.value >= checkOut.value) {
        checkOut.setErrors({"Check-out should be greater than check-in": false});
        this.isValid = false;
      } else {
        checkOut.setErrors(null);
        this.isValid = true;
      }
      return;
    };
  }
}
