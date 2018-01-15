package com.example.carikado.service;

import com.example.carikado.model.Gift;
import com.example.carikado.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("giftService")
public class GiftService {

    private GiftRepository mGiftRepository;

    @Autowired
    public GiftService(GiftRepository giftRepository) {
        mGiftRepository = giftRepository;
    }

    public Integer count() {
        return (int) mGiftRepository.count();
    }

    public List<Gift> findAll() {
        return mGiftRepository.findAll();
    }

    public Page<Gift> findAllPageable(Pageable pageable) {
        return mGiftRepository.findAll(pageable);
    }

    public List<Gift> findAllSuggestion(String gender,
                                        Integer age,
                                        Integer budgetFrom,
                                        Integer budgetTo,
                                        String category) {
        return mGiftRepository.findGiftSuggestion(gender, age, budgetFrom, budgetTo, category);
    }

    public List<Gift> findAllSuggestion(String gender,
                                        Integer age,
                                        Integer budgetFrom,
                                        Integer budgetTo,
                                        String category,
                                        String name) {
        return mGiftRepository.findGiftSuggestion(gender, age, budgetFrom, budgetTo, category, name);
    }

    public Page<Gift> findAllSuggestionPageable(String gender,
                                                Integer age,
                                                Integer budgetFrom,
                                                Integer budgetTo,
                                                String category,
                                                Pageable pageable) {
        return mGiftRepository.findGiftSuggestion(gender, age, budgetFrom, budgetTo, category, pageable);
    }

    public Page<Gift> findAllSuggestionPageable(String gender,
                                                Integer age,
                                                Integer budgetFrom,
                                                Integer budgetTo,
                                                String category,
                                                String name,
                                                Pageable pageable) {
        return mGiftRepository.findGiftSuggestion(gender, age, budgetFrom, budgetTo, category, name, pageable);
    }

    public Gift findGift(Integer id) {
        return mGiftRepository.findOne(id);
    }

    public Gift addGift(Gift gift) {
        return mGiftRepository.save(gift);
    }

    public void deleteGift(Integer id) {
        mGiftRepository.delete(id);
    }
}
