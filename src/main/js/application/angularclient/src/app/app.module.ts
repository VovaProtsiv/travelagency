import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { UserListComponent } from './user/user-list/user-list.component';
import { UserFormComponent } from './user/user-form/user-form.component';
import { UserService } from './service/user.service';
import { EditUserComponent } from './user/edit-user/edit-user.component';
import {HotelService} from "./service/hotel-service";
import {HotelListComponent} from "./hotel/hotel-list/hotel-list.component";
import { HotelFormComponent } from './hotel/hotel-form/hotel-form.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserFormComponent,
    EditUserComponent,
    HotelListComponent,
    HotelFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [UserService,HotelService],
  bootstrap: [AppComponent]
})
export class AppModule { }
