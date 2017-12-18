package com.example.carikado.service;

import com.example.carikado.model.District;
import com.example.carikado.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("districtService")
public class DistrictService {

    private DistrictRepository mDistrictRepository;

    @Autowired
    public DistrictService(DistrictRepository districtRepository) {
        mDistrictRepository = districtRepository;
    }

    public Integer count() { return (int) mDistrictRepository.count(); }

    public List<District> findAll() { return mDistrictRepository.findAll(); }

    public Page<District> findAllPageable(Pageable pageable) { return mDistrictRepository.findAll(pageable); }

    public District findDistrict(Integer id) { return mDistrictRepository.findOne(id); }

    public District addDistrict(District district) { return mDistrictRepository.save(district); }

    public void deleteDistrict(Integer id) { mDistrictRepository.delete(id); }
}
