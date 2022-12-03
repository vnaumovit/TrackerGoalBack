package com.tracker.goals.service.abst.security;

import com.tracker.goals.model.entity.security.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> findAllRole();

    Set<Role> findByIdRoles(List<Integer> roles);
}
