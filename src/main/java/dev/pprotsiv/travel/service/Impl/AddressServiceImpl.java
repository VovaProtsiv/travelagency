package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.Address;
import dev.pprotsiv.travel.repo.AddressRepository;
import dev.pprotsiv.travel.service.AddressService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;


    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;

    }

    @Override
       public Address create(Address address) {
        if (address != null) {
            return addressRepository.save(address);
        }
        throw new NullEntityReferenceException("Address cannot be 'null'");
    }

    @Override
    public Address readById(long id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Address with id " + id + " not found"));
    }

    @Override
    public Address update(Address address) {
        if (address != null) {
            readById(address.getId());
            return addressRepository.save(address);
        }
        throw new NullEntityReferenceException("Address cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        addressRepository.delete(readById(id));
    }

    @Override
    public List<Address> getAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.isEmpty() ? new ArrayList<>() : addresses;
    }
}
