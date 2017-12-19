package com.example.carikado.RESTcontroller;

import com.example.carikado.model.MyResponse;
import com.example.carikado.model.MyPage;
import com.example.carikado.model.Role;
import com.example.carikado.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
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

    @GetMapping("/api/role/count/all")
    public MyResponse<Integer> countRoles() {
        String message = "Count roles success";
        Integer count = mRoleService.count();
        return new MyResponse<>(message, count);
    }

    @GetMapping("/api/role/all")
    public MyResponse<List> findRoles() {
        String message = "Find roles success";
        ArrayList<Role> roles = (ArrayList<Role>) mRoleService.findAll();
        return new MyResponse<>(message, roles);
    }

    @GetMapping("/api/role")
    public MyResponse<MyPage<List>> findRoles(@RequestParam(required = false, defaultValue = "0") Integer page,
                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<Role> roles;
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
        Page<Role> rolePage = mRoleService.findAllPageable(pageRequest);

        roles = rolePage.getContent();
        message = "Find role success";

        MyPage<List> myPage = new MyPage<>();

        myPage.setPage(++page);
        myPage.setLastPage(rolePage.getTotalPages());
        myPage.setPageSize(pageSize);
        myPage.setSort(sort == null ? 1 : sort);
        myPage.setTotalElement(rolePage.getTotalElements());
        myPage.setData(roles);

        return new MyResponse<>(message, myPage);
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
        String message;
        int response;

        if (role.getId() != null) {
            long usersCount = mRoleService.countUsers(role.getId());

            if (usersCount == 0) {
                role = mRoleService.addRole(role);

                boolean isSuccess = role != null;
                message = isSuccess ? "Role edit success" : "Role edit failed";
                response = isSuccess ? 1 : 0;
            } else {
                message = "Role edit failed - Role in use";
                response = 0;
            }
        } else {
            try {
                role = mRoleService.addRole(role);

                boolean isSuccess = role != null;
                message = isSuccess ? "Role add success" : "Role add failed";
                response = isSuccess ? 1 : 0;
            } catch (DataIntegrityViolationException e) {
                message = "Role add failed - Role already exists";
                response = 0;
            } catch (Exception e) {
                message = "Role add failed - Internal Server Error";
                response = 0;
            }
        }

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/role/{roleId}")
    public MyResponse<Integer> deleteRole(@PathVariable Integer roleId) {
        String message;
        Integer response;

        if (roleId != null && roleId >= 0) {
            try {
                mRoleService.deleteRole(roleId);
                response = 1;
            } catch (EmptyResultDataAccessException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            } catch (DataIntegrityViolationException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            }

            boolean isDeleted = response == 1;
            message = isDeleted ? "Delete role success" : "Delete role failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "Role not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }
}
