package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {

}
