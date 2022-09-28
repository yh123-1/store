package com.yh.store.service;


import com.yh.store.pojo.Address;
import com.yh.store.pojo.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DistrictServiceTests {

    @Autowired
    private IDistrictService districtService;

    @Test
    public void getByParent(){

        List<District> districtList = districtService.getByParent("86");
        for (District d : districtList){
            System.out.println(d);
        }
    }
}
