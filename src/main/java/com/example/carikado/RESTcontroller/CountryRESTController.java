package com.example.carikado.RESTcontroller;

import com.example.carikado.model.Country;
import com.example.carikado.model.MyResponse;
import com.example.carikado.model.Country;
import com.example.carikado.service.CountryService;
import com.example.carikado.service.CountryService;
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

@RestController
public class CountryRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryRESTController.class);
    private static final String[] PROPERTIES = {
            "name"
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private CountryService countryService;

    @Autowired
    public CountryRESTController(CountryService countryService) {
        countryService = countryService;
    }

    @GetMapping("/api/country/count/all")
    public MyResponse<Integer> countCountry() {
        String message = "Count Country success";
        Integer count = countryService.count();
        return new MyResponse<>(message, count);
    }

    @GetMapping("/api/country/all")
    public MyResponse<List> findCountry() {
        String message = "Find Country success";
        ArrayList<Country> countries = (ArrayList<Country>) countryService.findAll();
        return new MyResponse<>(message, countries);
    }

    @GetMapping("/api/country")
    public MyResponse<List> findRoles(@RequestParam(required = false, defaultValue = "0") Integer page,
                                      @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                      @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<Country> countries = null;
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
        countries = countryService.findAllPageable(pageRequest).getContent();

        message = "Find gift info success";

        return new MyResponse<>(message, countries);
    }

    @GetMapping("/api/country/{countryId}")
    public MyResponse<Country> findCountry(@PathVariable Integer countryId) {
        Country country = null;
        String message = "Country not found";

        if (country != null && countryId >= 0) {
            country = countryService.findCountry(countryId);

            if (country != null)
                message = "Find country success";
        }

        return new MyResponse<>(message, country);
    }

    @PostMapping("/api/country/add")
    public MyResponse<Integer> addCountry(@RequestBody Country country) {
        LOGGER.info(country.getId() + "");
        LOGGER.info(country.getName());

        country = countryService.addCountry(country);

        boolean isSuccess = country != null;
        String message = isSuccess ? "Country add success" : "Country add failed";
        int response = isSuccess ? 1 : 0;

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/country/{countryId}")
    public MyResponse<Integer> deleteCountry(@PathVariable Integer countryId) {
        String message;
        Integer response;

        if (countryId != null && countryId >= 0) {
            Country country;
            boolean isDeleted;

            try {
                countryService.deleteCountry(countryId);
                country = countryService.findCountry(countryId);

                isDeleted = country == null;
            } catch (EmptyResultDataAccessException e) {
                isDeleted = false;
                LOGGER.error(e.getMessage());
            } catch (DataIntegrityViolationException e) {
                isDeleted = false;
                LOGGER.error(e.getMessage());
            }

            message = isDeleted ? "Delete country success" : "Delete country failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "Country not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }
}
