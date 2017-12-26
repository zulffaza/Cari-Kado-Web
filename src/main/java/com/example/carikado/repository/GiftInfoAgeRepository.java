package com.example.carikado.repository;

import com.example.carikado.model.GiftInfoAge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftInfoAgeRepository extends JpaRepository<GiftInfoAge, Integer> {

    @Query("select gia from GiftInfoAge gia order by gia.id")
    public List<GiftInfoAge> findAllWithSort();

}
