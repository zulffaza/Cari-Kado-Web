package com.example.carikado.repository;

import com.example.carikado.model.GiftInfo;
import com.example.carikado.model.GiftInfoPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftInfoPictureRepository extends JpaRepository<GiftInfoPicture, Integer> {

    @Query("select gip from GiftInfoPicture gip order by gip.name")
    public List<GiftInfoPicture> findAllWithSort();
}
