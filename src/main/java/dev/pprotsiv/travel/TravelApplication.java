package dev.pprotsiv.travel;

import dev.pprotsiv.travel.model.Address;
import dev.pprotsiv.travel.model.Hotel;
import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.repo.UserRepository;
import dev.pprotsiv.travel.service.AddressService;
import dev.pprotsiv.travel.service.HotelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class TravelApplication {



    public static void main(String[] args) {
        SpringApplication.run(TravelApplication.class, args);
    }

}
