import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientRoomListComponent } from './client-room-list.component';

describe('ClientRoomListComponent', () => {
  let component: ClientRoomListComponent;
  let fixture: ComponentFixture<ClientRoomListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientRoomListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientRoomListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
