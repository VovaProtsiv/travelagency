package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.Hotel;
import dev.pprotsiv.travel.repo.HotelRepository;
import dev.pprotsiv.travel.service.HotelService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Hotel> getAll() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.isEmpty() ? new ArrayList<>() : hotels;
    }
}
