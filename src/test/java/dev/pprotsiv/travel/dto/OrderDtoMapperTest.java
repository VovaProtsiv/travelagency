package dev.pprotsiv.travel.dto;

import dev.pprotsiv.travel.model.Order;
import dev.pprotsiv.travel.model.State;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class OrderDtoMapperTest {

    @Test
    void fromDto() {
        OrderDto dto = new OrderDto();
        long id = 1L;
        BigDecimal totalAmount = new BigDecimal(1000);
        State state = State.NEW;
        String hotelId = "10";
        String userId = "11";
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = LocalDate.now().plusDays(1);
        Set<String> rooms = new HashSet<>();
        rooms.add("1");
        rooms.add("2");
        rooms.add("3");

        dto.setId(id);
        dto.setTotalAmount(totalAmount);
        dto.setState(state);
        dto.setHotelId(hotelId);
        dto.setUserId(userId);
        dto.setCheckIn(checkIn);
        dto.setCheckOut(checkOut);
        dto.setRooms(rooms);
        Order order = OrderDtoMapper.fromDto(dto);

        assertEquals(id, order.getId());
        assertEquals(totalAmount, order.getTotalAmount());
        assertEquals(checkIn, order.getCheckIn());
        assertEquals(checkOut, order.getCheckOut());
        assertEquals(state, order.getState());
        assertEquals(hotelId, order.getHotel().getId().toString());
        assertEquals(userId, order.getClient().getId().toString());

        Set<String> roomsId = order.getRooms().stream().map(e -> e.getId().toString()).collect(Collectors.toSet());

        assertEquals(rooms,roomsId);
    }
}