package dev.pprotsiv.travel.service;

import dev.pprotsiv.travel.model.Address;

import java.util.List;

public interface AddressService {
    Address create(Address address);
    Address readById(long id);
    Address update(Address address);
    void delete(long id);
    List<Address> getAll();
}
