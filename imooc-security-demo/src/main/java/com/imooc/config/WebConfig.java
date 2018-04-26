package com.imooc.config;

import com.imooc.filter.TimeFilter;
import com.imooc.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration//告诉spring这是一个配置类
public class WebConfig extends WebMvcConfigurerAdapter {

    //注册bean
    /*
    这一段代码实际上和传统的web.xml中配置过滤器的作用是一样的
     */
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        /*
        设置对哪些url起作用
         */
        List<String> url = new ArrayList<>();
        url.add("/*");
        registrationBean.setUrlPatterns(url);

        return registrationBean;
    }



    /*
    interceptor光加@Component注解是不行的，为了注入interceptor，将此类继承WebMvcConfigurerAdapter
    并且覆盖addInterceptors方法
    因为TimeInterceptor类已经注解了@Component声明成了spring的组件了
    所以这里可以@Autowired
     */

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
        super.addInterceptors(registry);
    }
}
