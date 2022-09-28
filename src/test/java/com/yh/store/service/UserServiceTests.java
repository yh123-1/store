package com.yh.store.service;


import com.yh.store.exception.InsertException;
import com.yh.store.exception.UserNameDuplicatedException;
import com.yh.store.mapper.UserMapper;
import com.yh.store.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//@SpringBootTest 表示标注当前的类是测试类
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private IUserService userService;


    /**
     * 单元测试方法：可以独立运行的条件，不用启动整个项目做单元测试，提升了代码的测试效率
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须是void
     * 3.方法的参数列表不指定任何类型
     * 4。方法的访问修饰符必须是public
     */
    @Test
    public void insert(){
        try {
            User user = new User();
            user.setUsername("test03");
            user.setPassword("123");
            userService.insert(user);
            System.out.println("OK");
        } catch (UserNameDuplicatedException e) {
            //打印异常信息
            System.out.println(e.getMessage());
        } catch (InsertException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        User user = userService.login("test04", "123");
        System.out.println(user);
    }





    @Test
    public void changePassword() {
        userService.changePassword(8,"test03","123","321");
    }



    @Test
    public void getInfoByUid(){
        User user = userService.getInfoByUid(9);
        System.out.println(user);
    }



    @Test
    public void changeAvatar(){
//        userService.changeAvatar(8,"/upload/test.png","管理员");
    }

    @Test
    public void testTime(){
        Date date = new Date();
        long time = date.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = dateFormat.format(date);

        System.out.println(s);
    }
}
