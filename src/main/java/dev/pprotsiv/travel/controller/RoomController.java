package dev.pprotsiv.travel.controller;

import dev.pprotsiv.travel.model.Hotel;
import dev.pprotsiv.travel.model.Room;
import dev.pprotsiv.travel.service.HotelService;
import dev.pprotsiv.travel.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {
    private final RoomService roomService;
    private final HotelService hotelService;

    public RoomController(RoomService roomService, HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    @GetMapping("/{id}")
    public List<Room> getRoomsByHotelId(@PathVariable long id) {
        return roomService.getAllByHotelId(id);
    }

    @PostMapping("/{id}/add")
   public Room addRoom(@PathVariable long id,@RequestBody Room room){
        Hotel hotel = hotelService.readById(id);
        room.setHotel(hotel);
        roomService.create(room);
        List<Room> rooms = hotel.getRooms();
        rooms.add(room);
        hotel.setRooms(rooms);
        hotelService.update(hotel);
        return room;
    }
}
