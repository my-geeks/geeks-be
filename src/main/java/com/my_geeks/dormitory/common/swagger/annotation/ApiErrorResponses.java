package com.my_geeks.dormitory.common.swagger.annotation;

import com.my_geeks.dormitory.common.exception.errorcode.ErrorCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorResponses {
    ErrorCode[] value();
}
