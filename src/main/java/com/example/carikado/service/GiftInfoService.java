package com.example.carikado.service;

import com.example.carikado.model.GiftInfo;
import com.example.carikado.repository.GiftInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("giftInfoService")
public class GiftInfoService {

    private GiftInfoRepository mGiftInfoRepository;

    @Autowired
    public GiftInfoService(GiftInfoRepository giftInfoRepository) {
        mGiftInfoRepository = giftInfoRepository;
    }

    public Integer count() {
        return (int) mGiftInfoRepository.count();
    }

    public List<GiftInfo> findAll() {
        return mGiftInfoRepository.findAll();
    }

    public Page<GiftInfo> findAllPageable(Pageable pageable) {
        return mGiftInfoRepository.findAll(pageable);
    }

    public GiftInfo findGift(Long id) {
        return mGiftInfoRepository.findOne(id);
    }

    public GiftInfo addGiftInfo(GiftInfo giftInfo) {
        return mGiftInfoRepository.save(giftInfo);
    }

    public void deleteGiftInfo(Long id) {
        mGiftInfoRepository.delete(id);
    }
}
