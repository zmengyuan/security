package com.imooc.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder() {
        /*
        这里可以返回security自带的，也可以返回自定义的去实现PasswordEncoder接口，然后重写encode方法即可
         */
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin() //使用表单登录
//                http.httpBasic()  //使用springsecurity默认的登录
                .and()
                .authorizeRequests() //下面这些都是授权的配置
                .anyRequest()  //任何请求
                .authenticated();  //都需要身份认证
    }
}
