package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.Address;
import dev.pprotsiv.travel.model.Hotel;
import dev.pprotsiv.travel.projection.HotelProjection;
import dev.pprotsiv.travel.projection.OrderProjection;
import dev.pprotsiv.travel.repo.AddressRepository;
import dev.pprotsiv.travel.repo.HotelRepository;
import dev.pprotsiv.travel.service.AddressService;
import dev.pprotsiv.travel.service.HotelService;
import org.checkerframework.checker.units.qual.A;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class HotelServiceImplTest {
    @TestConfiguration
    static class HotelServiceImplTestConfiguration {
        @Bean
        public HotelService accountServiceService(HotelRepository hotelRepository) {
            return new HotelServiceImpl(hotelRepository);
        }
    }
    @MockBean
    private HotelRepository hotelRepository;

    @Autowired
    private HotelService hotelService;

    @Test
    void createTest() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        Hotel hotel1 = new Hotel();
        hotel1.setId(2L);
        Mockito.when(hotelRepository.save(hotel)).thenReturn(hotel);
        Mockito.when(hotelRepository.save(hotel1)).thenReturn(hotel1);
        assertEquals(hotel, hotelService.create(hotel));
        assertNotEquals(hotel, hotelService.create(hotel1));
    }
    @Test
    void createNullHotelTest() {
        assertThrows(NullEntityReferenceException.class, () -> hotelService.create(null));
    }
    @Test
    void readByIdTest() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        Long id = hotel.getId();
        Mockito.when(hotelRepository.findById(id)).thenReturn(Optional.of(hotel));
        assertEquals(hotel, hotelService.readById(id));
    }
    @Test
    void readByNotExistingIdTest() {
        Mockito.when((hotelRepository.findById(2L))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> hotelService.readById(2L));
    }
    @Test
    void readProjectionByIdTest() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        HotelProjection hotelProjection = factory.createProjection(HotelProjection.class);
        Mockito.when(hotelRepository.findProjectionById(1L)).thenReturn(hotelProjection);
        assertEquals(hotelProjection, hotelService.readProjectionById(1L));
    }
    @Test
    void readProjectionByNotExistingIdTest() {
        assertThrows(EntityNotFoundException.class, () -> hotelService.readProjectionById(2L));
    }
    @Test
    void updateTest() {
        Hotel hotel1 = new Hotel();
        hotel1.setId(1L);
        Hotel hotel2 = new Hotel();
        hotel2.setId(2L);
        Mockito.when(hotelRepository.findById(hotel1.getId())).thenReturn(Optional.of(hotel1));
        Mockito.when(hotelRepository.findById(hotel2.getId())).thenReturn(Optional.of(hotel2));
        Mockito.when(hotelRepository.save(hotel1)).thenReturn(hotel1);

        Mockito.when(hotelRepository.save(hotel2)).thenReturn(hotel2);
        assertEquals(hotel1, hotelService.update(hotel1));
        assertEquals(hotel2, hotelService.update(hotel2));
        assertNotEquals(hotel1, hotelService.update(hotel2));
    }
    @Test
    void updateNotExistingHotelTest() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        assertThrows(EntityNotFoundException.class, () -> hotelService.update(hotel));
    }
    @Test
    void deleteTest() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        Long id = hotel.getId();
        Mockito.when(hotelRepository.findById(id)).thenReturn(Optional.of(hotel));
        hotelService.delete(id);
    }

    @Test
    void deleteNotExistingHotelTest() {
        Mockito.when(hotelRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> hotelService.delete(2L));
    }

    @Test
    void getAllProjectionsTest() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        HotelProjection hotelProjection1 = factory.createProjection(HotelProjection.class);
        HotelProjection hotelProjection2 = factory.createProjection(HotelProjection.class);
        List<HotelProjection> hotelProjections = new ArrayList<>();
        hotelProjections.add(hotelProjection1);
        hotelProjections.add(hotelProjection2);
        Mockito.when(hotelRepository.findAllProjection()).thenReturn(hotelProjections);
        assertEquals(hotelProjections,hotelService.getAllProjections());
    }

    @Test
    void testGetAllProjectionsTest() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        HotelProjection hotelProjection1 = factory.createProjection(HotelProjection.class);
        HotelProjection hotelProjection2 = factory.createProjection(HotelProjection.class);
        List<HotelProjection> hotelProjections = new ArrayList<>();
        hotelProjections.add(hotelProjection1);
        hotelProjections.add(hotelProjection2);
        String name = "SomeName";
        Mockito.when(hotelRepository.findAllProjections(name)).thenReturn(hotelProjections);
        assertEquals(hotelProjections,hotelService.getAllProjections(name));
    }
}