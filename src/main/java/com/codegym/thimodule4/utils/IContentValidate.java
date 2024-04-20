package com.codegym.thimodule4.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContentValidate.class)
//Nếu muốn tạo annotation cho class thì dùng type
//Tạo annotation cho thuộc tính thì dùng field
//Tạo annotation cho phương thức thì dùng method
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IContentValidate {
    String message() default "Tên sai định dạng";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
