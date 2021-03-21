package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.dto.UserDto;
import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.Role;
import dev.pprotsiv.travel.model.Room;
import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.projection.RoomProjection;
import dev.pprotsiv.travel.projection.UserProjection;
import dev.pprotsiv.travel.repo.RoomRepository;
import dev.pprotsiv.travel.repo.UserRepository;
import dev.pprotsiv.travel.service.RoomService;
import dev.pprotsiv.travel.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @TestConfiguration
    static class UserServiceImplTestConfiguration {
        @Bean
        public UserService accountServiceService(UserRepository userRepository) {
            return new UserServiceImpl(userRepository);
        }
    }

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void create() {
        User user = new User();
        user.setId(1L);
        User user1 = new User();
        user1.setId(2L);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.save(user1)).thenReturn(user1);
        assertEquals(user, userService.create(user));
        assertNotEquals(user, userService.create(user1));
    }

    @Test
    void createNullUSerTest() {
        assertThrows(NullEntityReferenceException.class, () -> userService.create(null));
    }

    @Test
    void readById() {
        User user = new User();
        user.setId(1L);
        Long id = user.getId();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        assertEquals(user, userService.readById(id));
    }

    @Test
    void readByNotExistingIdTest() {
        Mockito.when((userRepository.findById(2L))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.readById(2L));
    }

    @Test
    void getProjection() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        UserProjection userProjection = factory.createProjection(UserProjection.class);
        Mockito.when(userRepository.findProjectionById(1L)).thenReturn(userProjection);
        assertEquals(userProjection, userService.getProjection(1L));
    }

    @Test
    void readProjectionByNotExistingIdTest() {
        assertThrows(EntityNotFoundException.class, () -> userService.getProjection(2L));
    }

    @Test
    void getDto() {
        User user = new User();
        user.setId(1L);
        String pass = "password";
        String username = "username";
        String email = "email@email.com";
        user.setPassword(pass);
        user.setUsername(username);
        user.setEmail(email);
        Role role = new Role();
        role.setId(2);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        Long id = user.getId();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        UserDto dto = userService.getDto(id);
        assertEquals(id, dto.getId());
        assertEquals(pass, dto.getPassword());
        assertEquals(username, dto.getUsername());
        assertEquals(email, dto.getEmail());
        assertEquals(roles, dto.getRoles());

    }

    @Test
    void getDtoByNotExistingIdTest() {
        Mockito.when((userRepository.findById(2L))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.getDto(2L));
    }

    @Test
    void update() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);
        Mockito.when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        Mockito.when(userRepository.findById(user2.getId())).thenReturn(Optional.of(user2));
        Mockito.when(userRepository.save(user1)).thenReturn(user1);

        Mockito.when(userRepository.save(user2)).thenReturn(user2);
        assertEquals(user1, userService.update(user1));
        assertEquals(user2, userService.update(user2));
        assertNotEquals(user1, userService.update(user2));
    }

    @Test
    void updateNotExistingUserTest() {
        User user = new User();
        user.setId(1L);
        assertThrows(EntityNotFoundException.class, () -> userService.update(user));
    }

    @Test
    void delete() {
        User user = new User();
        user.setId(1L);
        Long id = user.getId();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        userService.delete(id);
        Mockito.verify(userRepository).delete(user);
    }

    @Test
    void deleteNotExistingUserTest() {
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.delete(2L));
    }

    @Test
    void getAllDtos() {
        //TODO
    }


    @Test
    void findByUsername() {
        User user = new User();
        user.setId(1L);
        String username = "username";
        user.setUsername(username);
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        assertEquals(user, userService.findByUsername(username));
    }
    @Test
    void findByNotExistingUsername() {
        String username = "username";
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class,()->userService.findByUsername(username));

    }
    @Test
    void existsByUsername() {
        String username= "username";
        userService.existsByUsername(username);
        Mockito.verify(userRepository).existsByUsername(username);
    }

    @Test
    void existsByEmail() {
        String email= "email";
        userService.existsByEmail(email);
        Mockito.verify(userRepository).existsByEmail(email);
    }
}