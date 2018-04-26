package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class TimeAspect {

    /*
     * ProceedingJoinPoint这个对象包含拦截的信息（就像拦截器里面每个方法都有handler参数一样）
     * 执行 任何返回值 类：任何方法 任何参数
     */
    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time start");

        Object[] args = pjp.getArgs();

        for (Object object : args) {
            System.out.println("参数是"+ object);
        }

        Long start = new Date().getTime();

        Object object = pjp.proceed();//这个方法就和在Filter类中dofilter方法一样，返回值就是调用哪个方法的返回值

        System.out.println("耗时:  "+(new Date().getTime()-start));

        System.out.println("time end");

        return object;
    }
}
