package com.example.carikado.RESTcontroller;

import com.example.carikado.model.MyPage;
import com.example.carikado.model.MyResponse;
import com.example.carikado.model.Gift;
import com.example.carikado.service.GiftService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GiftRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiftRESTController.class);
    private static final String[] PROPERTIES = {
            "name",
            "ageFrom",
            "ageTo",
            "price",
            "rating"
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private GiftService mGiftService;

    @Autowired
    public GiftRESTController(GiftService giftService) {
        mGiftService = giftService;
    }

    @GetMapping("/api/gift/count/all")
    public MyResponse<Integer> countGifts() {
        String message = "Count gifts success";
        Integer count = mGiftService.count();
        return new MyResponse<>(message, count);
    }

    @GetMapping("/api/gift/all")
    public MyResponse<List> findGifts() {
        String message = "Find gifts success";
        ArrayList<Gift> gifts = (ArrayList<Gift>) mGiftService.findAll();
        return new MyResponse<>(message, gifts);
    }

    @GetMapping("/api/gift/suggestion/all")
    public MyResponse<List> findGiftSuggestions(@RequestParam String gender,
                                                @RequestParam Integer age,
                                                @RequestParam Integer budgetFrom,
                                                @RequestParam Integer budgetTo,
                                                @RequestParam String category,
                                                @RequestParam(required = false) String name) {
        String message = "Find gift suggestions success";
        ArrayList<Gift> gifts;

        if (name == null)
            gifts = (ArrayList<Gift>) mGiftService.findAllSuggestion(gender, age, budgetFrom, budgetTo, category);
        else
            gifts = (ArrayList<Gift>) mGiftService.findAllSuggestion(gender, age, budgetFrom, budgetTo, category, name);

        return new MyResponse<>(message, gifts);
    }

    @GetMapping("/api/gift")
    public MyResponse<MyPage<List>> findGifts(@RequestParam(required = false, defaultValue = "0") Integer page,
                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<Gift> gifts;
        String message;
        Sort.Direction direction;

        if (page > 0)
            page -= 1;

        if (page < 0)
            page = 0;

        int propertiesIndex = 0;
        int directionIndex = 0;

        if (sort != null && sort >= 1 && sort <= 10) {
            boolean isPrime = sort % 2 == 0;
            directionIndex = isPrime ? 1 : 0;

            propertiesIndex = (int) (Math.ceil((double) sort / 2) - 1);
        }

        direction = Sort.Direction.fromString(DIRECTION[directionIndex]);
        properties.add(PROPERTIES[propertiesIndex]);

        Sort sortOrder = new Sort(direction, properties);
        PageRequest pageRequest = new PageRequest(page, pageSize, sortOrder);
        Page<Gift> giftPage = mGiftService.findAllPageable(pageRequest);

        gifts = giftPage.getContent();
        message = "Find gift success";

        MyPage<List> myPage = new MyPage<>();

        myPage.setPage(++page);
        myPage.setLastPage(giftPage.getTotalPages() == 0 ? 1 : giftPage.getTotalPages());
        myPage.setPageSize(pageSize);
        myPage.setSort(sort == null ? 1 : sort);
        myPage.setTotalElement(giftPage.getTotalElements());
        myPage.setData(gifts);

        return new MyResponse<>(message, myPage);
    }

    @GetMapping("/api/gift/suggestion")
    public MyResponse<MyPage<List>> findGifts(@RequestParam(required = false, defaultValue = "0") Integer page,
                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false) Integer sort,
                                              @RequestParam String gender,
                                              @RequestParam Integer age,
                                              @RequestParam Integer budgetFrom,
                                              @RequestParam Integer budgetTo,
                                              @RequestParam String category,
                                              @RequestParam(required = false) String name) {
        ArrayList<String> properties = new ArrayList<>();
        List<Gift> gifts;
        String message;
        Sort.Direction direction;

        if (page > 0)
            page -= 1;

        if (page < 0)
            page = 0;

        int propertiesIndex = 0;
        int directionIndex = 0;

        if (sort != null && sort >= 1 && sort <= 2)
            directionIndex = sort - 1;

        direction = Sort.Direction.fromString(DIRECTION[directionIndex]);
        properties.add(PROPERTIES[propertiesIndex]);

        Sort sortOrder = new Sort(direction, properties);
        PageRequest pageRequest = new PageRequest(page, pageSize, sortOrder);
        Page<Gift> giftPage;

        if (name == null)
            giftPage = mGiftService.findAllSuggestionPageable(gender, age, budgetFrom, budgetTo, category, pageRequest);
        else
            giftPage = mGiftService.findAllSuggestionPageable(gender, age, budgetFrom, budgetTo, category, name, pageRequest);

        gifts = giftPage.getContent();
        message = "Find gift suggestion success";

        MyPage<List> myPage = new MyPage<>();

        myPage.setPage(++page);
        myPage.setLastPage(giftPage.getTotalPages() == 0 ? 1 : giftPage.getTotalPages());
        myPage.setPageSize(pageSize);
        myPage.setSort(sort == null ? 1 : sort);
        myPage.setTotalElement(giftPage.getTotalElements());
        myPage.setData(gifts);

        return new MyResponse<>(message, myPage);
    }

    @GetMapping("/api/gift/{giftId}")
    public MyResponse<Gift> findGift(@PathVariable Integer giftId) {
        Gift gift = null;
        String message = "Gift not found";

        if (giftId != null && giftId >= 0) {
            gift = mGiftService.findGift(giftId);

            if (gift != null)
                message = "Find gift success";
        }

        return new MyResponse<>(message, gift);
    }

    @PostMapping("/api/gift/add")
    public MyResponse<Integer> addGift(@RequestBody Gift gift) {
        String message = "Gift ";
        int response;

        try {
            message += gift.getId() != null ? "edit " : "add ";

            gift = mGiftService.addGift(gift);

            boolean isSuccess = gift != null;
            message += isSuccess ? "success" : "failed";
            response = isSuccess ? 1 : 0;
        } catch (DataIntegrityViolationException e) {
            message += "failed - Country already exists";
            response = 0;
        } catch (Exception e) {
            message += "failed - Internal Server Error";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/gift/{giftId}")
    public MyResponse<Integer> deleteGift(@PathVariable Integer giftId) {
        String message;
        Integer response;

        if (giftId != null && giftId >= 0) {
            try {
                mGiftService.deleteGift(giftId);
                response = 1;
            } catch (EmptyResultDataAccessException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            } catch (DataIntegrityViolationException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            }

            boolean isDeleted = response == 1;
            message = isDeleted ? "Delete gift success" : "Delete gift failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "Gift not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }
}
