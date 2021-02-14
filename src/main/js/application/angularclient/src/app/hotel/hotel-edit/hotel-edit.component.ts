import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {HotelService} from "../../service/hotel-service";
import {Hotel} from "../../model/hotel";
import {any} from "codelyzer/util/function";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-hotel-edit',
  templateUrl: './hotel-edit.component.html',
  styleUrls: ['./hotel-edit.component.css']
})
export class HotelEditComponent implements OnInit {
  editForm: FormGroup;
  hotel: Hotel;

  constructor(private router: Router, private hotelService: HotelService,private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    let hotelId = window.localStorage.getItem("editHotelId");
    if (!hotelId) {
      alert("Invalid action.")
      this.router.navigate(['/hotels']);
      return;
    }
    this.editForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      country: ['', Validators.required],
      city: ['', Validators.required],
      street: ['', Validators.required],
      houseNumber: ['', Validators.required],

    });
    this.hotelService.getHotelById(+hotelId)
      .subscribe(data => {
        this.editForm.setValue({
          id: data.id,
          name: data.name,
          country: data.country,
          city: data.city,
          street: data.street,
          houseNumber: data.houseNumber
        });
      });

  }

  onSubmit() {
    this.hotelService.edit(this.editForm.value).pipe(first()).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/hotels']);
  }
}
