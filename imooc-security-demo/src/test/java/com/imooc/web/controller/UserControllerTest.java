package com.imooc.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)//如何执行测试用例，会用一个SpringRunner来执行
@SpringBootTest
public class UserControllerTest {

    /*
    伪造mvc环境，不会真正启动tomcat
     */
    @Autowired
    private WebApplicationContext wac;//伪造web环境

    private MockMvc mockMvc;//伪造mvc环境

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * mockMvc.perform执行的意思
     * MockMvcRequestBuilders.get("/user")：模拟发出一个get请求，路径是/user
     * .param("username", "jojo")参数
     * contentType(MediaType.APPLICATION_JSON_UTF8)，描述请求
     * 执行完上述请求之后
     * 写一些期望andExpect(MockMvcResultMatchers.status().isOk())，判断服务是否成功，用状态码，isOk的状态吗就是200
     * 判断一下返回的json内容，期望是一个集合，长度为3 即结果是3元素，jsonpath就是解析返回的json内容，然后针对这个
     * 做一些判断
     * andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3)
     * 400的状态吗：请求格式错误
     * @throws Exception
     */
    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username","jojo")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    @Test
    public void whenQuery() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/userCondition")
                .param("username", "jojo")
                .param("age", "14")
                .param("size", "15")//每页15条
                .param("page", "3")//第三页
                .param("sort", "age,desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
