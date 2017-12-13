package com.example.carikado.repository;

import com.example.carikado.model.GiftInfo;
import com.example.carikado.model.GiftInfoCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftInfoRepository extends JpaRepository<GiftInfo, Long> {

}
