import {Component, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../service/user.service";
import {Role} from "../../model/role";
import {ActivatedRoute, Router} from "@angular/router";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  roleForm: FormGroup;
  rolesList: Role[];

  user: User;
  isRoleUpload: boolean = false;
  isUserUpload: boolean = false;

  constructor(private fb: FormBuilder, private router: Router, private userService: UserService, private route: ActivatedRoute) {


  }


  async ngOnInit(): Promise<void> {
    let userId = this.route.snapshot.paramMap.get('userId');
    this.rolesList = await this.initRoles();
    this.user = await this.initUser(userId);
    this.roleForm = this.fb.group({
      id: [this.user.id],
      username: [this.user.username, Validators.required],
      email: [this.user.email, Validators.required],
      roles: this.fb.array(this.rolesList.map(control => this.fb.control(false)))
      ,
    });
    this.patchValue();


  }

  async initRoles(): Promise<Role[]> {
    await this.userService.findAllRoles().toPromise().then(data => {
      this.rolesList = data;
      this.isRoleUpload = true;
    })
    return this.rolesList;
  }

  get permissionsArr() {
    return this.roleForm.get('roles') as FormArray;
  }

  submit() {
    const selectedRoles = this.roleForm.value.roles
      .map((checked, i) => checked ? this.rolesList[i] : null)
      .filter(value => value !== null);
    let userUpdate = new User();
    userUpdate.id = this.roleForm.value.id;
    userUpdate.username = this.roleForm.value.username;
    userUpdate.email = this.roleForm.value.email;
    userUpdate.roles = selectedRoles;
    this.userService.edit(userUpdate).pipe(first()).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/users']);
  }

  patchValue() {
    this.rolesList.map((perm, i) => {
      let arr = [];
      this.user.roles.forEach(value => {
        arr.push(value.id);
      });
      if (arr.indexOf(perm.id) !== -1) {
        this.permissionsArr.at(i).patchValue(true)
      }
    })

  }


  private async initUser(id: string): Promise<User> {
    await this.userService.getUserById(parseInt(id)).toPromise().then(data => {
      this.user = data;
      this.isUserUpload = true;
    });
    return this.user;
  }
}
