package dev.pprotsiv.travel.service;

import dev.pprotsiv.travel.dto.UserDto;
import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.projection.UserProjection;

import java.util.List;

public interface UserService {
    User create(User user);

    User readById(long id);

    UserProjection getProjection(long id);

    User update(User user);

    void delete(long id);

    List<UserDto> getAllDtos();

    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}

