package com.example.carikado.repository;

import com.example.carikado.model.Gift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Integer> {

    @Query("select g from Gift g " +
            "where (g.gender = 'All' or g.gender = :gender) " +
            "and g.ageFrom <= :age " +
            "and g.ageTo >= :age " +
            "and g.price between :budgetFrom and :budgetTo")
    public List<Gift> findGiftSuggestion(@Param("gender") String gender,
                                         @Param("age") Integer age,
                                         @Param("budgetFrom") Integer budgetFrom,
                                         @Param("budgetTo") Integer budgetTo);

    @Query("select g from Gift g " +
            "where (g.gender = 'All' or g.gender = :gender) " +
            "and g.ageFrom <= :age " +
            "and g.ageTo >= :age " +
            "and g.price between :budgetFrom and :budgetTo")
    public Page<Gift> findGiftSuggestion(@Param("gender") String gender,
                                         @Param("age") Integer age,
                                         @Param("budgetFrom") Integer budgetFrom,
                                         @Param("budgetTo") Integer budgetTo,
                                         Pageable pageable);
}
