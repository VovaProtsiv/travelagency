package dev.pprotsiv.travel.service;

import dev.pprotsiv.travel.model.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create(Hotel hotel);
    Hotel readById(long id);
    Hotel update(Hotel hotel);
    void delete(long id);
    List<Hotel> getAll();
}
