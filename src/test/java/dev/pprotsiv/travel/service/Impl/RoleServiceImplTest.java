package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.model.ERole;
import dev.pprotsiv.travel.model.Role;
import dev.pprotsiv.travel.projection.RoleProjection;
import dev.pprotsiv.travel.repo.RoleRepository;
import dev.pprotsiv.travel.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class RoleServiceImplTest {
    @TestConfiguration
    static class RoleServiceImplTestConfiguration {
        @Bean
        public RoleService accountServiceService(RoleRepository roleRepository) {
            return new RoleServiceImpl(roleRepository);
        }
    }

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Test
    void findByName() {
        Role role = new Role();
        role.setId(1);
        ERole roleUser = ERole.ROLE_USER;
        role.setName(roleUser);
        Mockito.when(roleRepository.findByName(roleUser)).thenReturn(Optional.of(role));
        assertEquals(role, roleService.findByName(roleUser));
    }

    @Test
    void findByNullName() {
        assertThrows(EntityNotFoundException.class, () -> roleService.findByName(null));
    }

    @Test
    void findByNotExistingName() {
        ERole roleUser = ERole.ROLE_USER;
        Mockito.when(roleRepository.findByName(roleUser)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> roleService.findByName(roleUser));
    }

    @Test
    void findAll() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        RoleProjection user = factory.createProjection(RoleProjection.class);
        RoleProjection moderator = factory.createProjection(RoleProjection.class);

        List<RoleProjection> roles = new ArrayList<>();
        roles.add(user);
        roles.add(moderator);
        Mockito.when(roleRepository.findAllProjections()).thenReturn(roles);
        assertEquals(roles, roleService.findAll());
    }


    @Test
    void findByUserId() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        RoleProjection user = factory.createProjection(RoleProjection.class);
        RoleProjection moderator = factory.createProjection(RoleProjection.class);

        List<RoleProjection> roles = new ArrayList<>();
        roles.add(user);
        roles.add(moderator);
        int id = 1;
        Mockito.when(roleRepository.findByProjectionByUserID(id)).thenReturn(roles);
        assertEquals(roles, roleService.findByUserId(id));
    }
}