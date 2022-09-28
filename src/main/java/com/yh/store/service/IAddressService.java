package com.yh.store.service;

import com.yh.store.pojo.Address;
import jdk.internal.dynalink.linker.LinkerServices;

import java.util.List;

/**
 * 收货地址业务层接口
 */
public interface IAddressService {

    /**
     * 新增收货地址
     * @param address 收货地址数据
     * @param uid 用户id
     * @param username 修改者
     */
    void addNewAddress(Address address, Integer uid, String username);

    /**
     * 得到用户的所有收货地址
     * @param uid 用户id
     * @return 收货地址数据
     */
    List<Address> getAddressByUid(Integer uid);

    /**
     * 修改某个用户的某条收货地址为默认地址
     * @param aid
     * @param uid
     * @param username
     */
    void setDefaultAddress(Integer aid,Integer uid,String username);

    /**
     * 删除用户选中的收货地址
     * @param aid 收货地址id
     * @param uid 用户id
     * @param username 用户名
     */
    void deleteAddress(Integer aid,Integer uid,String username);

    /**
     * 修改收货地址
     * @param aid 要修改的收货地址id
     * @param usname 修改者
     * @param address 修改的收货地址
     */
    void updateAddress(Integer aid,String usname,Address address);

    /**
     * 根据aid查询收货地址
     * @param aid 收货地址id
     * @param uid 用户id
     * @return 查询到的收货地址
     */
    Address queryOneAddressByAid(Integer aid,Integer uid);
}
