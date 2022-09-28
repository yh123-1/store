package com.yh.store.service.impl;

import com.yh.store.exception.*;
import com.yh.store.mapper.AddressMapper;
import com.yh.store.mapper.DistrictMapper;
import com.yh.store.pojo.Address;
import com.yh.store.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private DistrictMapper districtMapper;

    @Value("${user.address.max-count}")
    private Integer maxCount;


    @Override
    public void addNewAddress(Address address, Integer uid, String username) {
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount){
            throw new AddressCountLimitException("用户收货地址超出限制");
        }

        //补全地址信息
        String province = districtMapper.findNameByCode(address.getProvinceCode());
        String city = districtMapper.findNameByCode(address.getCityCode());
        String area = districtMapper.findNameByCode(address.getAreaCode());
        address.setProvinceName(province);
        address.setCityName(city);
        address.setAreaName(area);

        //uid,is_default
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;//1表示默认地址
        address.setIsDefault(isDefault);
        //补全日志
        address.setModifiedUser(username);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        //调用插入地址的方法
        Integer rows = addressMapper.insertAddress(address);
        if (rows != 1){
            throw new InsertException("插入用户的收货地址产生未知的异常");
        }
    }

    @Override
    public List<Address> getAddressByUid(Integer uid) {
        List<Address> addressList = addressMapper.findAddressByUid(uid);
        for (Address address : addressList){
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setTel(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return addressList;
    }

    @Override
    @Transactional(rollbackFor = UpdateException.class)
    public void setDefaultAddress(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findAddressByAid(aid);
        if (address == null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测当前获取到的收货地址数据的归属
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        //先将所有地址设置为非默认
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows < 1){
            throw new UpdateException("更新数据产生未知的异常");
        }
        //将用户选中的地址修改为默认地址
         rows = addressMapper.updateIsDefaultByAid(aid, username, new Date());
        if (rows != 1){
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public void deleteAddress(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findAddressByAid(aid);
        if (address == null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        //检测当前获取到的收货地址数据的归属
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }

        //删除操作
        Integer rows = addressMapper.deleteAddressByAid(aid);
        if (rows != 1){
            throw new UpdateException("删除数据产生未知的异常");
        }

        //判断用户是否没有收货地址数据
        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            //终止程序
            return;
        }
        //判断是否删除的是默认地址
        if (address.getIsDefault() == 0){
            return;
        }
        //将最新修改的条数据设为默认地址
        Address lastModifiedTimeAddress = addressMapper.findByUidLastModifiedTime(uid);
        Integer updateIsDefault = addressMapper.updateIsDefaultByAid(lastModifiedTimeAddress.getAid(), username, new Date());
        if (updateIsDefault != 1){
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public void updateAddress(Integer aid,String username, Address address) {

        Address oldAddress = addressMapper.findAddressByAid(aid);
        if (oldAddress == null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }

        //补全信息aid
        address.setAid(aid);
        //补全地址信息
        String province = districtMapper.findNameByCode(address.getProvinceCode());
        String city = districtMapper.findNameByCode(address.getCityCode());
        String area = districtMapper.findNameByCode(address.getAreaCode());
        address.setProvinceName(province);
        address.setCityName(city);
        address.setAreaName(area);
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        Integer rows = addressMapper.updateAddressByAid(address);
        if (rows != 1){
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public Address queryOneAddressByAid(Integer aid,Integer uid) {
        Address address = addressMapper.findAddressByAid(aid);
        if (address == null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }
}
