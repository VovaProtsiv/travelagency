package dev.pprotsiv.travel.service;

import dev.pprotsiv.travel.dto.OrderDto;
import dev.pprotsiv.travel.exception.IllegalDateException;
import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.Room;
import dev.pprotsiv.travel.repo.OrderRepository;
import dev.pprotsiv.travel.service.Impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class AccountServiceTest {
    @TestConfiguration
    static class AccountServiceTestConfiguration {
        @Bean
        public AccountService accountServiceService(RoomService roomService, OrderRepository orderRepository) {
            return new OrderServiceImpl(orderRepository,roomService);
        }
    }

    @Autowired
    private AccountService accountService;

    @MockBean
    private RoomService roomService;

    @MockBean
    private OrderRepository orderRepository;


    @Test
    void getTotalAmount() {
        OrderDto dto = new OrderDto();
        dto.setCheckIn(LocalDate.now());
        dto.setCheckOut(LocalDate.now().plusDays(2));
        Set<String> rooms = new HashSet<>();
        rooms.add("1");
        rooms.add("2");
        dto.setRooms(rooms);
        Room room = new Room();
        room.setId(1L);
        room.setPrice(new BigDecimal(100));
        Room room1 = new Room();
        room1.setId(2L);
        room1.setPrice(new BigDecimal(150));
        Mockito.when(roomService.readById(1L)).thenReturn(room);
        Mockito.when(roomService.readById(2L)).thenReturn(room1);
        assertEquals(new BigDecimal(500), accountService.getTotalAmount(dto));
    }

    @Test
    void getTotalAmountDtoIsNull(){
        assertThrows(NullEntityReferenceException.class,()->accountService.getTotalAmount(null));
    }
    @Test
    void getTotalAmountInValidDate(){
        OrderDto dto = new OrderDto();
        dto.setCheckIn(LocalDate.now());
        dto.setCheckOut(LocalDate.now());
        assertThrows(IllegalDateException.class,()->accountService.getTotalAmount(dto));
        dto.setCheckIn(null);
        dto.setCheckOut(null);
        assertThrows(IllegalDateException.class,()->accountService.getTotalAmount(dto));
        dto.setCheckIn(LocalDate.now());
        assertThrows(IllegalDateException.class,()->accountService.getTotalAmount(dto));
        dto.setCheckIn(LocalDate.now());
        dto.setCheckOut(LocalDate.now().minusDays(1));
        assertThrows(IllegalDateException.class,()->accountService.getTotalAmount(dto));
    }
}