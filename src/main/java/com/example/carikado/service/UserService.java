package com.example.carikado.service;

import com.example.carikado.model.User;
import com.example.carikado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserService {

    private UserRepository mUserRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    public Integer count() {
        return (int) mUserRepository.count();
    }

    public List<User> findAll() {
        return mUserRepository.findAllWithSort();
    }

    public Page<User> findAllPageable(Pageable pageable) {
        return mUserRepository.findAll(pageable);
    }

    public User findUser(Integer id) {
        return mUserRepository.findOne(id);
    }

    public User findUserByEmail(String email) {
        return mUserRepository.findByEmail(email);
    }

    public User addUser(User user) {
        return mUserRepository.save(user);
    }

    public void deleteUser(Integer id) {
        mUserRepository.delete(id);
    }
}
