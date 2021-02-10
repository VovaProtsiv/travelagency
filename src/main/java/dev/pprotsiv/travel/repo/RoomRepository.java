package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByHotel_Id(long id);
}
