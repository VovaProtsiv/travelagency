package dev.pprotsiv.travel.controller;

import dev.pprotsiv.travel.model.Hotel;
import dev.pprotsiv.travel.model.Room;
import dev.pprotsiv.travel.projection.RoomProjection;
import dev.pprotsiv.travel.service.HotelService;
import dev.pprotsiv.travel.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {
    private final RoomService roomService;
    private final HotelService hotelService;

    public RoomController(RoomService roomService, HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    @GetMapping("/{id}/all")
    public ResponseEntity<List<RoomProjection>> getRoomsByHotelId(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.getAllProjectionsByHotelId(id));
    }

    @GetMapping("/{room_id}")
    public ResponseEntity<RoomProjection> getRoom(@PathVariable long room_id) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.readProjectionById(room_id));
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<Room> addRoom(@PathVariable long id, @RequestBody Room room) {
        Hotel hotel = hotelService.readById(id);
        room.setHotel(hotel);
        roomService.create(room);
        hotel.addRoom(room);
        hotelService.update(hotel);
        return ResponseEntity.status(HttpStatus.OK).body(room);
    }

    @PutMapping("/{hotel_id}/edit/{room_id}")
    public Room editRoom(@PathVariable long hotel_id, @PathVariable long room_id, @RequestBody Room room) {
        room.setHotel(hotelService.readById(hotel_id));
        roomService.update(room);
        return room;
    }

    @DeleteMapping("/{hotel_id}/remove/{room_id}")
    public void deleteRoom(@PathVariable long hotel_id, @PathVariable long room_id) {
        Hotel hotel = hotelService.readById(hotel_id);
        hotel.removeRoom(roomService.readById(room_id));
        hotelService.update(hotel);
        roomService.delete(room_id);
    }
}
