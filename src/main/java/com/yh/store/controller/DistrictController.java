package com.yh.store.controller;


import com.yh.store.pojo.District;
import com.yh.store.service.IDistrictService;
import com.yh.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/districts")
public class DistrictController {

    @Autowired
    private IDistrictService districtService;


    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByparent(String parent){
        List<District> districtList = districtService.getByParent(parent);
        return new JsonResult<>(JsonResult.OK,districtList);
    }
}
