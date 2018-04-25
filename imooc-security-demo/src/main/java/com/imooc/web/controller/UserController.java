package com.imooc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "",method = RequestMethod.GET)
    @JsonView(User.UserSimpleView.class)
    public List<User> query(@RequestParam(required = false,name = "username",value = "username",
                            defaultValue = "zhhangsan") String username) {
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

    @RequestMapping(value = "/ondition",method = RequestMethod.GET)
    @JsonView(User.UserSimpleView.class)
    public List<User> queryCondition(UserQueryCondition userQueryCondition,
                                     @PageableDefault(page = 2,size = 17,sort = "username") Pageable pageable) {

        System.out.println(ReflectionToStringBuilder.toString(userQueryCondition, ToStringStyle.MULTI_LINE_STYLE));

        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

//    @GetMapping("/user/{id:\\d+}")//只能接受数字
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable(name = "id",value = "id",required = false) String id) {

//		throw new UserNotExistException(id);

        System.out.println("进入getinfo");
        User user = new User();
        user.setUsername("tom");
        return user;
    }
}
