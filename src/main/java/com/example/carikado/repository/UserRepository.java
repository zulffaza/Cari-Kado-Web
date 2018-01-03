package com.example.carikado.repository;

import com.example.carikado.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u order by u.email")
    public List<User> findAllWithSort();

    public User findByEmail(String email);
}
