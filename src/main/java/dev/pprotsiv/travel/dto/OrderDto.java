package dev.pprotsiv.travel.dto;

import dev.pprotsiv.travel.model.State;

import java.time.LocalDate;
import java.util.Set;

public class OrderDto {
   private Long id;
   private LocalDate checkIn;
   private LocalDate checkOut;
   private String hotelId;
   private Set<String> rooms;
   private State state;
   private String userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public Set<String> getRooms() {
        return rooms;
    }

    public void setRooms(Set<String> rooms) {
        this.rooms = rooms;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", hotelId='" + hotelId + '\'' +
                ", rooms=" + rooms +
                ", state='" + state + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
