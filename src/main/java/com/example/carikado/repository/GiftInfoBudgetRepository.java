package com.example.carikado.repository;

import com.example.carikado.model.GiftInfoBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftInfoBudgetRepository extends JpaRepository<GiftInfoBudget, Integer> {

    @Query("select gib from GiftInfoBudget gib order by gib.id")
    public List<GiftInfoBudget> findAllWithSort();

}
