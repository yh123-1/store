package com.yh.store.service.impl;

import com.yh.store.mapper.DistrictMapper;
import com.yh.store.pojo.District;
import com.yh.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> districtList = districtMapper.findByParent(parent);
        return districtList;
    }

    @Override
    public String getNameByCode(String code) {
        String name = districtMapper.findNameByCode(code);
        return name;
    }
}
