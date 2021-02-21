package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.Address;
import dev.pprotsiv.travel.model.Hotel;
import dev.pprotsiv.travel.projection.HotelProjection;
import dev.pprotsiv.travel.repo.HotelRepository;
import dev.pprotsiv.travel.service.AddressService;
import dev.pprotsiv.travel.service.HotelService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel create(Hotel hotel) {
        if (hotel != null) {
            return hotelRepository.save(hotel);
        }
        throw new NullEntityReferenceException("Hotel cannot be 'null'");
    }

    @Override
    public Hotel readById(long id) {
        return hotelRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Hotel with id " + id + " not found"));
    }

    @Override
    public HotelProjection readProjectionById(long id) {
        return Optional.ofNullable(hotelRepository.findProjectionById(id)).orElseThrow(
                () -> new EntityNotFoundException("Hotel with id " + id + " not found")
        );
    }

    @Override
    public Hotel update(Hotel hotel) {
        if (hotel != null) {
            readById(hotel.getId());
            return hotelRepository.save(hotel);
        }
        throw new NullEntityReferenceException("Hotel cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        hotelRepository.delete(readById(id));
    }

    @Override
    public List<HotelProjection> getAllProjections() {
        List<HotelProjection> hotels = hotelRepository.findAllProjection();
        return hotels.isEmpty() ? new ArrayList<>() : hotels;
    }

    @Override
    public List<HotelProjection> getAllProjections(String name) {
        List<HotelProjection> hotels = hotelRepository.findAllProjections(name);
        return hotels.isEmpty() ? new ArrayList<>() : hotels;
    }

}
