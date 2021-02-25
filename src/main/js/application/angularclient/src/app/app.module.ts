import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {UserListComponent} from './user/user-list/user-list.component';
import {UserFormComponent} from './user/user-form/user-form.component';
import {UserService} from './service/user.service';
import {EditUserComponent} from './user/edit-user/edit-user.component';
import {HotelService} from "./service/hotel-service";
import {HotelListComponent} from "./hotel/hotel-list/hotel-list.component";
import {HotelFormComponent} from './hotel/hotel-form/hotel-form.component';
import {HotelEditComponent} from './hotel/hotel-edit/hotel-edit.component';
import {RoomFormComponent} from './room/room-form/room-form.component';
import {RoomListComponent} from './room/room-list/room-list.component';
import {RoomEditComponent} from './room/room-edit/room-edit.component';
import {OrderListComponent} from './order/order-list/order-list.component';
import {HomeComponent} from './client/home/home.component';
import {ClientRoomListComponent} from './client/client-room-list/client-room-list.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {ProfileComponent} from './profile/profile.component';
import {BoardUserComponent} from './board-user/board-user.component';
import {BoardModeratorComponent} from './board-moderator/board-moderator.component';
import {BoardAdminComponent} from './board-admin/board-admin.component';
import {authInterceptorProviders} from "./service/auth.interceptor";


@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserFormComponent,
    EditUserComponent,
    HotelListComponent,
    HotelFormComponent,
    HotelEditComponent,
    RoomFormComponent,
    RoomListComponent,
    RoomEditComponent,
    OrderListComponent,
    HomeComponent,
    ClientRoomListComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    BoardUserComponent,
    BoardModeratorComponent,
    BoardAdminComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [authInterceptorProviders, UserService, HotelService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
