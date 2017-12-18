package com.example.carikado.RESTcontroller;

import com.example.carikado.model.City;
import com.example.carikado.model.MyPage;
import com.example.carikado.model.MyResponse;
import com.example.carikado.service.CityService;
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
import org.slf4j.Logger;

@RestController
public class CityRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityRESTController.class);
    private static final String[] PROPERTIES = {
            "name",
            "province.name",
            "province.country.name"
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private CityService mCityService;

    @Autowired
    public CityRESTController(CityService cityService) {
        mCityService = cityService;
    }

    @GetMapping("/api/city/count/all")
    public MyResponse<Integer> countCities() {
        String message = "Count cities success";
        Integer count = mCityService.count();
        return new MyResponse<>(message, count);
    }

    @GetMapping("/api/city/all")
    public MyResponse<List> findCities() {
        String message = "Find cities success";
        ArrayList<City> cities = (ArrayList<City>) mCityService.findAll();
        return new MyResponse<>(message, cities);
    }

    @GetMapping("/api/city")
    public MyResponse<MyPage<List>> findCities(@RequestParam(required = false, defaultValue = "0") Integer page,
                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<City> cities;
        String message;
        Sort.Direction direction;

        if (page > 0)
            page -= 1;

        if (page < 0)
            page = 0;

        int propertiesIndex = 0;
        int directionIndex = 0;

        if (sort != null && sort >= 1 && sort <= 6) {
            boolean isPrime = sort % 2 == 0;
            directionIndex = isPrime ? 1 : 0;

            propertiesIndex = (int) (Math.ceil((double) sort / 2) - 1);
        }

        direction = Sort.Direction.fromString(DIRECTION[directionIndex]);
        properties.add(PROPERTIES[propertiesIndex]);

        Sort sortOrder = new Sort(direction, properties);
        PageRequest pageRequest = new PageRequest(page, pageSize, sortOrder);
        Page<City> cityPage = mCityService.findAllPageable(pageRequest);

        cities = cityPage.getContent();
        message = "Find cities success";

        MyPage<List> myPage = new MyPage<>();

        myPage.setPage(++page);
        myPage.setLastPage(cityPage.getTotalPages());
        myPage.setPageSize(pageSize);
        myPage.setSort(sort == null ? 1 : sort);
        myPage.setTotalElement(cityPage.getTotalElements());
        myPage.setData(cities);

        return new MyResponse<>(message, myPage);
    }

    @GetMapping("/api/city/{cityId}")
    public MyResponse<City> findCity(@PathVariable Integer cityId) {
        City city = null;
        String message = "City not found";

        if (cityId != null && cityId >= 0) {
            city = mCityService.findCity(cityId);

            if (city != null)
                message = "Find city success";
        }

        return new MyResponse<>(message, city);
    }

    @PostMapping("/api/city/add")
    public MyResponse<Integer> addCity(@RequestBody City city) {
        String message = "City ";
        int response;

        try {
            message += city.getId() != null ? "edit " : "add ";

            city = mCityService.addCity(city);

            boolean isSuccess = city != null;
            message += isSuccess ? "success" : "failed";
            response = isSuccess ? 1 : 0;
        } catch (DataIntegrityViolationException e) {
            message = "City add failed - City already exists";
            response = 0;
        } catch (Exception e) {
            message = "City add failed - Internal Server Error";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/city/{cityId}")
    public MyResponse<Integer> deleteCity(@PathVariable Integer cityId) {
        String message;
        Integer response;

        if (cityId != null && cityId >= 0) {
            try {
                mCityService.deleteCity(cityId);
                response = 1;
            } catch (EmptyResultDataAccessException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            } catch (DataIntegrityViolationException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            }

            boolean isDeleted = response == 1;
            message = isDeleted ? "Delete city success" : "Delete city failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "City not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }
}
