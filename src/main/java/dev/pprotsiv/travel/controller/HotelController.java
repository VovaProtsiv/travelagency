package dev.pprotsiv.travel.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.pprotsiv.travel.dto.HotelDto;
import dev.pprotsiv.travel.dto.HotelDtoMapper;
import dev.pprotsiv.travel.model.Hotel;
import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.service.HotelService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotels")
    public List<HotelDto> getHotels() {
     return  HotelDtoMapper.convertToDto(hotelService.getAll());
    }
}
