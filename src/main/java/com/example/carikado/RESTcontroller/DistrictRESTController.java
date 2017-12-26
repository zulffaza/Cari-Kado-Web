package com.example.carikado.RESTcontroller;

import com.example.carikado.model.District;
import com.example.carikado.model.MyPage;
import com.example.carikado.model.MyResponse;
import com.example.carikado.service.DistrictService;
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
public class DistrictRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistrictRESTController.class);
    private static final String[] PROPERTIES = {
            "name",
            "district.name",
            "district.province.name",
            "district.province.country.name"
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private DistrictService mDistrictService;

    @Autowired
    public DistrictRESTController(DistrictService districtService) {
        mDistrictService = districtService;
    }

    @GetMapping("/api/district/count/all")
    public MyResponse<Integer> countDistricts() {
        String message = "Count districts success";
        Integer count = mDistrictService.count();
        return new MyResponse<>(message, count);
    }

    @GetMapping("/api/district/all")
    public MyResponse<List> findDistricts() {
        String message = "Find districts success";
        ArrayList<District> districts = (ArrayList<District>) mDistrictService.findAll();
        return new MyResponse<>(message, districts);
    }

    @GetMapping("/api/district/city/{cityId}/all")
    public MyResponse<List> findCities(@PathVariable Integer cityId) {
        String message = "Find districts success";
        ArrayList<District> districts = (ArrayList<District>) mDistrictService.findAll(cityId);
        return new MyResponse<>(message, districts);
    }

    @GetMapping("/api/district")
    public MyResponse<MyPage<List>> findDistricts(@RequestParam(required = false, defaultValue = "0") Integer page,
                                               @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                               @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<District> districts;
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
        Page<District> districtPage = mDistrictService.findAllPageable(pageRequest);

        districts = districtPage.getContent();
        message = "Find districts success";

        MyPage<List> myPage = new MyPage<>();

        myPage.setPage(++page);
        myPage.setLastPage(districtPage.getTotalPages() == 0 ? 1 : districtPage.getTotalPages());
        myPage.setPageSize(pageSize);
        myPage.setSort(sort == null ? 1 : sort);
        myPage.setTotalElement(districtPage.getTotalElements());
        myPage.setData(districts);

        return new MyResponse<>(message, myPage);
    }

    @GetMapping("/api/district/{districtId}")
    public MyResponse<District> findDistrict(@PathVariable Integer districtId) {
        District district = null;
        String message = "District not found";

        if (districtId != null && districtId >= 0) {
            district = mDistrictService.findDistrict(districtId);

            if (district != null)
                message = "Find district success";
        }

        return new MyResponse<>(message, district);
    }

    @PostMapping("/api/district/add")
    public MyResponse<Integer> addDistrict(@RequestBody District district) {
        String message = "District ";
        int response;

        try {
            message += district.getId() != null ? "edit " : "add ";

            district = mDistrictService.addDistrict(district);

            boolean isSuccess = district != null;
            message += isSuccess ? "success" : "failed";
            response = isSuccess ? 1 : 0;
        } catch (DataIntegrityViolationException e) {
            message = "District add failed - District already exists";
            response = 0;
        } catch (Exception e) {
            message = "District add failed - Internal Server Error";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/district/{districtId}")
    public MyResponse<Integer> deleteDistrict(@PathVariable Integer districtId) {
        String message;
        Integer response;

        if (districtId != null && districtId >= 0) {
            try {
                mDistrictService.deleteDistrict(districtId);
                response = 1;
            } catch (EmptyResultDataAccessException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            } catch (DataIntegrityViolationException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            }

            boolean isDeleted = response == 1;
            message = isDeleted ? "Delete district success" : "Delete district failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "District not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }
}
