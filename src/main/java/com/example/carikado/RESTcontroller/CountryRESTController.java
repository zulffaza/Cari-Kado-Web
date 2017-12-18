package com.example.carikado.RESTcontroller;

import com.example.carikado.model.Country;
import com.example.carikado.model.MyPage;
import com.example.carikado.model.MyResponse;
import com.example.carikado.service.CountryService;
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
public class CountryRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryRESTController.class);
    private static final String[] PROPERTIES = {
            "name"
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private CountryService mCountryService;

    @Autowired
    public CountryRESTController(CountryService countryService) {
        mCountryService = countryService;
    }

    @GetMapping("/api/country/count/all")
    public MyResponse<Integer> countCountries() {
        String message = "Count Country success";
        Integer count = mCountryService.count();
        return new MyResponse<>(message, count);
    }

    @GetMapping("/api/country/all")
    public MyResponse<List> findCountries() {
        String message = "Find Country success";
        ArrayList<Country> countries = (ArrayList<Country>) mCountryService.findAll();
        return new MyResponse<>(message, countries);
    }

    @GetMapping("/api/country")
    public MyResponse<MyPage<List>> findCountries(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                 @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                 @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<Country> countries;
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
        Page<Country> rolePage = mCountryService.findAllPageable(pageRequest);

        countries = rolePage.getContent();
        message = "Find countries success";

        MyPage<List> myPage = new MyPage<>();

        myPage.setPage(++page);
        myPage.setLastPage(rolePage.getTotalPages());
        myPage.setPageSize(pageSize);
        myPage.setSort(sort == null ? 1 : sort);
        myPage.setTotalElement(rolePage.getTotalElements());
        myPage.setData(countries);

        return new MyResponse<>(message, myPage);
    }

    @GetMapping("/api/country/{countryId}")
    public MyResponse<Country> findCountry(@PathVariable Integer countryId) {
        Country country = null;
        String message = "Country not found";

        if (countryId != null && countryId >= 0) {
            country = mCountryService.findCountry(countryId);

            if (country != null)
                message = "Find country success";
        }

        return new MyResponse<>(message, country);
    }

    @PostMapping("/api/country/add")
    public MyResponse<Integer> addCountry(@RequestBody Country country) {
        String message = "Country ";
        int response;

        try {
            message += country.getId() != null ? "edit " : "add ";

            country = mCountryService.addCountry(country);

            boolean isSuccess = country != null;
            message += isSuccess ? "success" : "failed";
            response = isSuccess ? 1 : 0;
        } catch (DataIntegrityViolationException e) {
            message = "Country add failed - Country already exists";
            response = 0;
        } catch (Exception e) {
            message = "Country add failed - Internal Server Error";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/country/{countryId}")
    public MyResponse<Integer> deleteCountry(@PathVariable Integer countryId) {
        String message;
        Integer response;

        if (countryId != null && countryId >= 0) {
            try {
                mCountryService.deleteCountry(countryId);
                response = 1;
            } catch (EmptyResultDataAccessException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            } catch (DataIntegrityViolationException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            }

            boolean isDeleted = response == 1;
            message = isDeleted ? "Delete country success" : "Delete country failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "Country not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }
}
