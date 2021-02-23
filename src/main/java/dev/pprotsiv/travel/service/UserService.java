package dev.pprotsiv.travel.service;

import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.projection.UserProjection;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);
    User readById(long id);
    UserProjection getProjection(long id);
    User update(User user);
    void delete(long id);
    List<UserProjection> getAllProjections();

   User findByUsername(String username);
}
