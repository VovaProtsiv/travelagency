package dev.pprotsiv.travel.service;

import dev.pprotsiv.travel.model.ERole;
import dev.pprotsiv.travel.model.Role;

public interface RoleService {
    Role findByName(ERole name);
}
