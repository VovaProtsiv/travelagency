package dev.pprotsiv.travel.repo;

import dev.pprotsiv.travel.model.User;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    public static final String USER_NAME = "VovaTest";
    public static final String USER_EMAIL = "vovatest@gmail.com";

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        User vova = new User();
        vova.setEmail(USER_EMAIL);
        vova.setUsername(USER_NAME);
        vova.setPassword("password");
        this.userRepository.save(vova);
    }

    @Test
    void findByUsername() {
        Optional<User> user = userRepository.findByUsername(USER_NAME);
        assertEquals(user.get().getUsername(), USER_NAME);
    }

    @Test
    void findByNotExistingUsername() {
        assertTrue(userRepository.findByUsername("NotExisting").isEmpty());
    }

    @Test
    void existsByUsername() {
        assertTrue(userRepository.existsByUsername(USER_NAME));
    }

    @Test
    void existsByNotExistingUsername() {
        assertFalse(userRepository.existsByUsername("NotExisting"));
    }

    @Test
    void existsByEmail() {
        assertTrue(userRepository.existsByEmail(USER_EMAIL));
    }

    @Test
    void existsByNotExistingEmail() {
        assertFalse(userRepository.existsByEmail("NotExisting"));
    }
}