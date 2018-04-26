package com.imooc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //控制器处理之前调用

        System.out.println("preHandle");

        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        httpServletRequest.setAttribute("startTime",new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        //控制器处理之后调用，如果controller层抛出异常，这个方法不会调用
        System.out.println("postHandle");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时："+(new Date().getTime() -start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
        //不管controller是正常运行，还是抛出异常，这个方法都会正常调用
        System.out.println("afterCompletion");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时："+(new Date().getTime() -start));

        /*
        当这里有异常的时候，还是返回空，是因为
        ControllerExceptionHandler已经把异常处理掉了

        当没有处理某类异常，会看到拦截器运行了两次
        第一次拦截的是UserController
        第二次拦截的是BasicErrorController
        拦截器会拦截所有的Controler，不管是不是自己写的
         */
        System.out.println("ex is "+e);
    }
}
