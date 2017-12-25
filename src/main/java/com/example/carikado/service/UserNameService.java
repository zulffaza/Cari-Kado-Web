package com.example.carikado.service;

import com.example.carikado.model.UserName;
import com.example.carikado.repository.UserNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userNameService")
public class UserNameService {

    private UserNameRepository mUserNameRepository;

    @Autowired
    public UserNameService(UserNameRepository userNameRepository) { this.mUserNameRepository = userNameRepository; }

    public Integer count() {
        return (int) mUserNameRepository.count();
    }

    public List<UserName> findAll() {
        return mUserNameRepository.findAllWithSort();
    }

    public Page<UserName> findAllPageable(Pageable pageable) {
        return mUserNameRepository.findAll(pageable);
    }

    public UserName findUserName(Integer id) { return mUserNameRepository.findOne(id); }

    public UserName addUserName(UserName userName) {
        return mUserNameRepository.save(userName);
    }

    public void deleteUserName(Integer id) { mUserNameRepository.delete(id); }
}
