package com.example.carikado.RESTcontroller;

import com.example.carikado.model.GiftInfo;
import com.example.carikado.model.MyPage;
import com.example.carikado.model.MyResponse;
import com.example.carikado.service.GiftInfoService;
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
import java.util.Date;
import java.util.List;

@RestController
public class GiftInfoRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiftInfoRESTController.class);
    private static final String[] PROPERTIES = {
            "createdAt",
            "title",
            "giftInfoAge.from",
            "giftInfoBudget.from",
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private GiftInfoService mGiftInfoService;

    @Autowired
    public GiftInfoRESTController(GiftInfoService giftInfoService) {
        mGiftInfoService = giftInfoService;
    }

    @GetMapping("/api/gift-info/count/all")
    public MyResponse<Integer> countGiftInfos() {
        String message = "Count gift infos success";
        Integer count = mGiftInfoService.count();
        return new MyResponse<>(message, count);
    }

    @GetMapping("/api/gift-info/all")
    public MyResponse<List> findGiftInfos() {
        String message = "Find gift infos success";
        ArrayList<GiftInfo> giftInfos = (ArrayList<GiftInfo>) mGiftInfoService.findAll();
        return new MyResponse<>(message, giftInfos);
    }

    @GetMapping("/api/gift-info")
    public MyResponse<MyPage<List>> findGiftInfos(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                           @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<GiftInfo> giftInfos;
        String message;
        Sort.Direction direction;

        if (page > 0)
            page -= 1;

        if (page < 0)
            page = 0;

        int propertiesIndex = 0;
        int directionIndex = 0;

        if (sort != null && sort >= 1 && sort <= 8) {
            boolean isPrime = sort % 2 == 0;
            directionIndex = isPrime ? 1 : 0;

            propertiesIndex = (int) (Math.ceil((double) sort / 2) - 1);
        }

        direction = Sort.Direction.fromString(DIRECTION[directionIndex]);
        properties.add(PROPERTIES[propertiesIndex]);

        Sort sortOrder = new Sort(direction, properties);
        PageRequest pageRequest = new PageRequest(page, pageSize, sortOrder);
        Page<GiftInfo> giftInfoPage = mGiftInfoService.findAllPageable(pageRequest);

        giftInfos = giftInfoPage.getContent();
        message = "Find gift infos success";

        MyPage<List> myPage = new MyPage<>();

        myPage.setPage(++page);
        myPage.setLastPage(giftInfoPage.getTotalPages());
        myPage.setPageSize(pageSize);
        myPage.setSort(sort == null ? 1 : sort);
        myPage.setTotalElement(giftInfoPage.getTotalElements());
        myPage.setData(giftInfos);

        return new MyResponse<>(message, myPage);
    }

    @GetMapping("/api/gift-info/{giftInfoId}")
    public MyResponse<GiftInfo> findGiftInfo(@PathVariable Integer giftInfoId) {
        GiftInfo giftInfo = null;
        String message = "Gift info not found";

        if (giftInfoId != null && giftInfoId >= 0) {
            giftInfo = mGiftInfoService.findGiftInfo(giftInfoId);

            if (giftInfo != null)
                message = "Find gift info success";
        }

        return new MyResponse<>(message, giftInfo);
    }

    @PostMapping("/api/gift-info/add")
    public MyResponse<Integer> addGiftInfo(@RequestBody GiftInfo giftInfo) {
        String message;
        int response;

        try {
            giftInfo = mGiftInfoService.addGiftInfo(giftInfo);

            boolean isSuccess = giftInfo != null;
            message = isSuccess ? "Gift info add success" : "Gift info add failed";
            response = isSuccess ? 1 : 0;
        } catch (DataIntegrityViolationException e) {
            message = "Gift info add failed - Gift info already exists";
            response = 0;
        } catch (Exception e) {
            message = "Gift info add failed - Internal Server Error";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/gift-info/{giftInfoId}")
    public MyResponse<Integer> deleteGiftInfo(@PathVariable Integer giftInfoId) {
        String message;
        Integer response;

        if (giftInfoId != null && giftInfoId >= 0) {
            try {
                mGiftInfoService.deleteGiftInfo(giftInfoId);
                response = 1;
            } catch (EmptyResultDataAccessException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            } catch (DataIntegrityViolationException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            }

            boolean isDeleted = response == 1;
            message = isDeleted ? "Delete gift info success" : "Delete gift info failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "Gift info not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }
}
