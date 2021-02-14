package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.Room;
import dev.pprotsiv.travel.projection.RoomProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByHotel_Id(long id);

    @Query("SELECT r.id as id, r.name as name, r.sleeps as sleeps, r.hotel.id as hotelId FROM Room r WHERE id = ?1")
    RoomProjection findProjectionById(long id);

    @Query("SELECT r.id as id, r.name as name, r.sleeps as sleeps, r.hotel.id as hotelId FROM Room r WHERE r.hotel.id = ?1")
    List<RoomProjection> findAllProjectionByHotelId(long id);
}
