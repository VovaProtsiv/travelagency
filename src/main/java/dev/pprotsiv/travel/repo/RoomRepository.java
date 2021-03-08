package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.Room;
import dev.pprotsiv.travel.model.State;
import dev.pprotsiv.travel.projection.RoomProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r.id as id, r.name as name, r.sleeps as sleeps, r.hotel.id as hotelId FROM Room r WHERE id = ?1")
    RoomProjection findProjectionById(long id);

    @Query("SELECT r.id as id, r.name as name, r.sleeps as sleeps, r.hotel.id as hotelId FROM Room r WHERE r.hotel.id = ?1")
    List<RoomProjection> findAllProjectionByHotelId(long id);

    @Query(value = "SELECT r.id as id, r.name as name, r.sleeps as sleeps, r.hotel_id as hotelId " +
            "FROM rooms r INNER JOIN order_room o ON r.id = o.fk_room " +
            "WHERE o.fk_order = ?1", nativeQuery = true)
    List<RoomProjection> findProjectionsByOrderId(long id);

    @Query(value = "select r.id as id" +
            " from rooms r inner join order_room orr on r.id = orr.fk_room inner join orders o on orr.fk_order = o.id where r.hotel_id = ?1 and o.state = ?2 and " +
            "((?3 >= o.check_in and ?3 < o.check_out) or (?4 > o.check_in and ?4 < o.check_out) or (?3 < o.check_in and ?4 > o.check_in))", nativeQuery = true)
    List<String> findOrderedRoomByHotelIdAndDate(long id, String state, LocalDate checkIn, LocalDate checkOut);
}
