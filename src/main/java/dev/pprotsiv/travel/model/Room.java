package dev.pprotsiv.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The 'name' cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Sleeps may not be empty")
    @Range(min = 1, max = 20, message = "Sleeps should be with minimum 1 and maximum 20")
    @Column(name = "sleeps", nullable = false)
    private Integer sleeps;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @JsonIgnore
    @ManyToMany(mappedBy = "rooms", cascade = CascadeType.REMOVE)
    private Set<Order> orders = new HashSet<Order>();

    public Room() {
    }

    public Room(Long id, String name, Integer sleeps, Hotel hotel, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.sleeps = sleeps;
        this.hotel = hotel;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSleeps() {
        return sleeps;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setSleeps(Integer sleeps) {
        this.sleeps = sleeps;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
        order.addRoom(this);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
        order.removeRoom(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) && Objects.equals(name, room.name) && Objects.equals(sleeps, room.sleeps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sleeps);
    }
}
