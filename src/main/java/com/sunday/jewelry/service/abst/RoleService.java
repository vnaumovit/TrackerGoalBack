package com.sunday.jewelry.service.abst;

import com.sunday.jewelry.model.security.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> findAllRole();
    void addDefaultRole();
    Set<Role> findByIdRoles(List<Long>roles);
}
