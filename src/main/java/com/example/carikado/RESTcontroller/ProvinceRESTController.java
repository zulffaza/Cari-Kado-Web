package com.example.carikado.RESTcontroller;

import com.example.carikado.model.MyResponse;
import com.example.carikado.model.Province;
import com.example.carikado.service.ProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class ProvinceRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProvinceRESTController.class);
    private static final String[] PROPERTIES = {
            "name"
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private ProvinceService provinceService;

    @Autowired
    public ProvinceRESTController(ProvinceService provinceService) {
        provinceService = provinceService;
    }

    @GetMapping("/api/province/count/all")
    public MyResponse<Integer> countProvinces() {
        String message = "Count provinces success";
        Integer count = provinceService.count();
        return new MyResponse<>(message, count);
    }

    @GetMapping("/api/province/all")
    public MyResponse<List> findProvinces() {
        String message = "Find provinces success";
        ArrayList<Province> provinces = (ArrayList<Province>) provinceService.findAll();
        return new MyResponse<>(message, provinces);
    }

    @GetMapping("/api/province")
    public MyResponse<List> findProvinces(@RequestParam(required = false, defaultValue = "0") Integer page,
                                      @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                      @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<Province> provinces = null;
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
        provinces = provinceService.findAllPageable(pageRequest).getContent();

        message = "Find gift info success";

        return new MyResponse<>(message, provinces);
    }

    @GetMapping("/api/province/{provinceId}")
    public MyResponse<Province> findProvince(@PathVariable Integer provinceId) {
        Province province = null;
        String message = "Province not found";

        if (provinceId != null && provinceId >= 0) {
            province = provinceService.findProvince(provinceId);

            if (province != null)
                message = "Find province success";
        }

        return new MyResponse<>(message, province);
    }

    @PostMapping("/api/province/add")
    public MyResponse<Integer> addProvince(@RequestBody Province province) {
        LOGGER.info(province.getId() + "");
        LOGGER.info(province.getName());

        province = provinceService.addProvince(province);

        boolean isSuccess = province != null;
        String message = isSuccess ? "Province add success" : "Province add failed";
        int response = isSuccess ? 1 : 0;

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/province/{provinceId}")
    public MyResponse<Integer> deleteProvince(@PathVariable Integer provinceId) {
        String message;
        Integer response;

        if (provinceId != null && provinceId >= 0) {
            Province province;
            boolean isDeleted;

            try {
                provinceService.deleteProvince(provinceId);
                province = provinceService.findProvince(provinceId);

                isDeleted = province == null;
            } catch (EmptyResultDataAccessException e) {
                isDeleted = false;
                LOGGER.error(e.getMessage());
            } catch (DataIntegrityViolationException e) {
                isDeleted = false;
                LOGGER.error(e.getMessage());
            }

            message = isDeleted ? "Delete province success" : "Delete province failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "Province not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }

}
