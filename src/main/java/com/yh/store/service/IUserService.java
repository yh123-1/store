package com.yh.store.service;

import com.yh.store.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 用户模块业务层接口
 */
public interface IUserService {

    /**
     * 用户注册方法
     * @param user 用户的数据对象
     */
    void insert(User user);

    /**
     * 用户登录功能
     * @param username 用户名
     * @param password 用户密码
     * @return 当前匹配的用户数据，没有则返回null
     */
    User login(String username,String password);

    /**
     * 修改密码功能
     * @param uid 用户id
     * @param username 用户名
     * @param oldPassword 原始密码
     * @param newPassword 新密码
     */
    void changePassword(Integer uid,String username,String oldPassword,String newPassword);

    /**
     * 根据用户uid查询用户数据,回显给前端
     * @param uid 用户id
     * @return 用户的数据
     */
    User getInfoByUid(Integer uid);

    /**
     * 修改用户信息
     * @param uid 用户id
     * @param user 用户修改后的信息
     */
    void changeInfo(Integer uid,User user);

    /**
     * 修改用户头像
     * @param session
     * @param file
     * @return 头像的绝对路径
     */
    String changeAvatar(HttpSession session, MultipartFile file);
}
