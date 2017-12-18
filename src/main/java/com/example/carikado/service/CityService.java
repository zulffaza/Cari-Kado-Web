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

    private CityRepository cityRepository;

    @Autowired
    public CityService (CityRepository cityRepository) { cityRepository = cityRepository; }

    public Integer count() { return (int) cityRepository.count(); }

    public List<City> findAll() { return cityRepository.findAll(); }

    public Page<City> findAllPageable(Pageable pageable) { return cityRepository.findAll(pageable); }

    public City findCity(Integer id) { return cityRepository.findOne(id); }

    public City addCity(City city) { return cityRepository.save(city); }

    public void deleteCity(Integer id) { cityRepository.delete(id); }
}
