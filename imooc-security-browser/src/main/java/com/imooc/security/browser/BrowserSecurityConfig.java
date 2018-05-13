package com.imooc.security.browser;

import com.imooc.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    配置类
     */
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        /*
        这里可以返回security自带的，也可以返回自定义的去实现PasswordEncoder接口，然后重写encode方法即可
         */
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
        http.formLogin() //使用表单登录
//                http.httpBasic()  //使用springsecurity默认的登录
                .and()
                .authorizeRequests() //下面这些都是授权的配置
                .anyRequest()  //任何请求
                .authenticated();  //都需要身份认证
    }
  */

/*
        http.formLogin() //使用表单登录,自定义登录表单访问路径，自定义登录html
                .loginPage("/imooc-signIn.html")
                .loginProcessingUrl("/authentication/form") //这个设置主要是因为，security默认的登录是/login,要让自己的表单路径走登录流程，要在这里配置
                .and()
                .authorizeRequests() //下面这些都是授权的配置
                .antMatchers("/imooc-signIn.html").permitAll()  //当访问这个url时候，不需要身份认证，就可以访问
                .anyRequest()  //任何请求
                .authenticated()//都需要身份认证
                .and()
                .csrf().disable();  //关闭csrf的防护
        }
*/

        http.formLogin() //使用表单登录
                .loginPage("/authentication/require") //为了处理不同前端传来的，所以这里不写死页面，由controller去处理，这里去访问controller路径
                .loginProcessingUrl("/authentication/form") //这个设置主要是因为，security默认的登录是/login,要让自己的表单路径走登录流程，要在这里配置
                .and()
                .authorizeRequests() //下面这些都是授权的配置
                .antMatchers("/authentication/require",
                            securityProperties.getBrowser().getLoginPage()) //配置用户自定义的登录页面也放行
                            .permitAll()  //当访问这个url时候，不需要身份认证，就可以访问
                .anyRequest()  //任何请求
                .authenticated()//都需要身份认证
                .and()
                .csrf().disable();  //关闭csrf的防护
}
}
