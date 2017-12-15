package com.example.carikado.RESTcontroller;

import com.example.carikado.model.GiftInfo;
import com.example.carikado.model.MyResponse;
import com.example.carikado.service.GiftInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/api/giftinfo/all")
    public MyResponse<List> findGiftInfos() {
        String message = "Find gift infos success";
        ArrayList<GiftInfo> giftInfos = (ArrayList<GiftInfo>) mGiftInfoService.findAll();
        return new MyResponse<>(message, giftInfos);
    }

    @GetMapping("/api/giftinfo")
    public MyResponse<List> findGiftInfos(@RequestParam(required = false, defaultValue = "0") Integer page,
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
        giftInfos = mGiftInfoService.findAllPageable(pageRequest).getContent();

        message = "Find gift info success";

        return new MyResponse<>(message, giftInfos);
    }

    @GetMapping("/api/giftinfo/{giftInfoId}")
    public MyResponse<GiftInfo> findGiftInfo(@PathVariable Long giftInfoId) {
        GiftInfo giftInfo = null;
        String message = "Gift info not found";

        if (giftInfoId != null && giftInfoId >= 0) {
            giftInfo = mGiftInfoService.findGift(giftInfoId);

            if (giftInfo != null)
                message = "Find gift info success";
        }

        return new MyResponse<>(message, giftInfo);
    }

    @PostMapping("/api/giftinfo/add")
    public MyResponse<Integer> addGiftInfo(@RequestBody GiftInfo giftInfo) {
        if (giftInfo.getCreatedAt() == null)
            giftInfo.setCreatedAt(new Date());

        giftInfo = mGiftInfoService.addGiftInfo(giftInfo);

        boolean isSuccess = giftInfo != null;
        String message = isSuccess ? "Gift Info add success" : "Gift Info add failed";
        int response = isSuccess ? 1 : 0;

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/giftinfo/{giftInfoId}")
    public MyResponse<Integer> deleteGiftInfo(@PathVariable Long giftInfoId) {
        String message;
        Integer response;

        if (giftInfoId != null && giftInfoId >= 0) {
            GiftInfo giftInfo;
            boolean isDeleted;

            try {
                mGiftInfoService.deleteGiftInfo(giftInfoId);
                giftInfo = mGiftInfoService.findGift(giftInfoId);

                isDeleted = giftInfo == null;
            } catch (EmptyResultDataAccessException e) {
                isDeleted = false;
                LOGGER.error(e.getMessage());
            }

            message = isDeleted ? "Delete gift info success" : "Delete gift info failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "Gift info not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }
}
