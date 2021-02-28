package dev.pprotsiv.travel.service;

import dev.pprotsiv.travel.model.ERole;
import dev.pprotsiv.travel.model.Role;
import dev.pprotsiv.travel.projection.RoleProjection;

import java.util.List;

public interface RoleService {
    Role findByName(ERole name);

    List<RoleProjection> findAll();

    List<RoleProjection> findByUserId(long id);
}
