package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.ERole;
import dev.pprotsiv.travel.model.Role;
import dev.pprotsiv.travel.projection.RoleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

    @Query("SELECT id as id, name as name FROM Role")
    List<RoleProjection> findAllProjections();

    @Query(value = "SELECT r.name as name FROM roles r INNER JOIN user_roles u ON u.role_id = r.id AND u.user_id = ?1",nativeQuery = true)
    List<RoleProjection> findByProjectionByUserID(long id);
}
