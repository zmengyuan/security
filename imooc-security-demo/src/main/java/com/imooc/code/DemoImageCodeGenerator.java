package com.imooc.code;

import com.imooc.security.core.validate.code.ImageCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/*
自定义图形验证码逻辑
 */
@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode createImageCode(HttpServletRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }
}
