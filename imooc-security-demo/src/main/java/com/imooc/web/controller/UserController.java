package com.imooc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import com.imooc.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @RequestMapping(value = "/condition",method = RequestMethod.GET)
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
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
    public User getInfo(@PathVariable(name = "id",value = "id",required = false) @ApiParam(value = "用户id") String id) {

//        throw new RuntimeException("user not exist");
//        throw  new UserNotExistException(id);


        System.out.println("进入getinfo服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {

		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
//			errors.getAllErrors().stream().forEach(error -> {
//				String message = ((FieldError) error).getField() + error.getDefaultMessage();
//				System.out.println(message);
//			});
        }
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }


    /*
    me1和me2是一样的返回，
     */
    @GetMapping(value = "/me1")
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping(value = "/me2")
    public Object getCurrentUser(Authentication authentication) {
        return authentication;
    }

    /*
    如果不想返回所有信息，可以只返回userDeatils，实际上就是authentication里面的principal
     */
    @GetMapping(value = "/me3")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }


}
