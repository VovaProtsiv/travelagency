package dev.pprotsiv.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "check_in", nullable = false)
        private LocalDate checkIn;
        @Column(name = "check_out", nullable = false)
        private LocalDate checkOut;

        @JsonIgnore
        @ManyToMany
        @JoinTable(name = "order_room",
                joinColumns = { @JoinColumn(name = "fk_order") },
                inverseJoinColumns = { @JoinColumn(name = "fk_room") })
        private Set<Room> rooms = new HashSet<Room>();

        @Column(name = "state")
        @Enumerated(EnumType.STRING)
        private State state;

        public void addRoom(Room room){
                this.rooms.add(room);
                room.addOrder(this);
        }

        public void removeRoom(Room room){
                this.rooms.remove(room);
                room.removeOrder(this);
        }

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        private User client;

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        private Hotel hotel;

        public Order(LocalDate checkIn, LocalDate checkOut, Set<Room> rooms, State state, User client, Hotel hotel) {
                this.checkIn = checkIn;
                this.checkOut = checkOut;
                this.rooms = rooms;
                this.state = state;
                this.client = client;
                this.hotel = hotel;
        }

        public Order() {
        }

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

        public Set<Room> getRooms() {
                return rooms;
        }

        public void setRooms(Set<Room> rooms) {
                this.rooms = rooms;
        }

        public User getClient() {
                return client;
        }

        public void setClient(User client) {
                this.client = client;
        }

        public State getState() {
                return state;
        }

        public void setState(State state) {
                this.state = state;
        }

        public Hotel getHotel() {
                return hotel;
        }

        public void setHotel(Hotel hotel) {
                this.hotel = hotel;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Order order = (Order) o;
                return Objects.equals(id, order.id);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id);
        }
}
