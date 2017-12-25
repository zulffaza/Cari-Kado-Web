package com.example.carikado.service;

import com.example.carikado.model.SubDistrict;
import com.example.carikado.repository.SubDistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("subDistrictService")
public class SubDistrictService {

    private SubDistrictRepository mSubDistrictRepository;

    @Autowired
    public SubDistrictService(SubDistrictRepository subDistrictRepository) {
        mSubDistrictRepository = subDistrictRepository;
    }

    public Integer count() { return (int) mSubDistrictRepository.count(); }

    public List<SubDistrict> findAll() { return mSubDistrictRepository.findAllWithSort(); }

    public List<SubDistrict> findAll(Integer districtId) {
        return mSubDistrictRepository.findAllByDistrictId(districtId);
    }

    public Page<SubDistrict> findAllPageable(Pageable pageable) { return mSubDistrictRepository.findAll(pageable); }

    public SubDistrict findSubDistrict(Long id) { return mSubDistrictRepository.findOne(id); }

    public SubDistrict addSubDistrict(SubDistrict subDistrict) { return mSubDistrictRepository.save(subDistrict); }

    public void deleteSubDistrict(Long id) { mSubDistrictRepository.delete(id); }
}
