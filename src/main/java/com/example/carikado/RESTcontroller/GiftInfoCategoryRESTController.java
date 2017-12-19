package com.example.carikado.RESTcontroller;

import com.example.carikado.model.GiftInfoCategory;
import com.example.carikado.model.MyPage;
import com.example.carikado.model.MyResponse;
import com.example.carikado.service.GiftInfoCategoryService;
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
public class GiftInfoCategoryRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiftInfoCategoryRESTController.class);
    private static final String[] PROPERTIES = {
            "name"
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private GiftInfoCategoryService mGiftInfoCategoryService;

    @Autowired
    public GiftInfoCategoryRESTController(GiftInfoCategoryService giftInfoCategoryService) {
        mGiftInfoCategoryService = giftInfoCategoryService;
    }

    @GetMapping("/api/gift-info-category/count/all")
    public MyResponse<Integer> countGiftInfoCategories() {
        String message = "Count gift info categories success";
        Integer count = mGiftInfoCategoryService.count();
        return new MyResponse<>(message, count);
    }

    @GetMapping("/api/gift-info-category/all")
    public MyResponse<List> findGiftInfoCategories() {
        String message = "Find gift info categories success";
        ArrayList<GiftInfoCategory> giftInfoCategories = (ArrayList<GiftInfoCategory>) mGiftInfoCategoryService.findAll();
        return new MyResponse<>(message, giftInfoCategories);
    }

    @GetMapping("/api/gift-info-category")
    public MyResponse<MyPage<List>> findGiftInfoCategories(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<GiftInfoCategory> giftInfoCategories;
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
        Page<GiftInfoCategory> giftInfoCategoryPage = mGiftInfoCategoryService.findAllPageable(pageRequest);

        giftInfoCategories = giftInfoCategoryPage.getContent();
        message = "Find gift info categories success";

        MyPage<List> myPage = new MyPage<>();

        myPage.setPage(++page);
        myPage.setLastPage(giftInfoCategoryPage.getTotalPages());
        myPage.setPageSize(pageSize);
        myPage.setSort(sort == null ? 1 : sort);
        myPage.setTotalElement(giftInfoCategoryPage.getTotalElements());
        myPage.setData(giftInfoCategories);

        return new MyResponse<>(message, myPage);
    }

    @GetMapping("/api/gift-info-category/{giftInfoCategoryId}")
    public MyResponse<GiftInfoCategory> findGiftInfoCategory(@PathVariable Integer giftInfoCategoryId) {
        GiftInfoCategory giftInfoCategory = null;
        String message = "Gift info category not found";

        if (giftInfoCategoryId != null && giftInfoCategoryId >= 0) {
            giftInfoCategory = mGiftInfoCategoryService.findGiftInfoCategory(giftInfoCategoryId);

            if (giftInfoCategory != null)
                message = "Find gift info category success";
        }

        return new MyResponse<>(message, giftInfoCategory);
    }

    @PostMapping("/api/gift-info-category/add")
    public MyResponse<Integer> addGiftInfoCategory(@RequestBody GiftInfoCategory giftInfoCategory) {
        String message;
        int response;

        try {
            giftInfoCategory = mGiftInfoCategoryService.addGiftInfoCategory(giftInfoCategory);

            boolean isSuccess = giftInfoCategory != null;
            message = isSuccess ? "Gift info category add success" : "Gift info category add failed";
            response = isSuccess ? 1 : 0;
        } catch (DataIntegrityViolationException e) {
            message = "Gift info category add failed - Gift info category already exists";
            response = 0;
        } catch (Exception e) {
            message = "Gift info category add failed - Internal Server Error";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/gift-info-category/{giftInfoCategoryId}")
    public MyResponse<Integer> deleteGiftInfoCategory(@PathVariable Integer giftInfoCategoryId) {
        String message;
        Integer response;

        if (giftInfoCategoryId != null && giftInfoCategoryId >= 0) {
            try {
                mGiftInfoCategoryService.deleteGiftInfoCategory(giftInfoCategoryId);
                response = 1;
            } catch (EmptyResultDataAccessException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            } catch (DataIntegrityViolationException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            }

            boolean isDeleted = response == 1;
            message = isDeleted ? "Delete gift info category success" : "Delete gift info category failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "Gift info category not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }
}
