package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
