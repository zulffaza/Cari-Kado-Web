package com.example.carikado.repository;

import com.example.carikado.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    public List<Province> findAllByCountryId(Integer countryId);

    @Query("select p from Province p order by p.name")
    public List<Province> findAllWithSort();
}
