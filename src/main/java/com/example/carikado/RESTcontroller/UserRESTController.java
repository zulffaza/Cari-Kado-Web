package com.example.carikado.RESTcontroller;

import com.example.carikado.model.*;
import com.example.carikado.service.UserAddressService;
import com.example.carikado.service.UserNameService;
import com.example.carikado.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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

    private UserAddressService mUserAddressService;
    private UserNameService mUserNameService;
    private UserService mUserService;

    @Autowired
    public UserRESTController(UserAddressService userAddressService, UserNameService userNameService,
                              UserService userService) {
        mUserAddressService = userAddressService;
        mUserNameService = userNameService;
        mUserService = userService;
    }

    @GetMapping("/api/user/all")
    public MyResponse<List> findUsers() {
        String message = "Find users success";
        ArrayList<User> users = (ArrayList<User>) mUserService.findAll();
        return new MyResponse<>(message, users);
    }

    @GetMapping("/api/user")
    public MyResponse<MyPage<List>> findUsers(@RequestParam(required = false, defaultValue = "0") Integer page,
                                             @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                             @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<User> users;
        String message;
        Sort.Direction direction;

        if (page > 0)
            page -= 1;

        if (page < 0)
            page = 0;

        int propertiesIndex = 0;
        int directionIndex = 0;

        if (sort != null && sort >= 1 && sort <= 12) {
            boolean isPrime = sort % 2 == 0;
            directionIndex = isPrime ? 1 : 0;

            propertiesIndex = (int) (Math.ceil((double) sort / 2) - 1);
        }

        direction = Sort.Direction.fromString(DIRECTION[directionIndex]);
        properties.add(PROPERTIES[propertiesIndex]);

        Sort sortOrder = new Sort(direction, properties);
        PageRequest pageRequest = new PageRequest(page, pageSize, sortOrder);
        Page<User> userPage = mUserService.findAllPageable(pageRequest);

        users = userPage.getContent();
        message = "Find user success";

        MyPage<List> myPage = new MyPage<>();

        myPage.setPage(++page);
        myPage.setLastPage(userPage.getTotalPages() == 0 ? 1 : userPage.getTotalPages());
        myPage.setPageSize(pageSize);
        myPage.setSort(sort == null ? 1 : sort);
        myPage.setTotalElement(userPage.getTotalElements());
        myPage.setData(users);

        return new MyResponse<>(message, myPage);
    }

    @GetMapping("/api/user/{userId}")
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

    @Transactional
    @PostMapping("/api/user/add")
    public MyResponse<Integer> addUser(@RequestBody User user) {
        if (user.getCreatedAt() == null)
            user.setCreatedAt(new Date());

        boolean isEdit = user.getId() != null;
        String message = "User " + (isEdit ? "edit " : "add ");

        try {
            if (!isEdit) {
                UserName userName = mUserNameService.addUserName(user.getUserName());
                UserAddress userAddress = mUserAddressService.addUserAddress(user.getUserAddress());

                user.setUserName(userName);
                user.setUserAddress(userAddress);
                user.setPassword(User.passwordEncoder(user.getPassword()));
            }

            user = mUserService.addUser(user);
        } catch (NoSuchAlgorithmException e) {
            user = null;
            LOGGER.error(e.getMessage());
        }

        boolean isSuccess = user != null;
        message += isSuccess ? "success" : "failed";
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
