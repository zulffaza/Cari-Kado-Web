package com.example.carikado.service;

import com.example.carikado.model.GiftInfoCategory;
import com.example.carikado.repository.GiftInfoCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("giftInfoCategoryService")
public class GiftInfoCategoryService {

    private GiftInfoCategoryRepository mGiftInfoCategoryRepository;

    @Autowired
    public GiftInfoCategoryService(GiftInfoCategoryRepository giftInfoCategoryRepository) {
        mGiftInfoCategoryRepository = giftInfoCategoryRepository;
    }

    public Integer count() {
        return (int) mGiftInfoCategoryRepository.count();
    }

    public List<GiftInfoCategory> findAll() {
        return mGiftInfoCategoryRepository.findAllWithSort();
    }

    public Page<GiftInfoCategory> findAllPageable(Pageable pageable) {
        return mGiftInfoCategoryRepository.findAll(pageable);
    }

    public GiftInfoCategory findGiftInfoCategory(Integer id) {
        return mGiftInfoCategoryRepository.findOne(id);
    }

    public GiftInfoCategory addGiftInfoCategory(GiftInfoCategory giftInfo) {
        return mGiftInfoCategoryRepository.save(giftInfo);
    }

    public void deleteGiftInfoCategory(Integer id) {
        mGiftInfoCategoryRepository.delete(id);
    }
}
