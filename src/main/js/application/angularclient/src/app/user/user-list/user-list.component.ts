import {Component, OnInit} from '@angular/core';
import {User} from '../../model/user';
import {UserService} from '../../service/user.service';
import {ActivatedRoute, Router} from "@angular/router";
import {Role} from "../../model/role";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];


  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService) {
  }

  ngOnInit() {
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });

  }

  deleteUser(user: User) {
    this.userService.delete(user).subscribe(result => {
      this.users = this.users.filter(u => u !== user);
    });
  }

  updateUser(user: User) {
        this.router.navigate(['edit-user/'+user.id]);
  }

  getOrders(user: User) {
    this.router.navigate(['orders/' + user.id + '/all']);
  }

}
