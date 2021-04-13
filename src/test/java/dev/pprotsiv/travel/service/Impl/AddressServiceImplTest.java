package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.Address;
import dev.pprotsiv.travel.repo.AddressRepository;
import dev.pprotsiv.travel.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AddressServiceImplTest {

    @TestConfiguration
    static class AddressServiceImplTestConfiguration {
        @Bean
        public AddressService accountServiceService(AddressRepository addressRepository) {
            return new AddressServiceImpl(addressRepository);
        }
    }

    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Test
    void createTest() {
        Address address = new Address();
        address.setId(1L);
        Address address1 = new Address();
        address1.setId(2L);
        Mockito.when(addressRepository.save(address)).thenReturn(address);
        Mockito.when(addressRepository.save(address1)).thenReturn(address1);
        assertEquals(address, addressService.create(address));
        assertNotEquals(address, addressService.create(address1));
    }

    @Test
    void createNullAddressTest() {
        assertThrows(NullEntityReferenceException.class, () -> addressService.create(null));
    }

    @Test
    void readByIdTest() {
        Address address = new Address();
        address.setId(1L);
        Long id = address.getId();
        Mockito.when(addressRepository.findById(id)).thenReturn(Optional.of(address));
        assertEquals(address, addressService.readById(id));
    }

    @Test
    void readByIdNotExistingTest() {
        Mockito.when((addressRepository.findById(2L))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> addressService.readById(2L));
    }

    @Test
    void updateTest() {
        Address address1 = new Address();
        address1.setId(1L);
        Address address2 = new Address();
        address2.setId(2L);
        Mockito.when(addressRepository.findById(address1.getId())).thenReturn(Optional.of(address1));
        Mockito.when(addressRepository.findById(address2.getId())).thenReturn(Optional.of(address2));
        Mockito.when(addressRepository.save(address1)).thenReturn(address1);

        Mockito.when(addressRepository.save(address2)).thenReturn(address2);
        assertEquals(address1, addressService.update(address1));
        assertEquals(address2, addressService.update(address2));
        assertNotEquals(address1, addressService.update(address2));
    }

    @Test
    void updateNotExistingAddressTest() {
        Address address1 = new Address();
        address1.setId(1L);
        assertThrows(EntityNotFoundException.class, () -> addressService.update(address1));
    }

    @Test
    void deleteTest() {
        Address address = new Address();
        address.setId(1L);
        Long id = address.getId();
        Mockito.when(addressRepository.findById(id)).thenReturn(Optional.of(address));
        addressService.delete(id);
    }

    @Test
    void deleteNotExistingAddressTest() {
        Mockito.when(addressRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> addressService.delete(2L));
    }

    @Test
    void getAllTest() {
        Address address1 = new Address();
        address1.setId(1L);
        Address address2 = new Address();
        address1.setId(2L);
        List<Address> addresses = new ArrayList<>();
        addresses.add(address1);
        addresses.add(address2);
        Mockito.when(addressRepository.findAll()).thenReturn(addresses);
        assertEquals(addresses,addressService.getAll());
        addresses.clear();
        assertTrue(addressService.getAll().isEmpty());
    }
}