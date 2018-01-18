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
            "inner join g.giftInfoCategories gic " +
            "where g.ageFrom <= :age " +
            "and g.ageTo >= :age " +
            "and g.price between :budgetFrom and :budgetTo " +
            "and gic.name = :category")
    public List<Gift> findGiftSuggestion(@Param("age") Integer age,
                                         @Param("budgetFrom") Integer budgetFrom,
                                         @Param("budgetTo") Integer budgetTo,
                                         @Param("category") String category);

    @Query("select g from Gift g " +
            "inner join g.giftInfoCategories gic " +
            "where (g.gender = 'All' or g.gender = :gender) " +
            "and g.ageFrom <= :age " +
            "and g.ageTo >= :age " +
            "and g.price between :budgetFrom and :budgetTo " +
            "and gic.name = :category")
    public List<Gift> findGiftSuggestion(@Param("gender") String gender,
                                         @Param("age") Integer age,
                                         @Param("budgetFrom") Integer budgetFrom,
                                         @Param("budgetTo") Integer budgetTo,
                                         @Param("category") String category);

    @Query("select g from Gift g " +
            "inner join g.giftInfoCategories gic " +
            "where g.name like %:name% " +
            "and g.ageFrom <= :age " +
            "and g.ageTo >= :age " +
            "and g.price between :budgetFrom and :budgetTo " +
            "and gic.name = :category")
    public List<Gift> findGiftSuggestion(@Param("age") Integer age,
                                         @Param("budgetFrom") Integer budgetFrom,
                                         @Param("budgetTo") Integer budgetTo,
                                         @Param("category") String category,
                                         @Param("name") String name);

    @Query("select g from Gift g " +
            "inner join g.giftInfoCategories gic " +
            "where g.name like %:name% " +
            "and (g.gender = 'All' or g.gender = :gender) " +
            "and g.ageFrom <= :age " +
            "and g.ageTo >= :age " +
            "and g.price between :budgetFrom and :budgetTo " +
            "and gic.name = :category")
    public List<Gift> findGiftSuggestion(@Param("gender") String gender,
                                         @Param("age") Integer age,
                                         @Param("budgetFrom") Integer budgetFrom,
                                         @Param("budgetTo") Integer budgetTo,
                                         @Param("category") String category,
                                         @Param("name") String name);

    @Query("select g from Gift g " +
            "inner join g.giftInfoCategories gic " +
            "where g.ageFrom <= :age " +
            "and g.ageTo >= :age " +
            "and g.price between :budgetFrom and :budgetTo " +
            "and gic.name = :category")
    public Page<Gift> findGiftSuggestion(@Param("age") Integer age,
                                         @Param("budgetFrom") Integer budgetFrom,
                                         @Param("budgetTo") Integer budgetTo,
                                         @Param("category") String category,
                                         Pageable pageable);

    @Query("select g from Gift g " +
            "inner join g.giftInfoCategories gic " +
            "where (g.gender = 'All' or g.gender = :gender) " +
            "and g.ageFrom <= :age " +
            "and g.ageTo >= :age " +
            "and g.price between :budgetFrom and :budgetTo " +
            "and gic.name = :category")
    public Page<Gift> findGiftSuggestion(@Param("gender") String gender,
                                         @Param("age") Integer age,
                                         @Param("budgetFrom") Integer budgetFrom,
                                         @Param("budgetTo") Integer budgetTo,
                                         @Param("category") String category,
                                         Pageable pageable);

    @Query("select g from Gift g " +
            "inner join g.giftInfoCategories gic " +
            "where g.name like %:name% " +
            "and g.ageFrom <= :age " +
            "and g.ageTo >= :age " +
            "and g.price between :budgetFrom and :budgetTo " +
            "and gic.name = :category")
    public Page<Gift> findGiftSuggestion(@Param("age") Integer age,
                                         @Param("budgetFrom") Integer budgetFrom,
                                         @Param("budgetTo") Integer budgetTo,
                                         @Param("category") String category,
                                         @Param("name") String name,
                                         Pageable pageable);

    @Query("select g from Gift g " +
            "inner join g.giftInfoCategories gic " +
            "where g.name like %:name% " +
            "and (g.gender = 'All' or g.gender = :gender) " +
            "and g.ageFrom <= :age " +
            "and g.ageTo >= :age " +
            "and g.price between :budgetFrom and :budgetTo " +
            "and gic.name = :category")
    public Page<Gift> findGiftSuggestion(@Param("gender") String gender,
                                         @Param("age") Integer age,
                                         @Param("budgetFrom") Integer budgetFrom,
                                         @Param("budgetTo") Integer budgetTo,
                                         @Param("category") String category,
                                         @Param("name") String name,
                                         Pageable pageable);
}
