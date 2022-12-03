package com.tracker.goals.service.impl.security;

import com.tracker.goals.model.entity.security.Role;
import com.tracker.goals.repository.security.RoleRepository;
import com.tracker.goals.service.abst.security.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Set<Role> findByIdRoles(List<Integer> roles) {
        return new HashSet<>(roleRepository.findAllById(roles));
    }
}
