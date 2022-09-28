package com.yh.store.service;

import com.yh.store.pojo.District;

import java.util.List;

public interface IDistrictService {

    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 多个区域的信息
     */
    List<District> getByParent(String parent);


    String getNameByCode(String code);
}
