package com.example.carikado.RESTcontroller;

import com.example.carikado.model.MyResponse;
import com.example.carikado.model.Role;
import com.example.carikado.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleRESTController.class);
    private static final String[] PROPERTIES = {
            "name"
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private RoleService mRoleService;

    @Autowired
    public RoleRESTController(RoleService roleService) {
        mRoleService = roleService;
    }

    @GetMapping("/api/role/all")
    public MyResponse<List> findRoles() {
        String message = "Find roles success";
        ArrayList<Role> roles = (ArrayList<Role>) mRoleService.findAll();
        return new MyResponse<>(message, roles);
    }

    @GetMapping("/api/role")
    public MyResponse<List> findRoles(@RequestParam(required = false, defaultValue = "0") Integer page,
                                      @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                      @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<Role> roles = null;
        String message;
        Sort.Direction direction;

        if (page > 0)
            page -= 1;

        if (page < 0)
            page = 0;

        int propertiesIndex = 0;
        int directionIndex = 0;

        if (sort != null && sort >= 1 && sort <= 2)
            directionIndex = sort - 1;

        direction = Sort.Direction.fromString(DIRECTION[directionIndex]);
        properties.add(PROPERTIES[propertiesIndex]);

        Sort sortOrder = new Sort(direction, properties);
        PageRequest pageRequest = new PageRequest(page, pageSize, sortOrder);
        roles = mRoleService.findAllPageable(pageRequest).getContent();

        message = "Find gift info success";

        return new MyResponse<>(message, roles);
    }

    @GetMapping("/api/role/{roleId}")
    public MyResponse<Role> findRole(@PathVariable Integer roleId) {
        Role role = null;
        String message = "Role not found";

        if (roleId != null && roleId >= 0) {
            role = mRoleService.findRole(roleId);

            if (role != null)
                message = "Find role success";
        }

        return new MyResponse<>(message, role);
    }

    @PostMapping("/api/role/add")
    public MyResponse<Integer> addRole(@RequestBody Role role) {
        LOGGER.info(role.getId() + "");
        LOGGER.info(role.getName());

        role = mRoleService.addRole(role);

        boolean isSuccess = role != null;
        String message = isSuccess ? "Role add success" : "Role add failed";
        int response = isSuccess ? 1 : 0;

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/role/{roleId}")
    public MyResponse<Integer> deleteRole(@PathVariable Integer roleId) {
        String message;
        Integer response;

        if (roleId != null && roleId >= 0) {
            Role role;
            boolean isDeleted;

            try {
                mRoleService.deleteRole(roleId);
                role = mRoleService.findRole(roleId);

                isDeleted = role == null;
            } catch (EmptyResultDataAccessException e) {
                isDeleted = false;
                LOGGER.error(e.getMessage());
            }

            message = isDeleted ? "Delete role success" : "Delete role failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "Role not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }
}
