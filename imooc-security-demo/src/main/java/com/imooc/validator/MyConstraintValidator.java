package com.imooc.validator;

import com.imooc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
第一个字段：对哪个注解进行逻辑判断
第二个字段：要判断的字段类型是什么
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {

    /*
    这个类可以注入spring的类
    @Autowired
    private ...
    虽然这个类有@Autowired，但是这个类不用@Component或者@Service来注解这个类
    Spring看到实现了这个接口会直接看成Spring的Bean的
     */
    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        /*
        这里写逻辑，怎么判断
         */

        return false;
    }
}
