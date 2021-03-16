import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Room} from "../../model/room";
import {ActivatedRoute, Router} from "@angular/router";
import {first} from "rxjs/operators";
import {RoomService} from "../../service/room-service";

@Component({
  selector: 'app-room-edit',
  templateUrl: './room-edit.component.html',
  styleUrls: ['./room-edit.component.css']
})
export class RoomEditComponent implements OnInit {
  editForm: FormGroup;
  room:Room;


  constructor(private route: ActivatedRoute, private router: Router, private roomService: RoomService,private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      sleeps: ['', Validators.required],
      price: ['', Validators.required],
      hotelId: ['', Validators.required],
     });
     this.roomService.getRoom(parseInt(this.route.snapshot.paramMap.get('roomId')))
      .subscribe(data => {
        this.editForm.setValue({
          id: data.id,
          name: data.name,
          sleeps: data.sleeps,
          price: data.price,
          hotelId:data.hotelId
        });
      });
  }

  onSubmit() {
    this.roomService.edit(this.editForm.value).pipe(first()).subscribe(result => this.gotoRoomList());
  }

  gotoRoomList() {
   this.router.navigate(['/hotel/'+this.route.snapshot.paramMap.get('hotelId')]);
  }
}
