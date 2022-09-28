package com.yh.store.mapper;


import com.yh.store.pojo.Address;
import com.yh.store.pojo.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@SpringBootTest 表示标注当前的类是测试类
@SpringBootTest
public class DistrictMapperTests {

    @Autowired
    private DistrictMapper districtMapper;

    /**
     * 单元测试方法：可以独立运行的条件，不用启动整个项目做单元测试，提升了代码的测试效率
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须是void
     * 3.方法的参数列表不指定任何类型
     * 4。方法的访问修饰符必须是public
     */
    @Test
    public void findByParent(){
        List<District> districtList = districtMapper.findByParent("110100");
        for (District d : districtList) {
            System.out.println(d);
        }
    }

    @Test
    public void findNameByCode(){
        String name = districtMapper.findNameByCode("610000");
        System.out.println(name);
    }



}
