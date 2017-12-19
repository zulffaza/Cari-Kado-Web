package com.example.carikado.repository;

import com.example.carikado.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    @Query("select d from District d order by d.name")
    public List<District> findAllWithSort();
}
