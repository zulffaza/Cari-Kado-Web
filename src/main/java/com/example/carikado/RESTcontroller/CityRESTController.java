package com.example.carikado.RESTcontroller;


import com.example.carikado.model.City;
import com.example.carikado.model.MyResponse;
import com.example.carikado.service.CityService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

public class CityRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityRESTController.class);
    private static final String[] PROPERTIES = {
            "name"
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private CityService cityService;

    @Autowired
    public CityRESTController(CityService cityService) {
        cityService = cityService;
    }

    @GetMapping("/api/city/count/all")
    public MyResponse<Integer> countCitys() {
        String message = "Count cities success";
        Integer count = cityService.count();
        return new MyResponse<>(message, count);
    }

    @GetMapping("/api/city/all")
    public MyResponse<List> findCitys() {
        String message = "Find cities success";
        ArrayList<City> cities = (ArrayList<City>) cityService.findAll();
        return new MyResponse<>(message, cities);
    }

    @GetMapping("/api/city")
    public MyResponse<List> findCitys(@RequestParam(required = false, defaultValue = "0") Integer page,
                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                          @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<City> cities = null;
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
        cities = cityService.findAllPageable(pageRequest).getContent();

        message = "Find gift info success";

        return new MyResponse<>(message, cities);
    }

    @GetMapping("/api/city/{cityId}")
    public MyResponse<City> findCity(@PathVariable Integer cityId) {
        City city = null;
        String message = "City not found";

        if (cityId != null && cityId >= 0) {
            city = cityService.findCity(cityId);

            if (city != null)
                message = "Find city success";
        }

        return new MyResponse<>(message, city);
    }

    @PostMapping("/api/city/add")
    public MyResponse<Integer> addCity(@RequestBody City city) {
        LOGGER.info(city.getId() + "");
        LOGGER.info(city.getName());

        city = cityService.addCity(city);

        boolean isSuccess = city != null;
        String message = isSuccess ? "City add success" : "City add failed";
        int response = isSuccess ? 1 : 0;

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/city/{cityId}")
    public MyResponse<Integer> deleteCity(@PathVariable Integer cityId) {
        String message;
        Integer response;

        if (cityId != null && cityId >= 0) {
            City city;
            boolean isDeleted;

            try {
                cityService.deleteCity(cityId);
                city = cityService.findCity(cityId);

                isDeleted = city == null;
            } catch (EmptyResultDataAccessException e) {
                isDeleted = false;
                LOGGER.error(e.getMessage());
            } catch (DataIntegrityViolationException e) {
                isDeleted = false;
                LOGGER.error(e.getMessage());
            }

            message = isDeleted ? "Delete city success" : "Delete city failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "City not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }

}
