package com.yh.store.utils;

import javax.servlet.http.HttpSession;

public class GetFromSession {
    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录用户的uid值
     */
    public static final Integer getUidFromSession(HttpSession session){
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        return uid;
    }

    /**
     * 获取当前登录用户的用户名
     * @param session session对象
     * @return 当前登录用户的用户名
     */
    public static final String getUsernameFromSession(HttpSession session){
        String username = session.getAttribute("username").toString();
        return username;
    }
}
