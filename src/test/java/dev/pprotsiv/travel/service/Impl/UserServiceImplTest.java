package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.repo.UserRepository;
import dev.pprotsiv.travel.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    void getAll() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        Mockito.when(userRepository.findAll()).thenReturn(users);
        assertEquals(users,userService.getAll());
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