import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserListComponent} from './user/user-list/user-list.component';
import {UserFormComponent} from './user/user-form/user-form.component';
import {EditUserComponent} from "./user/edit-user/edit-user.component";
import {HotelListComponent} from "./hotel/hotel-list/hotel-list.component";
import {HotelFormComponent} from "./hotel/hotel-form/hotel-form.component";
import {HotelEditComponent} from "./hotel/hotel-edit/hotel-edit.component";
import {RoomFormComponent} from "./room/room-form/room-form.component";
import {RoomListComponent} from "./room/room-list/room-list.component";
import {RoomEditComponent} from "./room/room-edit/room-edit.component";
import {OrderListComponent} from "./order/order-list/order-list.component";
import {HomeComponent} from "./client/home/home.component";
import {ClientRoomListComponent} from "./client/client-room-list/client-room-list.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {ProfileComponent} from "./profile/profile.component";
import {HotelAdminComponent} from "./hotel/hotel-admin/hotel-admin.component";
import {OrderUserComponent} from "./order/order-user/order-user.component";


const routes: Routes = [
  {path: 'users', component: UserListComponent},
  {path: 'adduser', component: UserFormComponent},
  {path: 'edit-user/:userId', component: EditUserComponent},
  {path: 'orders/:userId', component: ClientRoomListComponent},
  {path: 'hotels', component: HotelListComponent},
  {path: 'add-hotel', component: HotelFormComponent},
  {path: 'hotel-edit', component: HotelEditComponent},
  {path: 'hotel/:id/add-room', component: RoomFormComponent},
  {path: 'hotel/:hotelId', component: HotelAdminComponent},
  {path: 'rooms/:id/all', component: RoomListComponent},
  {path: 'rooms/:hotelId/edit/:roomId', component: RoomEditComponent},
  {path: 'home', component: HomeComponent},
  {path: 'home/:userId/hotel/:hotelId', component: ClientRoomListComponent},
  {path: 'orders/:userId/all', component: OrderListComponent},
  {path: 'my-orders', component: OrderUserComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
