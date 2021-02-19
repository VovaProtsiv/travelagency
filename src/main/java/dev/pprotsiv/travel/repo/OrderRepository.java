package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.Order;
import dev.pprotsiv.travel.projection.OrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o.id as id, o.checkIn as checkIn, o.checkOut as checkOut, o.client.id as userId," +
            "o.state as state FROM Order o WHERE o.client.id =?1")
    List<OrderProjection> getAllOrderProjectionByUserId(long id);


    @Query("SELECT o.id as id, o.checkIn as checkIn, o.checkOut as checkOut" +
            " FROM Order o WHERE o.id =?1")
    OrderProjection getProjectionById(long id);
}
