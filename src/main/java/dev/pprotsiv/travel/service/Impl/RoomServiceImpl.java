package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.Room;
import dev.pprotsiv.travel.projection.RoomProjection;
import dev.pprotsiv.travel.repo.RoomRepository;
import dev.pprotsiv.travel.service.RoomService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room create(Room room) {
        if (room != null) {
            return roomRepository.save(room);
        }
        throw new NullEntityReferenceException("Room cannot be 'null'");
    }

    @Override
    public Room readById(long id) {
        return roomRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Room with id " + id + " not found"));
    }

    @Override
    public RoomProjection readProjectionById(long id) {
        return Optional.ofNullable(roomRepository.findProjectionById(id)).orElseThrow(
                () -> new EntityNotFoundException("Room with id " + id + " not found"));
    }

    @Override
    public Room update(Room room) {
        if (room != null) {
            readById(room.getId());
            return roomRepository.save(room);
        }
        throw new NullEntityReferenceException("Room cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        roomRepository.delete(readById(id));
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.isEmpty() ? new ArrayList<>() : rooms;
    }

    @Override
    public List<RoomProjection> getAllProjectionsByHotelId(long id) {
        List<RoomProjection> rooms = roomRepository.findAllProjectionByHotelId(id);
        return rooms.isEmpty() ? new ArrayList<>() : rooms;
    }

    @Override
    public List<RoomProjection> getProjectionsByOrderID(long id) {
        List<RoomProjection> rooms = roomRepository.findProjectionsByOrderId(id);
        return rooms.isEmpty() ? new ArrayList<>() : rooms;
    }


}
