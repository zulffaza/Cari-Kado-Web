package com.example.carikado.RESTcontroller;

import com.example.carikado.model.MyPage;
import com.example.carikado.model.MyResponse;
import com.example.carikado.model.Province;
import com.example.carikado.service.ProvinceService;
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
public class ProvinceRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProvinceRESTController.class);
    private static final String[] PROPERTIES = {
            "name",
            "country.name"
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private ProvinceService mProvinceService;

    @Autowired
    public ProvinceRESTController(ProvinceService provinceService) {
        mProvinceService = provinceService;
    }

    @GetMapping("/api/province/count/all")
    public MyResponse<Integer> countProvinces() {
        String message = "Count provinces success";
        Integer count = mProvinceService.count();
        return new MyResponse<>(message, count);
    }

    @GetMapping("/api/province/all")
    public MyResponse<List> findProvinces() {
        String message = "Find provinces success";
        ArrayList<Province> provinces = (ArrayList<Province>) mProvinceService.findAll();
        return new MyResponse<>(message, provinces);
    }

    @GetMapping("/api/province")
    public MyResponse<MyPage<List>> findProvinces(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                 @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                 @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<Province> provinces;
        String message;
        Sort.Direction direction;

        if (page > 0)
            page -= 1;

        if (page < 0)
            page = 0;

        int propertiesIndex = 0;
        int directionIndex = 0;

        if (sort != null && sort >= 1 && sort <= 4) {
            boolean isPrime = sort % 2 == 0;
            directionIndex = isPrime ? 1 : 0;

            propertiesIndex = (int) (Math.ceil((double) sort / 2) - 1);
        }

        direction = Sort.Direction.fromString(DIRECTION[directionIndex]);
        properties.add(PROPERTIES[propertiesIndex]);

        Sort sortOrder = new Sort(direction, properties);
        PageRequest pageRequest = new PageRequest(page, pageSize, sortOrder);
        Page<Province> provincePage = mProvinceService.findAllPageable(pageRequest);

        provinces = provincePage.getContent();
        message = "Find provinces success";

        MyPage<List> myPage = new MyPage<>();

        myPage.setPage(++page);
        myPage.setLastPage(provincePage.getTotalPages());
        myPage.setPageSize(pageSize);
        myPage.setSort(sort == null ? 1 : sort);
        myPage.setTotalElement(provincePage.getTotalElements());
        myPage.setData(provinces);

        return new MyResponse<>(message, myPage);
    }

    @GetMapping("/api/province/{provinceId}")
    public MyResponse<Province> findProvince(@PathVariable Integer provinceId) {
        Province province = null;
        String message = "Province not found";

        if (provinceId != null && provinceId >= 0) {
            province = mProvinceService.findProvince(provinceId);

            if (province != null)
                message = "Find province success";
        }

        return new MyResponse<>(message, province);
    }

    @PostMapping("/api/province/add")
    public MyResponse<Integer> addProvince(@RequestBody Province province) {
        String message = "Province ";
        int response;

        try {
            message += province.getId() != null ? "edit " : "add ";

            province = mProvinceService.addProvince(province);

            boolean isSuccess = province != null;
            message += isSuccess ? "success" : "failed";
            response = isSuccess ? 1 : 0;
        } catch (DataIntegrityViolationException e) {
            message = "Province add failed - Province already exists";
            response = 0;
        } catch (Exception e) {
            message = "Province add failed - Internal Server Error";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/province/{provinceId}")
    public MyResponse<Integer> deleteProvince(@PathVariable Integer provinceId) {
        String message;
        Integer response;

        if (provinceId != null && provinceId >= 0) {
            try {
                mProvinceService.deleteProvince(provinceId);
                response = 1;
            } catch (EmptyResultDataAccessException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            } catch (DataIntegrityViolationException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            }

            boolean isDeleted = response == 1;
            message = isDeleted ? "Delete province success" : "Delete province failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "Province not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }

}
