package com.example.carikado.repository;

import com.example.carikado.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    public List<City> findAllByProvinceId(Integer provinceId);

    @Query("select c from City c order by c.name")
    public List<City> findAllWithSort();
}
