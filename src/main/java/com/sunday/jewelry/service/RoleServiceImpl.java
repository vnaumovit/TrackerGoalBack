package com.sunday.jewelry.service;

import com.sunday.jewelry.repository.RoleRepository;
import com.sunday.jewelry.model.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        addDefaultRole();
    }


    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public void addDefaultRole() {
        roleRepository.save(new Role(1L,"ROLE_USER"));
        roleRepository.save(new Role(2L,"ROLE_ADMIN"));
    }

    @Override
    public Set<Role> findByIdRoles(List<Long> roles) {
      return new HashSet<>(roleRepository.findAllById(roles));
    }
}
