package com.yh.store.controller;

import com.yh.store.exception.FileEmptyException;
import com.yh.store.exception.FileSizeException;
import com.yh.store.exception.FileTypeException;
import com.yh.store.pojo.User;
import com.yh.store.service.IUserService;
import com.yh.store.utils.GetFromSession;
import com.yh.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController //@Controller + @ResponseBody 返回JSON格式的数据
@RequestMapping("/users")
public class UserController {



    @Autowired
    private IUserService userService;


    @RequestMapping("/reg")
    public JsonResult<Void> register(User user){
        userService.insert(user);
        return JsonResult.success("注册成功");
    }
    /*
    @RequestMapping("/reg")
    public JsonResult<Void> register(User user){
        JsonResult<Void> result = new JsonResult<>();

        try {
            userService.insert(user);
            result.setState(100);
            result.setMessage("用户注册成功");
        } catch (InsertException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        } catch (UserNameDuplicatedException e){
            result.setState(5000);
            result.setMessage("注册时发生了未知的异常");
        }
        return result;
    }
    */

    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username, password);
        //向session域中完成数据的绑定(uid,username)
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        session.setAttribute("avatar",data.getAvatar());
        return JsonResult.success(data);
    }

    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid = GetFromSession.getUidFromSession(session);
        String username = GetFromSession.getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return JsonResult.success("密码修改成功");
    }

    @RequestMapping("/get_info_by_uid")
    public JsonResult<User> getInfoByUid(HttpSession session){
        Integer uid = GetFromSession.getUidFromSession(session);
        User data = userService.getInfoByUid(uid);
        return JsonResult.success(data);
    }


    @RequestMapping("/change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        //user对象包含四部分数据，username，phone，email，gender
        //uid需要封装到user对象中
        Integer uid = GetFromSession.getUidFromSession(session);
        userService.changeInfo(uid,user);
        return JsonResult.success("修改成功");
    }

    //MultipartFile接口是SpringMVC提供的一个接口，可以获取文件类型的数据（任何类型的file都可以）
    //只需要在处理请求的方法参数列表上声明一个参数类型为MultipartFile的参数
    @RequestMapping("/change_avatar")
    public JsonResult<Void> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file){


        String avatar = userService.changeAvatar(session, file);

        //返回的avatar为文件路径
        return JsonResult.success(avatar);
    }

    @RequestMapping("/exit")
    public JsonResult<Void> exit(HttpSession session){
        session.removeAttribute("uid");
        session.removeAttribute("username");
        return JsonResult.success();
    }

}
