package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.dto.OrderDto;
import dev.pprotsiv.travel.exception.BookedRoomsExceptions;
import dev.pprotsiv.travel.exception.IllegalDateException;
import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.Order;
import dev.pprotsiv.travel.model.State;
import dev.pprotsiv.travel.projection.OrderProjection;
import dev.pprotsiv.travel.repo.OrderRepository;
import dev.pprotsiv.travel.service.OrderService;
import dev.pprotsiv.travel.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {

    @TestConfiguration
    static class OrderServiceImplTestConfiguration {
        @Bean
        public OrderService accountServiceService(RoomService roomService, OrderRepository orderRepository) {
            return new OrderServiceImpl(orderRepository, roomService);
        }
    }

    @Autowired
    private OrderService orderService;

    @MockBean
    private RoomService roomService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    void createTest() {
        OrderDto dto = new OrderDto();
        Set<String> roomsId = new HashSet<>();
        roomsId.add("1");
        roomsId.add("2");
        dto.setRooms(roomsId);
        dto.setUserId("1");
        dto.setHotelId("1");
        dto.setCheckIn(LocalDate.now());
        dto.setCheckOut(LocalDate.now().plusDays(1));
        Order order = new Order();
        List<String> orderedRoomId = new ArrayList<>();
        orderedRoomId.add("4");
        orderedRoomId.add("3");
        Mockito.when(roomService.findOrderedRoomByHotelIdAndDate(1, State.NEW.name(), LocalDate.now(), LocalDate.now().plusDays(1)))
                .thenReturn(orderedRoomId);
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        assertEquals(order, orderService.create(dto));
    }

    @Test
    void createWhenOrderIsNullTest() {
        Mockito.when(orderRepository.save(null)).thenThrow(IllegalArgumentException.class);
        assertThrows(NullEntityReferenceException.class, () -> orderService.create(null));
    }

    @Test
    void createWhenOrderDateIsInvalidTest() {
        OrderDto dto = new OrderDto();
        Set<String> roomsId = new HashSet<>();
        roomsId.add("1");
        roomsId.add("2");
        dto.setRooms(roomsId);
        dto.setUserId("1");
        dto.setHotelId("1");
        dto.setCheckIn(LocalDate.now());
        dto.setCheckOut(LocalDate.now());
        assertThrows(IllegalDateException.class, () -> orderService.create(dto));
        dto.setCheckIn(null);
        dto.setCheckOut(null);
        assertThrows(IllegalDateException.class, () -> orderService.create(dto));
        dto.setCheckIn(LocalDate.now());
        assertThrows(IllegalDateException.class, () -> orderService.create(dto));
        dto.setCheckIn(LocalDate.now());
        dto.setCheckOut(LocalDate.now().minusDays(1));
        assertThrows(IllegalDateException.class, () -> orderService.create(dto));
    }

    @Test
    void createWhenOrderRoomsIsNullOrEmptyTest() {
        OrderDto dto = new OrderDto();
        Set<String> roomsId = new HashSet<>();
        dto.setRooms(roomsId);
        dto.setUserId("1");
        dto.setHotelId("1");
        dto.setCheckIn(LocalDate.now());
        dto.setCheckOut(LocalDate.now().plusDays(1));
        assertThrows(IllegalArgumentException.class, () -> orderService.create(dto));
        dto.setRooms(null);
        assertThrows(IllegalArgumentException.class, () -> orderService.create(dto));
    }

    @Test
    void createWhenOrderRoomsIsBookedTest() {
        OrderDto dto = new OrderDto();
        Set<String> roomsId = new HashSet<>();
        roomsId.add("1");
        roomsId.add("2");
        dto.setRooms(roomsId);
        dto.setUserId("1");
        dto.setHotelId("1");
        dto.setCheckIn(LocalDate.now());
        dto.setCheckOut(LocalDate.now().plusDays(1));
        List<String> orderedRoomId = new ArrayList<>();
        orderedRoomId.add("1");
        orderedRoomId.add("3");
        Mockito.when(roomService.findOrderedRoomByHotelIdAndDate(1, State.NEW.name(), LocalDate.now(), LocalDate.now().plusDays(1)))
                .thenReturn(orderedRoomId);
        assertThrows(BookedRoomsExceptions.class, () -> orderService.create(dto));

    }

    @Test
    void readByIdTest() {
        Order order = new Order();
        order.setId(1L);
        Long id = order.getId();
        Mockito.when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        assertEquals(order, orderService.readById(id));
        Order orderNew = new Order();
        assertNotEquals(orderNew, orderService.readById(id));
    }

    @Test
    void readByIdWhenIdIsNotExistingTest() {
        Mockito.when((orderRepository.findById(2L))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> orderService.readById(2L));
    }

    @Test
    void readProjectionByIdTest() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        OrderProjection orderProjection = factory.createProjection(OrderProjection.class);
        Mockito.when(orderRepository.getProjectionById(1L)).thenReturn(orderProjection);
        assertEquals(orderProjection, orderService.readProjectionById(1L));
    }

    @Test
    void readProjectionByNotExistingIdTest() {
        assertThrows(EntityNotFoundException.class, () -> orderService.readProjectionById(2L));
    }

    @Test
    void updateTest() {
    }

    @Test
    void deleteTest() {
        Order order = new Order();
        order.setId(1L);
        Long id = order.getId();
        Mockito.when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        orderService.delete(id);
        Mockito.verify(orderRepository).delete(order);
    }

    @Test
    void deleteWhenOrderIsNullTest() {
        Mockito.when(orderRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> orderService.delete(2L));
    }

    @Test
    void getProjectionsByUserIdTest() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        OrderProjection orderProjection1 = factory.createProjection(OrderProjection.class);
        OrderProjection orderProjection2 = factory.createProjection(OrderProjection.class);
        OrderProjection orderProjection3 = factory.createProjection(OrderProjection.class);
        List<OrderProjection> orderProjections = new ArrayList<>();
        orderProjections.add(orderProjection1);
        orderProjections.add(orderProjection2);
        orderProjections.add(orderProjection3);
        Mockito.when(orderRepository.getAllOrderProjectionByUserId(1L)).thenReturn(orderProjections);
        assertEquals(orderProjections, orderService.getProjectionsByUserId(1L));

    }

    @Test
    void getEmptyProjectionsByUserIdTest() {
        assertTrue(orderService.getProjectionsByUserId(2L).isEmpty());
    }
}
