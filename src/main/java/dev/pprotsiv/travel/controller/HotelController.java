package dev.pprotsiv.travel.controller;

import dev.pprotsiv.travel.model.Hotel;
import dev.pprotsiv.travel.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@CrossOrigin(origins = "http://localhost:4200")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/all")
    public List<Hotel> getHotels() {
     return  hotelService.getAll();
    }
    @GetMapping("/{id}")
    public Hotel getUserById(@PathVariable long id) {
        return hotelService.readById(id);
    }

    @PostMapping
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.create(hotel);
    }

    @DeleteMapping("/{id}")
    void deleteHotel(@PathVariable long id) {
        hotelService.delete(id);
    }

    @PutMapping
    public Hotel editHotel(@RequestBody Hotel hotel) {
        return hotelService.update(hotel);
    }
}
