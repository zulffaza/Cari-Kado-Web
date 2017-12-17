package com.example.carikado.service;


import com.example.carikado.model.Province;
import com.example.carikado.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("provinceService")
public class ProvinceService {

    private ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceService (ProvinceRepository provinceRepository) { provinceRepository = provinceRepository; }

    public Integer count() { return (int) provinceRepository.count(); }

    public List<Province> findAll() { return provinceRepository.findAll(); }

    public Page<Province> findAllPageable(Pageable pageable) { return provinceRepository.findAll(pageable); }

    public Province findProvince(Integer id) { return provinceRepository.findOne(id); }

    public Province addProvince(Province province) { return provinceRepository.save(province); }

    public void deleteProvince(Integer id) { provinceRepository.delete(id); }

}
