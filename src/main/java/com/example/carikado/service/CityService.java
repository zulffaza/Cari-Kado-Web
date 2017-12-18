package com.example.carikado.service;


import com.example.carikado.model.City;
import com.example.carikado.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cityService")
public class CityService {

    private CityRepository mCityRepository;

    @Autowired
    public CityService (CityRepository cityRepository) { mCityRepository = cityRepository; }

    public Integer count() { return (int) mCityRepository.count(); }

    public List<City> findAll() { return mCityRepository.findAll(); }

    public Page<City> findAllPageable(Pageable pageable) { return mCityRepository.findAll(pageable); }

    public City findCity(Integer id) { return mCityRepository.findOne(id); }

    public City addCity(City city) { return mCityRepository.save(city); }

    public void deleteCity(Integer id) { mCityRepository.delete(id); }
}
