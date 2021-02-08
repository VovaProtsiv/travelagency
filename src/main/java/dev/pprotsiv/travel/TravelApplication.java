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

    @Bean
    CommandLineRunner init(UserRepository userRepository, HotelService hotelService, AddressService addressService) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                User user = new User(name, name.toLowerCase() + "@domain.com");
                userRepository.save(user);
            });
            /*Stream.of("Quick Stop Hotel","Hillside Hotel","Malibu").forEach(name->{
                Hotel hotel = new Hotel();
                hotel.setName(name);
                hotelService.create(hotel);

            });
            userRepository.findAll().forEach(System.out::println);
            System.out.println("-------Hotels-------");
            hotelService.getAll().forEach(System.out::println);
            Address address1 = new Address();
            address1.setCountry("UK1");
            address1.setCity("IF1");
            address1.setStreet("Franka1");
            address1.setHouseNumber("12-b1");
            address1.setHotel(hotelService.readById(2));
            addressService.create(address1);
            System.out.println("------Address-------");
            addressService.getAll().forEach(System.out::println);*/
        };
    }
}
