package com.example.carikado.repository;

import com.example.carikado.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {

    @Query("select ua from UserAddress ua order by ua.id")
    public List<UserAddress> findAllWithSort();
}
