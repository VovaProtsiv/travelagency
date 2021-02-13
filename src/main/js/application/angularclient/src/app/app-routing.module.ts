import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserListComponent } from './user/user-list/user-list.component';
import { UserFormComponent } from './user/user-form/user-form.component';
import {EditUserComponent} from "./user/edit-user/edit-user.component";
import {HotelListComponent} from "./hotel/hotel-list/hotel-list.component";
import {HotelFormComponent} from "./hotel/hotel-form/hotel-form.component";

const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'adduser', component: UserFormComponent },
  { path: 'edit-user', component: EditUserComponent },
  { path: 'hotels', component: HotelListComponent },
  { path: 'add-hotel', component: HotelFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
