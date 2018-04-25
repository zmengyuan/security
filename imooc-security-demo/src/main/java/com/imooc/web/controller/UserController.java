package com.imooc.web.controller;

import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<User> query(@RequestParam(required = false,name = "username",value = "username",
                            defaultValue = "zhhangsan") String username) {
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

    @RequestMapping(value = "/userCondition",method = RequestMethod.GET)
    public List<User> queryCondition(UserQueryCondition userQueryCondition,
                                     @PageableDefault(page = 2,size = 17,sort = "username") Pageable pageable) {

        System.out.println(ReflectionToStringBuilder.toString(userQueryCondition, ToStringStyle.MULTI_LINE_STYLE));

        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }
}
