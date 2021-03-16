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
import {AuthGuardService} from "./service/auth-guard.service";
import {NotFoundComponent} from "./exception/not-found/not-found.component";
import {InternalServerErrorComponent} from "./exception/internal-server-error/internal-server-error.component";


const routes: Routes = [
  {path: 'users', component: UserListComponent, canActivate: [AuthGuardService]},
  {path: 'adduser', component: UserFormComponent, canActivate: [AuthGuardService]},
  {path: 'edit-user/:userId', component: EditUserComponent, canActivate: [AuthGuardService]},
  {path: 'orders/:userId', component: ClientRoomListComponent, canActivate: [AuthGuardService]},
  {path: 'hotels', component: HotelListComponent, canActivate: [AuthGuardService]},
  {path: 'add-hotel', component: HotelFormComponent, canActivate: [AuthGuardService]},
  {path: 'hotel-edit', component: HotelEditComponent, canActivate: [AuthGuardService]},
  {path: 'hotel/:id/add-room', component: RoomFormComponent, canActivate: [AuthGuardService]},
  {path: 'hotel/:hotelId', component: HotelAdminComponent, canActivate: [AuthGuardService]},
  {path: 'rooms/:id/all', component: RoomListComponent, canActivate: [AuthGuardService]},
  {path: 'rooms/:hotelId/edit/:roomId', component: RoomEditComponent, canActivate: [AuthGuardService]},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuardService]},
  {path: 'home/:userId/hotel/:hotelId', component: ClientRoomListComponent, canActivate: [AuthGuardService]},
  {path: 'orders/:userId/all', component: OrderListComponent, canActivate: [AuthGuardService]},
  {path: 'my-orders', component: OrderUserComponent, canActivate: [AuthGuardService]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuardService]},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'error', component:InternalServerErrorComponent},
  {path: '**', component:NotFoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
