package com.imooc.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名为 {}",username);
        /*
        根据用户名查找用户信息，都是需要从数据库中得到的，返回security的User对象，有用户名，密码，权限
        AuthorityUtils.commaSeparatedStringToAuthorityList("admin")，是将字符串转换成权限对象

         */
//        return new User(username,"123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        /*
        根据查到的用户信息判断用户是否被冻结,在实际项目中，不一定要返回User，可以自定义，只需要实现UserDetails接口就可以了
         */
//        return new User(username,"123456",true,true,true,false,
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));


        /*
        密码加密
        实现PasswordEncoder接口，encode方法是用来加密的，match方法是用来判断数据库的和前端的是否匹配的
        往数据库插数据的时候，要用encode方法加密
        matches方法是spring自己调用的，登录过程中拿到UserDetails后它会去拿password，和登录的password作一个比对
        从数据库返回的就是一个加密了的密码
         */


        return new User(username,passwordEncoder.encode("123456"),true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }
}
