package com.example.carikado.service;

import com.example.carikado.model.Role;
import com.example.carikado.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleService {

    private RoleRepository mRoleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) { mRoleRepository = roleRepository; }

    public Integer count() {
        return (int) mRoleRepository.count();
    }

    public Integer countUsers(Integer roleId) {
        return mRoleRepository.countUserByRoleId(roleId);
    }

    public List<Role> findAll() {
        return mRoleRepository.findAll();
    }

    public Page<Role> findAllPageable(Pageable pageable) {
        return mRoleRepository.findAll(pageable);
    }

    public Role findRole(Integer id) { return mRoleRepository.findOne(id); }

    public Role addRole(Role role) {
        return mRoleRepository.save(role);
    }

    public void deleteRole(Integer id) { mRoleRepository.delete(id); }
}
