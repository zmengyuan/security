package com.imooc.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =MyConstraintValidator.class )
public @interface MyConstraint {
    /*
    验证注解必须有这三个属性
    下面两个是hibernate相关的
     */
    String message() ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
