package com.example.carikado.service;

import com.example.carikado.model.GiftInfoPicture;
import com.example.carikado.repository.GiftInfoPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("giftInfoPictureService")
public class GiftInfoPictureService {

    private GiftInfoPictureRepository mGiftInfoPictureRepository;

    @Autowired
    public GiftInfoPictureService(GiftInfoPictureRepository giftInfoPictureRepository) {
        mGiftInfoPictureRepository = giftInfoPictureRepository;
    }

    public Integer count() {
        return (int) mGiftInfoPictureRepository.count();
    }

    public List<GiftInfoPicture> findAll() {
        return mGiftInfoPictureRepository.findAllWithSort();
    }

    public Page<GiftInfoPicture> findAllPageable(Pageable pageable) {
        return mGiftInfoPictureRepository.findAll(pageable);
    }

    public GiftInfoPicture findGiftInfoPicture(Integer id) {
        return mGiftInfoPictureRepository.findOne(id);
    }

    public GiftInfoPicture addGiftInfoPicture(GiftInfoPicture giftInfo) {
        return mGiftInfoPictureRepository.save(giftInfo);
    }

    public void deleteGiftInfoPicture(Integer id) {
        mGiftInfoPictureRepository.delete(id);
    }
}
