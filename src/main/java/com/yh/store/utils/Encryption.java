package com.yh.store.utils;

import org.springframework.util.DigestUtils;

/**
 * 加密算法工具类
 */
public class Encryption {

    /**
     *
     * MD5加密算法3次
     * @param password 密码
     * @param salt 盐值
     * @return
     */
    public static String getMD5Password(String password,String salt){
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
