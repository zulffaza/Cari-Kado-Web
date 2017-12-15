package com.example.carikado.service;

import com.example.carikado.model.Country;
import com.example.carikado.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("countryService")
public class CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) { countryRepository = countryRepository; }

    public Integer count() { return (int) countryRepository.count(); }

    public List<Country> findAll() { return countryRepository.findAll();}

    public Page<Country> findAllPageable(Pageable pageable) { return countryRepository.findAll(pageable); }

    public Country findCountry(Integer id) { return countryRepository.findOne(id); }

    public Country addCountry(Country country) { return countryRepository.save(country); }

    public void deleteCountry(Integer id) { countryRepository.delete(id); }
}
