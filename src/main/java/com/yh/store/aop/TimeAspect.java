package com.yh.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component //将当前类的创建和维护交给Spring容器管理
@Aspect //将当前类标记为切面类
public class TimeAspect {


    @Around("execution(* com.yh.store.service.impl.*.*(..))")//返回类型 包 包下的类 包下的方法 参数
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        //记录开始时间
        Long statrt = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        //记录结束时间
        Long end = System.currentTimeMillis();
        System.out.println("耗时=" + (end - statrt));
        return proceed;
    }
}
