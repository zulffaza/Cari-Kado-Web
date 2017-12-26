package com.example.carikado.service;

import com.example.carikado.model.UserAddress;
import com.example.carikado.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userAddressService")
public class UserAddressService {

    private UserAddressRepository mUserAddressRepository;

    @Autowired
    public UserAddressService(UserAddressRepository userAddressRepository) { this.mUserAddressRepository = userAddressRepository; }

    public Integer count() {
        return (int) mUserAddressRepository.count();
    }

    public List<UserAddress> findAll() {
        return mUserAddressRepository.findAllWithSort();
    }

    public Page<UserAddress> findAllPageable(Pageable pageable) {
        return mUserAddressRepository.findAll(pageable);
    }

    public UserAddress findUserAddress(Integer id) { return mUserAddressRepository.findOne(id); }

    public UserAddress addUserAddress(UserAddress userAddress) {
        return mUserAddressRepository.save(userAddress);
    }

    public void deleteUserAddress(Integer id) { mUserAddressRepository.delete(id); }
}
