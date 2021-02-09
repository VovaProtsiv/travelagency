package dev.pprotsiv.travel.controller;

import dev.pprotsiv.travel.model.Address;
import dev.pprotsiv.travel.model.Hotel;
import dev.pprotsiv.travel.service.AddressService;
import dev.pprotsiv.travel.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@CrossOrigin(origins = "http://localhost:4200")
public class HotelController {
    private final HotelService hotelService;
    private final AddressService addressService;

    public HotelController(HotelService hotelService, AddressService addressService) {
        this.hotelService = hotelService;
        this.addressService = addressService;
    }

    @GetMapping("/all")
    public List<Hotel> getHotels() {
        return hotelService.getAll();
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

    @PostMapping("/{hotel_id}/add/address")
    public Hotel addAddress(@PathVariable long hotel_id, @RequestBody Address address) {
        Hotel hotel = hotelService.readById(hotel_id);
        hotel.setAddress(address);
        return hotelService.update(hotel);
    }

    @PutMapping("/{hotel_id}/update/address/{address_id}")
    public Hotel editAddress(@PathVariable long hotel_id, @PathVariable long address_id, @RequestBody Address address) {
        Hotel hotel = hotelService.readById(hotel_id);
        addressService.readById(address_id);
        addressService.update(address);
        return hotel;
    }
}

