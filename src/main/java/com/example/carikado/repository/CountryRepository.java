package com.example.carikado.repository;

import com.example.carikado.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("select c from Country c order by c.name")
    public List<Country> findAllWithSort();
}
