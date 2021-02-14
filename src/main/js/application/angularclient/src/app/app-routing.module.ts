import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {UserListComponent} from './user/user-list/user-list.component';
import {UserFormComponent} from './user/user-form/user-form.component';
import {EditUserComponent} from "./user/edit-user/edit-user.component";
import {HotelListComponent} from "./hotel/hotel-list/hotel-list.component";
import {HotelFormComponent} from "./hotel/hotel-form/hotel-form.component";
import {HotelEditComponent} from "./hotel/hotel-edit/hotel-edit.component";
import {RoomFormComponent} from "./room/room-form/room-form.component";
import {RoomListComponent} from "./room/room-list/room-list.component";
import {RoomEditComponent} from "./room/room-edit/room-edit.component";

const routes: Routes = [
  {path: 'users', component: UserListComponent},
  {path: 'adduser', component: UserFormComponent},
  {path: 'edit-user', component: EditUserComponent},
  {path: 'hotels', component: HotelListComponent},
  {path: 'add-hotel', component: HotelFormComponent},
  {path: 'hotel-edit', component: HotelEditComponent},
  {path: 'hotel/:id/add-room', component: RoomFormComponent},
  {path: 'rooms/:id/all', component: RoomListComponent},
  {path: 'rooms/:hotelId/edit/:roomId', component: RoomEditComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
