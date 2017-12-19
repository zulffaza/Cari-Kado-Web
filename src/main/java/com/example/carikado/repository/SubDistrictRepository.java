package com.example.carikado.repository;

import com.example.carikado.model.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubDistrictRepository extends JpaRepository<SubDistrict, Long> {

    @Query("select sd from SubDistrict sd order by sd.name")
    public List<SubDistrict> findAllWithSort();
}
