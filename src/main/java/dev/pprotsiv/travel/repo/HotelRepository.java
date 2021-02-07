package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
