package com.yh.store.mapper;

import com.yh.store.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


/**
 * 用户功能模块的持久层接口
 */
public interface UserMapper {

    /**
     * 添加用户
     * @param user 用户的数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 如果找到返回用户对象，没有则返回null
     */
    User selectByUserName(String username);

    /**
     * 根据用户uid来修改密码
     * @param uid 用户的id
     * @param password 用户修改的新密码
     * @param modifiedUser 修改的执行者
     * @param modifiedTime 修改的时间
     * @return 返回值受影响的行数
     */
    Integer updatePasswordByUid(@Param("uid") Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户的uid查询用户
     * @param uid 用户的id
     * @return 找到了则返回对象，否则返回null
     */
    User selectByUid(Integer uid);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 受影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据用户id修改用户头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}
