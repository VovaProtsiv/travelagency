package dev.pprotsiv.travel.dto;

import dev.pprotsiv.travel.model.Hotel;
import dev.pprotsiv.travel.model.Order;
import dev.pprotsiv.travel.model.Room;
import dev.pprotsiv.travel.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class OrderDtoMapper {
    public static Order fromDto(OrderDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setCheckIn(dto.getCheckIn());
        order.setCheckOut(dto.getCheckOut());
        order.setState(dto.getState());
        User user = new User();
        user.setId(Long.parseLong(dto.getUserId()));
        order.setClient(user);
        Hotel hotel = new Hotel();
        hotel.setId(Long.parseLong(dto.getHotelId()));
        order.setHotel(hotel);
        order.setRooms(getRooms(dto.getRooms()));
        return order;
    }

    private static Set<Room> getRooms(Set<String> setIds) {
        return setIds.stream().map(e -> {
            Room r = new Room();
            r.setId(Long.parseLong(e));
            return r;
        }).collect(Collectors.toSet());
    }
}
