package com.codegym.thimodule4.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ContentValidate implements ConstraintValidator<IContentValidate, String> {
    @Override
    public void initialize(IContentValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
//        value ở đây là các trường cso kiểu dữ liêụ String--> khi mình bỏ annotion @tenInterface vào thì bắt lỗi trường đó
        return value.matches("^[\\p{L}0-9\\s]*$");
    }
}
