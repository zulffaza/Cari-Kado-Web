package com.example.carikado.service;

import com.example.carikado.model.GiftInfoAge;
import com.example.carikado.repository.GiftInfoAgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("giftInfoAgeService")
public class GiftInfoAgeService {

    private GiftInfoAgeRepository mGiftInfoAgeRepository;

    @Autowired
    public GiftInfoAgeService(GiftInfoAgeRepository giftInfoAgeRepository) { mGiftInfoAgeRepository = giftInfoAgeRepository; }

    public Integer count() {
        return (int) mGiftInfoAgeRepository.count();
    }

    public List<GiftInfoAge> findAll() {
        return mGiftInfoAgeRepository.findAllWithSort();
    }

    public Page<GiftInfoAge> findAllPageable(Pageable pageable) {
        return mGiftInfoAgeRepository.findAll(pageable);
    }

    public GiftInfoAge findGiftInfoAge(Integer id) { return mGiftInfoAgeRepository.findOne(id); }

    public GiftInfoAge addGiftInfoAge(GiftInfoAge giftInfoAge) {
        return mGiftInfoAgeRepository.save(giftInfoAge);
    }

    public void deleteGiftInfoAge(Integer id) { mGiftInfoAgeRepository.delete(id); }
}
