package com.example.carikado.repository;

import com.example.carikado.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select count(u) from User u where u.role.id = :roleId")
    public Integer countUserByRoleId(@Param("roleId") Integer roleId);
}
