package com.yh.store.controller;

import com.yh.store.pojo.Address;
import com.yh.store.service.IAddressService;
import com.yh.store.utils.GetFromSession;
import com.yh.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(HttpSession session, Address address){
        Integer uid = GetFromSession.getUidFromSession(session);
        String username = GetFromSession.getUsernameFromSession(session);

        addressService.addNewAddress(address,uid,username);
        return JsonResult.success("新增收货地址成功");
    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getAddressByUid(HttpSession session){
        Integer uid = GetFromSession.getUidFromSession(session);
        List<Address> addressList = addressService.getAddressByUid(uid);
        return new JsonResult<>(JsonResult.OK,addressList);
    }


    //Restful风格
    @RequestMapping("/{aid}/set_default_address")
    public JsonResult<Void> setDefaultAddress(@PathVariable("aid") Integer aid,HttpSession session){
        Integer uid = GetFromSession.getUidFromSession(session);
        String username = GetFromSession.getUsernameFromSession(session);
        addressService.setDefaultAddress(aid,uid,username);
        return JsonResult.success("修改成功");
    }

    @RequestMapping("/{aid}/delete_address")
    public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid,HttpSession session){
        Integer uid = GetFromSession.getUidFromSession(session);
        String username = GetFromSession.getUsernameFromSession(session);
        addressService.deleteAddress(aid,uid,username);
        return JsonResult.success("删除成功");
    }

    @RequestMapping("/{aid}/update_address")
    public JsonResult<Void> updateAddress(@PathVariable("aid") Integer aid,Address address,HttpSession session){
        String username = GetFromSession.getUsernameFromSession(session);
        addressService.updateAddress(aid,username,address);
        return JsonResult.success("修改成功");
    }

    @RequestMapping("/{aid}/query_one_address")
    public JsonResult<Address> queryOneAddress(@PathVariable Integer aid,HttpSession session){
        Integer uid = GetFromSession.getUidFromSession(session);
        Address address = addressService.queryOneAddressByAid(aid,uid);
        return JsonResult.success(address);

    }

}
