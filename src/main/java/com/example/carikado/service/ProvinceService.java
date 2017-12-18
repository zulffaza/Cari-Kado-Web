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

    private ProvinceRepository mProvinceRepository;

    @Autowired
    public ProvinceService(ProvinceRepository provinceRepository) {
        mProvinceRepository = provinceRepository;
    }

    public Integer count() {
        return (int) mProvinceRepository.count();
    }

    public List<Province> findAll() {
        return mProvinceRepository.findAll();
    }

    public Page<Province> findAllPageable(Pageable pageable) {
        return mProvinceRepository.findAll(pageable);
    }

    public Province findProvince(Integer id) {
        return mProvinceRepository.findOne(id);
    }

    public Province addProvince(Province province) {
        return mProvinceRepository.save(province);
    }

    public void deleteProvince(Integer id) {
        mProvinceRepository.delete(id);
    }

}
