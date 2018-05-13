package com.imooc.security.browser;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.formLogin() //使用表单登录
                http.httpBasic()  //使用springsecurity默认的登录
                .and()
                .authorizeRequests() //下面这些都是授权的配置
                .anyRequest()  //任何请求
                .authenticated();  //都需要身份认证
    }
}
