package com.example.carikado.RESTcontroller;

import com.example.carikado.model.MyResponse;
import com.example.carikado.model.User;
import com.example.carikado.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class UserRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRESTController.class);
    private static final String[] PROPERTIES = {
            "userName.firstName",
            "userAddress.country.name",
            "userAddress.province.name",
            "userAddress.city.name",
            "userAddress.district.name",
            "userAddress.subDistrict.name",
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private UserService mUserService;

    @Autowired
    public UserRESTController(UserService userService) {
        mUserService = userService;
    }

    @PostMapping("/api/user/all")
    public MyResponse<List> findUsers() {
        String message = "Find users success";
        ArrayList<User> users = (ArrayList<User>) mUserService.findAll();
        return new MyResponse<>(message, users);
    }

    @PostMapping("/api/user")
    public MyResponse<List> findUsers(@RequestBody Map<String, String> params) {
        Integer pageInt = 0;
        Integer pageSizeInt = 10;
        Integer sortInt = null;

        String page = params.get("page");
        String pageSize = params.get("pageSize");
        String sort = params.get("sort");

        ArrayList<String> properties = new ArrayList<>();
        List<User> users = null;
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

            if (sortInt != null && sortInt >= 1 && sortInt <= 12) {
                boolean isPrime = sortInt % 2 == 0;
                directionIndex = isPrime ? 1 : 0;

                propertiesIndex = (int) (Math.ceil((double) sortInt / 2) - 1);
            }

            direction = Sort.Direction.fromString(DIRECTION[directionIndex]);
            properties.add(PROPERTIES[propertiesIndex]);

            Sort sortOrder = new Sort(direction, properties);
            PageRequest pageRequest = new PageRequest(pageInt, pageSizeInt, sortOrder);
            users = mUserService.findAllPageable(pageRequest).getContent();

            message = "Find user success";
        } else
            message = "Error parsing parameter";

        LOGGER.info(pageInt + "");
        LOGGER.info(pageSizeInt + "");
        LOGGER.info(sortInt + "");

        return new MyResponse<>(message, users);
    }

    @PostMapping("/api/user/{userId}")
    public MyResponse<User> findUser(@PathVariable Integer userId) {
        User user = null;
        String message = "User not found";

        if (userId != null && userId >= 0) {
            user = mUserService.findUser(userId);

            if (user != null)
                message = "Find user success";
        }

        return new MyResponse<>(message, user);
    }

    @PostMapping("/api/user/add")
    public MyResponse<Integer> addUser(@RequestBody User user) {
        if (user.getCreatedAt() == null)
            user.setCreatedAt(new Date());

        user = mUserService.addUser(user);

        boolean isSuccess = user != null;
        String message = isSuccess ? "User add success" : "User add failed";
        int response = isSuccess ? 1 : 0;

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/user/{userId}")
    public MyResponse<Integer> deleteUser(@PathVariable Integer userId) {
        String message;
        Integer response;

        if (userId != null && userId >= 0) {
            User user;
            boolean isDeleted;

            try {
                mUserService.deleteUser(userId);
                user = mUserService.findUser(userId);

                isDeleted = user == null;
            } catch (EmptyResultDataAccessException e) {
                isDeleted = false;
                LOGGER.error(e.getMessage());
            }

            message = isDeleted ? "Delete user success" : "Delete user failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "User not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }

    @PostMapping("/api/user/verifyuser")
    public MyResponse<User> verifyUser(@RequestBody Map<String, String> params) {
        String userEmail = params.get("userEmail");
        String userPassword;
        String message;

        try {
            userPassword = User.passwordEncoder(params.get("userPassword"));
        } catch (NoSuchAlgorithmException e) {
            userPassword = "";
            LOGGER.error(e.getMessage());
        }

        User user = mUserService.findUserByEmail(userEmail);

        if (user != null) {
            if (!user.getPassword().equals(userPassword)) {
                message = "Wrong Password";
                user = null;
            } else
                message = "User verified";
        } else
            message = "User not found";

        return new MyResponse<>(message, user);
    }
}
