package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.Room;
import dev.pprotsiv.travel.model.State;
import dev.pprotsiv.travel.projection.RoomProjection;
import dev.pprotsiv.travel.repo.RoomRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class RoomServiceImplTest {
    @TestConfiguration
    static class RoomServiceImplTestConfiguration {
        @Bean
        public RoomService accountServiceService(RoomRepository roomRepository) {
            return new RoomServiceImpl(roomRepository);
        }
    }

    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;

    @Test
    void create() {
        Room room = new Room();
        room.setId(1L);
        Room room1 = new Room();
        room1.setId(2L);
        Mockito.when(roomRepository.save(room)).thenReturn(room);
        Mockito.when(roomRepository.save(room1)).thenReturn(room1);
        assertEquals(room, roomService.create(room));
        assertNotEquals(room, roomService.create(room1));
    }

    @Test
    void createNullRoomTest() {
        assertThrows(NullEntityReferenceException.class, () -> roomService.create(null));
    }

    @Test
    void readById() {
        Room room = new Room();
        room.setId(1L);
        Long id = room.getId();
        Mockito.when(roomRepository.findById(id)).thenReturn(Optional.of(room));
        assertEquals(room, roomService.readById(id));
    }

    @Test
    void readByNotExistingIdTest() {
        Mockito.when((roomRepository.findById(2L))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> roomService.readById(2L));
    }

    @Test
    void readProjectionById() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        RoomProjection roomProjection = factory.createProjection(RoomProjection.class);
        Mockito.when(roomRepository.findProjectionById(1L)).thenReturn(roomProjection);
        assertEquals(roomProjection, roomService.readProjectionById(1L));
    }

    @Test
    void readProjectionByNotExistingIdTest() {
        assertThrows(EntityNotFoundException.class, () -> roomService.readProjectionById(2L));
    }

    @Test
    void update() {
        Room room1 = new Room();
        room1.setId(1L);
        Room room2 = new Room();
        room2.setId(2L);
        Mockito.when(roomRepository.findById(room1.getId())).thenReturn(Optional.of(room1));
        Mockito.when(roomRepository.findById(room2.getId())).thenReturn(Optional.of(room2));
        Mockito.when(roomRepository.save(room1)).thenReturn(room1);

        Mockito.when(roomRepository.save(room2)).thenReturn(room2);
        assertEquals(room1, roomService.update(room1));
        assertEquals(room2, roomService.update(room2));
        assertNotEquals(room1, roomService.update(room2));
    }

    @Test
    void updateNotExistingRoomTest() {
        Room room = new Room();
        room.setId(1L);
        assertThrows(EntityNotFoundException.class, () -> roomService.update(room));
    }

    @Test
    void deleteTest() {
        Room room = new Room();
        room.setId(1L);
        Long id = room.getId();
        Mockito.when(roomRepository.findById(id)).thenReturn(Optional.of(room));
        roomService.delete(id);
    }

    @Test
    void deleteNotExistingHotelTest() {
        Mockito.when(roomRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> roomService.delete(2L));
    }

    @Test
    void getAll() {
        Room room1 = new Room();
        room1.setId(1L);
        Room room2 = new Room();
        room2.setId(2L);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        Mockito.when(roomRepository.findAll()).thenReturn(rooms);
        assertEquals(rooms, roomService.getAll());
    }

    @Test
    void getAllProjectionsByHotelId() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        RoomProjection roomProjection1 = factory.createProjection(RoomProjection.class);
        RoomProjection roomProjection2 = factory.createProjection(RoomProjection.class);
        List<RoomProjection> rooms = new ArrayList<>();
        rooms.add(roomProjection1);
        rooms.add(roomProjection2);
        long id = 1L;
        Mockito.when(roomRepository.findAllProjectionByHotelId(id)).thenReturn(rooms);
        assertEquals(rooms, roomService.getAllProjectionsByHotelId(id));
    }

    @Test
    void getProjectionsByOrderID() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        RoomProjection roomProjection1 = factory.createProjection(RoomProjection.class);
        RoomProjection roomProjection2 = factory.createProjection(RoomProjection.class);
        List<RoomProjection> rooms = new ArrayList<>();
        rooms.add(roomProjection1);
        rooms.add(roomProjection2);
        long id = 1L;
        Mockito.when(roomRepository.findProjectionsByOrderId(id)).thenReturn(rooms);
        assertEquals(rooms, roomService.getProjectionsByOrderID(id));
    }

    @Test
    void findOrderedRoomByHotelIdAndDate() {
        List<String> rooms = new ArrayList<>();
        rooms.add("1");
        rooms.add("2");
        long id = 1L;
        String state = State.NEW.name();
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = LocalDate.now().plusDays(1);
        Mockito.when(roomRepository.findOrderedRoomByHotelIdAndDate(id, state, checkIn, checkOut))
                .thenReturn(rooms);
        assertEquals(rooms, roomService.findOrderedRoomByHotelIdAndDate(id, state, checkIn, checkOut));
    }
}