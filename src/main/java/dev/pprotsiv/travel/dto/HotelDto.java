package dev.pprotsiv.travel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.pprotsiv.travel.model.Address;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class HotelDto {
    private Long id;

    @NotBlank(message = "The 'name' cannot be empty")

    private String name;

    private long addressId;

    public HotelDto() {
    }

    public HotelDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelDto hotelDto = (HotelDto) o;
        return Objects.equals(id, hotelDto.id) && Objects.equals(name, hotelDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
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

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
}
