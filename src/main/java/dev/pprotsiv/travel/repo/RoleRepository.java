package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.ERole;
import dev.pprotsiv.travel.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository {
    Optional<Role> findByName(ERole name);
}
