package com.yh.store.mapper;


import com.yh.store.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

//@SpringBootTest 表示标注当前的类是测试类
@SpringBootTest
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 单元测试方法：可以独立运行的条件，不用启动整个项目做单元测试，提升了代码的测试效率
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须是void
     * 3.方法的参数列表不指定任何类型
     * 4。方法的访问修饰符必须是public
     */
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("tom");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void select(){
        User user = userMapper.selectByUserName("tom");
        System.out.println(user);
    }

    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(8);
        user.setPhone("123456789");
        user.setEmail("test03@qq.com");
        user.setGender(1);
        Integer rows = userMapper.updateInfoByUid(user);
        System.out.println(rows);
    }

    @Test
    public void updateAvatarByUid(){
        Integer rows = userMapper.updateAvatarByUid(8, "/upload/avatar.png", "管理员", new Date());
        System.out.println(rows);
    }

    @Test
    public void selectByUid(){
        Integer row = userMapper.updatePasswordByUid(7, "321", "管理员", new Date());
        System.out.println(row);
    }

    @Test
    public void updatePasswordByUid(){
        User user = userMapper.selectByUid(7);
        System.out.println(user);
    }

}
