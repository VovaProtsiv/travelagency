package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u.id as id, u.username as name, u.email as email FROM User u")
    List<UserProjection> getAllProjections();

    @Query("SELECT u.id as id, u.username as name, u.email as email FROM User u WHERE u.id = ?1")
    UserProjection findProjectionById(long id);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
