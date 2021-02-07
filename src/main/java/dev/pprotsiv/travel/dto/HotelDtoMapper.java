package dev.pprotsiv.travel.dto;

import dev.pprotsiv.travel.model.Address;
import dev.pprotsiv.travel.model.Hotel;

import java.util.List;
import java.util.stream.Collectors;

public class HotelDtoMapper {
    public static HotelDto convertToDto(Hotel hotel) {
        HotelDto hotelDto = new HotelDto(hotel.getId(), hotel.getName());
        Address address = hotel.getAddress();
        if (address != null) {
            hotelDto.setAddressId(address.getId());
        }
        return hotelDto;
    }
    public static Hotel convertFromDto(HotelDto hotelDto){
        Hotel hotel = new Hotel(hotelDto.getName());
        hotel.setId(hotelDto.getId());
        Address address = new Address();
        address.setId(hotelDto.getAddressId());
        hotel.setAddress(address);
        return hotel;
    }
    public static List<HotelDto> convertToDto(List<Hotel> hotel) {
        return hotel.stream().map(HotelDtoMapper::convertToDto).collect(Collectors.toList());
    }

    public static List<Hotel> convertFromDto(List<HotelDto> dto) {
        return dto.stream().map(HotelDtoMapper::convertFromDto).collect(Collectors.toList());
    }
}
