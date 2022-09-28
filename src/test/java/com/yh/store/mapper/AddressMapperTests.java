package com.yh.store.mapper;


import com.yh.store.pojo.Address;
import com.yh.store.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

//@SpringBootTest 表示标注当前的类是测试类
@SpringBootTest
public class AddressMapperTests {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 单元测试方法：可以独立运行的条件，不用启动整个项目做单元测试，提升了代码的测试效率
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须是void
     * 3.方法的参数列表不指定任何类型
     * 4。方法的访问修饰符必须是public
     */
    @Test
    public void insertAddress(){
        Address address = new Address();
        address.setUid(6);
        address.setPhone("123456789");
        address.setName("女朋友");
        addressMapper.insertAddress(address);
    }

    @Test
    public void countByUid(){
        Integer rows = addressMapper.countByUid(6);
        System.out.println(rows);

    }

    @Test
    public void findAddressByUid(){
        List<Address> addressList = addressMapper.findAddressByUid(8);
        for (Address address : addressList){
            System.out.println(address);
        }
    }

    @Test
    public void findAddressByAid(){
        Address address = addressMapper.findAddressByAid(5);
        System.out.println(address);
    }

    @Test
    public void updateNonDefault(){
        Integer rows = addressMapper.updateNonDefault(6);
        System.out.println(rows);
    }

    @Test
    public void updateIsDefaultByAid(){
        Integer rows = addressMapper.updateIsDefaultByAid(1, "管理员", new Date());
        System.out.println(rows);
    }

    @Test
    public void deleteAddressByAid(){
        Integer rows = addressMapper.deleteAddressByAid(2);
        System.out.println(rows);
    }

    @Test
    public void findByUidLastModifiedTime(){
        Address address = addressMapper.findByUidLastModifiedTime(8);
        System.out.println(address);
    }

    @Test
    public void updateAddressByAid(){
        Address newAddress = new Address();
        newAddress.setAid(8);
        newAddress.setName("王五");
        newAddress.setTel("12345678900");
        Integer rows = addressMapper.updateAddressByAid(newAddress);
        System.out.println(rows);
    }
}
