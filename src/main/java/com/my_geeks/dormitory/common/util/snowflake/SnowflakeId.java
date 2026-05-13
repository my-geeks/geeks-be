package com.my_geeks.dormitory.common.util.snowflake;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@IdGeneratorType(SnowflakeIdGenerator.class)
@Target({FIELD, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SnowflakeId {
}
