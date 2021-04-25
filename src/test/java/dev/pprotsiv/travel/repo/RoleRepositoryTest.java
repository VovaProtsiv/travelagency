package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.ERole;
import dev.pprotsiv.travel.model.Role;
import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.projection.RoleProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByName() {
        assertEquals(ERole.ROLE_MODERATOR, roleRepository.findByName(ERole.ROLE_MODERATOR).get().getName());
    }

    @Test
    void findAllProjections() {
        List<RoleProjection> allProjections = roleRepository.findAllProjections();
        List<String> expected = new ArrayList<>();
        expected.add(ERole.ROLE_ADMIN.name());
        expected.add(ERole.ROLE_MODERATOR.name());
        expected.add(ERole.ROLE_USER.name());
        List<String> actual = allProjections.stream().map(RoleProjection::getName).collect(Collectors.toList());
        assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    }

    @Test
    void findByProjectionByUserID() {
        User user = new User();
        user.setEmail("test@user.com");
        user.setUsername("testUserName");
        Optional<Role> role = roleRepository.findByName(ERole.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        role.ifPresent(roles::add);
        user.setRoles(roles);

        userRepository.save(user);
        Long userId = user.getId();

        List<RoleProjection> roleProjections = roleRepository.findByProjectionByUserID(userId);
        boolean isEqualsUser = roleProjections.stream()
                .map(RoleProjection::getName)
                .anyMatch(e -> e.equals(ERole.ROLE_USER.name()));
        assertTrue(isEqualsUser);
    }
}