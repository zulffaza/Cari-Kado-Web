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

    private CountryRepository mCountryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) { mCountryRepository = countryRepository; }

    public Integer count() { return (int) mCountryRepository.count(); }

    public List<Country> findAll() { return mCountryRepository.findAllWithSort();}

    public Page<Country> findAllPageable(Pageable pageable) { return mCountryRepository.findAll(pageable); }

    public Country findCountry(Integer id) { return mCountryRepository.findOne(id); }

    public Country addCountry(Country country) { return mCountryRepository.save(country); }

    public void deleteCountry(Integer id) { mCountryRepository.delete(id); }
}
