package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.Order;
import dev.pprotsiv.travel.model.State;
import dev.pprotsiv.travel.projection.OrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o.id as id, o.checkIn as checkIn, o.checkOut as checkOut, o.client.id as userId," +
            "o.state as state, o.hotel.id as hotelId, o.hotel.name as hotelName FROM Order o WHERE o.client.id =?1")
    List<OrderProjection> getAllOrderProjectionByUserId(long id );

    @Query("SELECT o.id as id, o.checkIn as checkIn, o.checkOut as checkOut, o.client.id as userId," +
            "o.state as state, o.hotel.id as hotelId, o.hotel.name as hotelName FROM Order o WHERE o.hotel.id =?1 AND o.state =?2")
    List<OrderProjection> getAllOrderProjectionByHotelIdAndState(long id, State state);

    @Query("SELECT o.id as id, o.checkIn as checkIn, o.checkOut as checkOut, o.hotel.id as hotelId, o.hotel.name as hotelName" +
            " FROM Order o WHERE o.id =?1")
    OrderProjection getProjectionById(long id);
}
