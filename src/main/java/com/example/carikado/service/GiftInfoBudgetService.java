package com.example.carikado.service;

import com.example.carikado.model.GiftInfoBudget;
import com.example.carikado.repository.GiftInfoBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("giftInfoBudgetService")
public class GiftInfoBudgetService {

    private GiftInfoBudgetRepository mGiftInfoBudgetRepository;

    @Autowired
    public GiftInfoBudgetService(GiftInfoBudgetRepository giftInfoBudgetRepository) { mGiftInfoBudgetRepository = giftInfoBudgetRepository; }

    public Integer count() {
        return (int) mGiftInfoBudgetRepository.count();
    }

    public List<GiftInfoBudget> findAll() {
        return mGiftInfoBudgetRepository.findAllWithSort();
    }

    public Page<GiftInfoBudget> findAllPageable(Pageable pageable) {
        return mGiftInfoBudgetRepository.findAll(pageable);
    }

    public GiftInfoBudget findGiftInfoBudget(Integer id) { return mGiftInfoBudgetRepository.findOne(id); }

    public GiftInfoBudget addGiftInfoBudget(GiftInfoBudget giftInfoBudget) {
        return mGiftInfoBudgetRepository.save(giftInfoBudget);
    }

    public void deleteGiftInfoBudget(Integer id) { mGiftInfoBudgetRepository.delete(id); }
}
