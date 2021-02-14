package dev.pprotsiv.travel.service;

import dev.pprotsiv.travel.model.Room;
import dev.pprotsiv.travel.projection.RoomProjection;

import java.util.List;

public interface RoomService {
    Room create(Room room);

    Room readById(long id);

    RoomProjection readProjectionById(long id);

    Room update(Room room);

    void delete(long id);

    List<Room> getAll();

    List<RoomProjection> getAllProjectionsByHotelId(long id);

}
