package com.ruanwenfu.test.aop.aspect.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface YMLog {
    // 注解属性
    String value() default "";
}
