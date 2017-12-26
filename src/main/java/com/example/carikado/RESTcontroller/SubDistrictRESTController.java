package com.example.carikado.RESTcontroller;

import com.example.carikado.model.MyPage;
import com.example.carikado.model.MyResponse;
import com.example.carikado.model.SubDistrict;
import com.example.carikado.service.SubDistrictService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SubDistrictRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubDistrictRESTController.class);
    private static final String[] PROPERTIES = {
            "name",
            "district.name",
            "district.city.name",
            "district.city.province.name",
            "district.city.province.country.name"
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private SubDistrictService mSubDistrictService;

    @Autowired
    public SubDistrictRESTController(SubDistrictService subDistrictService) {
        mSubDistrictService = subDistrictService;
    }

    @GetMapping("/api/sub-district/count/all")
    public MyResponse<Integer> countSubDistricts() {
        String message = "Count sub districts success";
        Integer count = mSubDistrictService.count();
        return new MyResponse<>(message, count);
    }

    @GetMapping("/api/sub-district/all")
    public MyResponse<List> findSubDistricts() {
        String message = "Find sub districts success";
        ArrayList<SubDistrict> subdistricts = (ArrayList<SubDistrict>) mSubDistrictService.findAll();
        return new MyResponse<>(message, subdistricts);
    }

    @GetMapping("/api/sub-district/district/{districtId}/all")
    public MyResponse<List> findCities(@PathVariable Integer districtId) {
        String message = "Find sub districts success";
        ArrayList<SubDistrict> subDistricts = (ArrayList<SubDistrict>) mSubDistrictService.findAll(districtId);
        return new MyResponse<>(message, subDistricts);
    }

    @GetMapping("/api/sub-district")
    public MyResponse<MyPage<List>> findSubDistricts(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                  @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<SubDistrict> subDistricts;
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
        Page<SubDistrict> subDistrictPage = mSubDistrictService.findAllPageable(pageRequest);

        subDistricts = subDistrictPage.getContent();
        message = "Find sub districts success";

        MyPage<List> myPage = new MyPage<>();

        myPage.setPage(++page);
        myPage.setLastPage(subDistrictPage.getTotalPages() == 0 ? 1 : subDistrictPage.getTotalPages());
        myPage.setPageSize(pageSize);
        myPage.setSort(sort == null ? 1 : sort);
        myPage.setTotalElement(subDistrictPage.getTotalElements());
        myPage.setData(subDistricts);

        return new MyResponse<>(message, myPage);
    }

    @GetMapping("/api/sub-district/{subDistrictId}")
    public MyResponse<SubDistrict> findSubDistrict(@PathVariable String subDistrictId) {
        SubDistrict subDistrict = null;
        String message = "Sub district not found";

        if (subDistrictId != null) {
            Long subDistrictIdL = Long.valueOf(subDistrictId);

            if (subDistrictIdL >= 0) {
                subDistrict = mSubDistrictService.findSubDistrict(subDistrictIdL);

                if (subDistrict != null)
                    message = "Find sub district success";
            }
        }

        return new MyResponse<>(message, subDistrict);
    }

    @PostMapping("/api/sub-district/add")
    public MyResponse<Integer> addSubDistrict(@RequestBody SubDistrict subDistrict) {
        String message = " Sub district ";
        int response;

        try {
            message += subDistrict.getId() != null ? "edit " : "add ";

            subDistrict = mSubDistrictService.addSubDistrict(subDistrict);

            boolean isSuccess = subDistrict != null;
            message += isSuccess ? "success" : "failed";
            response = isSuccess ? 1 : 0;
        } catch (DataIntegrityViolationException e) {
            message = "Sub district add failed - Sub district already exists";
            response = 0;
        } catch (Exception e) {
            message = "Sub district add failed - Internal Server Error";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/sub-district/{subDistrictId}")
    public MyResponse<Integer> deleteSubDistrict(@PathVariable String subDistrictId) {
        String message;
        Integer response;

        if (subDistrictId != null) {
            Long subDistrictIdL = Long.valueOf(subDistrictId);

            if (subDistrictIdL >= 0) {
                try {
                    mSubDistrictService.deleteSubDistrict(subDistrictIdL);
                    response = 1;
                } catch (EmptyResultDataAccessException e) {
                    LOGGER.error(e.getMessage());
                    response = 0;
                } catch (DataIntegrityViolationException e) {
                    LOGGER.error(e.getMessage());
                    response = 0;
                }

                boolean isDeleted = response == 1;
                message = isDeleted ? "Delete sub district success" : "Delete sub district failed";
                response = isDeleted ? 1 : 0;
            } else {
                message = "Sub district not found";
                response = 0;
            }
        } else {
            message = "Internal server error";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }
}
