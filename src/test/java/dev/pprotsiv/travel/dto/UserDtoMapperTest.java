package dev.pprotsiv.travel.dto;

import dev.pprotsiv.travel.model.Role;
import dev.pprotsiv.travel.model.User;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDtoMapperTest {


    @Test
    void convertToDto() {
        User user = new User();
        long id = 1L;
        user.setId(id);
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
        UserDto dto = UserDtoMapper.convertToDto(user);
        assertEquals(id, dto.getId());
        assertEquals(pass, dto.getPassword());
        assertEquals(username, dto.getUsername());
        assertEquals(email, dto.getEmail());
        assertEquals(roles, dto.getRoles());
    }

    @Test
    void testConvertToDto() {
        User user1 = new User();
        long id = 1L;
        user1.setId(id);
        String pass = "password1";
        String username = "username1";
        String email = "email@email.com1";
        user1.setPassword(pass);
        user1.setUsername(username);
        user1.setEmail(email);
        Role role = new Role();
        role.setId(1);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user1.setRoles(roles);

        User user2 = new User();
        long id2 = 2L;
        user2.setId(id2);
        String pass2 = "password2";
        String username2 = "username2";
        String email2 = "email@email.com2";
        user2.setPassword(pass2);
        user2.setUsername(username2);
        user2.setEmail(email2);
        Role role2 = new Role();
        role2.setId(2);
        Set<Role> roles2 = new HashSet<>();
        roles.add(role2);
        user1.setRoles(roles2);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        List<UserDto> userDtos = UserDtoMapper.convertToDto(users);
        Optional<UserDto> dto1 = userDtos.stream().filter(e -> e.getId().equals(user1.getId())).findFirst();
        Optional<UserDto> dto2 = userDtos.stream().filter(e -> e.getId().equals(user2.getId())).findFirst();
        assertTrue(dto1.isPresent());
        assertTrue(dto2.isPresent());
        assertEquals(user1.getId(), dto1.get().getId());
        assertEquals(user1.getUsername(), dto1.get().getUsername());
        assertEquals(user1.getEmail(), dto1.get().getEmail());
        assertEquals(user1.getPassword(), dto1.get().getPassword());
        assertEquals(user1.getRoles(), dto1.get().getRoles());

        assertEquals(user2.getId(), dto2.get().getId());
        assertEquals(user2.getUsername(), dto2.get().getUsername());
        assertEquals(user2.getEmail(), dto2.get().getEmail());
        assertEquals(user2.getPassword(), dto2.get().getPassword());
        assertEquals(user2.getRoles(), dto2.get().getRoles());
    }
}