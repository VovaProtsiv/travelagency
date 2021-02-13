package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.Hotel;
import dev.pprotsiv.travel.projection.HotelProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

   @Query("SELECT h.name as name, h.address.country as country, h.address.city as city," +
           "h.address.street as street, h.address.houseNumber as houseNumber from Hotel h WHERE h.id = ?1")
    HotelProjection findProjectionById(long id);

    @Query("SELECT h.name as name, h.address.country as country, h.address.city as city," +
            "h.address.street as street, h.address.houseNumber as houseNumber from Hotel h")
    List<HotelProjection> findAllProjection();
}
