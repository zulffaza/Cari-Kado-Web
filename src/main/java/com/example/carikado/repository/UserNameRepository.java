package com.example.carikado.repository;

import com.example.carikado.model.UserName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNameRepository extends JpaRepository<UserName, Integer> {

    @Query("select un from UserName un order by un.id")
    public List<UserName> findAllWithSort();
}
