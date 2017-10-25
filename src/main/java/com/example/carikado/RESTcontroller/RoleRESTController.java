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
import java.util.Map;

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

    @PostMapping("/api/role/all")
    public MyResponse<List> findRoles() {
        String message = "Find roles success";
        ArrayList<Role> roles = (ArrayList<Role>) mRoleService.findAll();
        return new MyResponse<>(message, roles);
    }

    @PostMapping("/api/role")
    public MyResponse<List> findRoles(@RequestBody Map<String, String> params) {
        Integer pageInt = 0;
        Integer pageSizeInt = 10;
        Integer sortInt = null;

        String page = params.get("page");
        String pageSize = params.get("pageSize");
        String sort = params.get("sort");

        ArrayList<String> properties = new ArrayList<>();
        List<Role> roles = null;
        String message;
        Sort.Direction direction;

        boolean isValid = true;

        try {
            if (page != null)
                pageInt = Integer.parseInt(page);

            if (pageSize != null)
                pageSizeInt = Integer.parseInt(pageSize);

            if (sort != null)
                sortInt = Integer.parseInt(sort);

            if (pageInt > 0)
                pageInt -= 1;

            if (pageInt < 0)
                pageInt = 0;
        } catch (Exception e) {
            isValid = false;
            LOGGER.error(e.getMessage());
        }

        if (isValid) {
            int propertiesIndex = 0;
            int directionIndex = 0;

            if (sortInt != null && sortInt >= 1 && sortInt <= 2)
                directionIndex = sortInt - 1;

            direction = Sort.Direction.fromString(DIRECTION[directionIndex]);
            properties.add(PROPERTIES[propertiesIndex]);

            Sort sortOrder = new Sort(direction, properties);
            PageRequest pageRequest = new PageRequest(pageInt, pageSizeInt, sortOrder);
            roles = mRoleService.findAllPageable(pageRequest).getContent();

            message = "Find gift info success";
        } else
            message = "Error parsing parameter";

        LOGGER.info(pageInt + "");
        LOGGER.info(pageSizeInt + "");
        LOGGER.info(sortInt + "");

        return new MyResponse<>(message, roles);
    }

    @PostMapping("/api/role/{roleId}")
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
