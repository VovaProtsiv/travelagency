package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.model.ERole;
import dev.pprotsiv.travel.model.Role;
import dev.pprotsiv.travel.projection.RoleProjection;
import dev.pprotsiv.travel.repo.RoleRepository;
import dev.pprotsiv.travel.service.RoleService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(ERole name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Error: Role is not found."));
    }

    @Override
    public List<RoleProjection> findAll() {
        List<RoleProjection> all = roleRepository.findAllProjections();
        return all.isEmpty() ? new ArrayList<>() : all;
    }

    @Override
    public List<RoleProjection> findByUserId(long id) {
        List<RoleProjection> all = roleRepository.findByProjectionByUserID(id);
        return all.isEmpty() ? new ArrayList<>() : all;
    }
}
