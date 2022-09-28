package com.yh.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {


    /**
     * 检测全局session对象中是否有uid数据，如果有，则放行，没有重定向到登录页面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器
     * @return 如果返回为true，表示放行请求，如果为false，则表示拦截请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过HttpServletRequest获取session对象
        HttpSession session = request.getSession();
        Object uid = session.getAttribute("uid");
        if (uid == null){
            //说明用户没有登录，重定向到登录页面
            response.sendRedirect("/web/login.html");
            return false;
        }
        //请求放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
