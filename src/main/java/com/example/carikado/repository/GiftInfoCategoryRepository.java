package com.example.carikado.repository;

import com.example.carikado.model.GiftInfoCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftInfoCategoryRepository extends JpaRepository<GiftInfoCategory, Integer> {

    @Query("select gic from GiftInfoCategory gic order by gic.name")
    public List<GiftInfoCategory> findAllWithSort();
}
