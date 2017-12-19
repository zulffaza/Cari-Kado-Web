package com.example.carikado.repository;

import com.example.carikado.model.GiftInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftInfoRepository extends JpaRepository<GiftInfo, Integer> {

    @Query("select gi from GiftInfo gi order by gi.createdAt")
    public List<GiftInfo> findAllWithSort();
}
