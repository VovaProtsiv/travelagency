package dev.pprotsiv.travel.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    public Room() {
    }

    public Room(String name, Integer sleeps) {
        this.name = name;
        this.sleeps = sleeps;
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

    public void setSleeps(Integer sleeps) {
        this.sleeps = sleeps;
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
