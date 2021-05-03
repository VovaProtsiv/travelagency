package dev.pprotsiv.travel.security.jwt;

import dev.pprotsiv.travel.model.ERole;
import dev.pprotsiv.travel.model.Role;
import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.security.service.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class JwtUtilsTestIT {
    private Authentication authentication;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JwtUtils jwtUtils;


    @BeforeEach
    void setUserDetails() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Petro");
        user.setPassword("333333");
        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        user.setRoles(roles);
        UserDetails userDetails = UserDetailsImpl.build(user);
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()));
    }

    @Test
    void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldGenerateAuthToken() throws Exception {
        String token = jwtUtils.generateJwtToken(authentication);
        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get("/hotels/all").header("Authorization", "Bearer " + token)).andExpect(status().isOk());
    }

    @Test
    void getUserNameFromJwtToken() {
        assertEquals("Petro", jwtUtils.getUserNameFromJwtToken(jwtUtils.generateJwtToken(authentication)));
    }

    @Test
    void validateJwtToken() {
        assertTrue(jwtUtils.validateJwtToken(jwtUtils.generateJwtToken(authentication)));
    }
}