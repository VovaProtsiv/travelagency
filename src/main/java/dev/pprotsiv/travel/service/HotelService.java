package dev.pprotsiv.travel.service;

import dev.pprotsiv.travel.model.Hotel;
import dev.pprotsiv.travel.projection.HotelProjection;

import java.util.List;

public interface HotelService {
    Hotel create(Hotel hotel);
    Hotel readById(long id);
    HotelProjection readProjectionById(long id);
    Hotel update(Hotel hotel);
    void delete(long id);
    List<HotelProjection> getAllProjections();
}
