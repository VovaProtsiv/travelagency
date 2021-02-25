import {Component, OnInit} from '@angular/core';
import {TokenStorage} from "../service/token-storage";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser: any;

  constructor(private token: TokenStorage) {
  }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
  }

}
