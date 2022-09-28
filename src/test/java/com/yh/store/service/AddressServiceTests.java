package com.yh.store.service;


import com.yh.store.pojo.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AddressServiceTests {

    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("987654321");
        address.setName("女朋友");
        addressService.addNewAddress(address,6,"管理员");
    }

    @Test
    public void getAddressByUid(){
        List<Address> addressList = addressService.getAddressByUid(8);
        for (Address address : addressList){
            System.out.println(address);
        }
    }

    @Test
    public void setDefaultAddress(){
        addressService.setDefaultAddress(5,8,"管理员");
    }

    @Test
    public void deleteAddress(){
        addressService.deleteAddress(4,8,"管理员");
    }

    @Test
    public void updateAddress(){
        Address address = new Address();
        address.setName("李四");
        address.setPhone("123456789");
        addressService.updateAddress(8,"test03",address);
    }

    @Test
    public void queryOneAddressByAid(){
        Address address = addressService.queryOneAddressByAid(6,8);
        System.out.println(address);
    }
}
