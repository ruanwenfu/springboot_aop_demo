package com.ruanwenfu.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;
@Component
@Aspect
@Order(1)
public class AspectTest2 {
    @Before(value = "execution(* com.ruanwenfu.test.aop.service.UserServiceImpl.eat(*))")
    public void before1(JoinPoint joinPoint){
        System.out.println("前置增强——门卫开门");
    }

    @After(value = "execution(* com.ruanwenfu.test.aop.service.UserServiceImpl.eat(*))")
    public void after1(JoinPoint joinPoint){
        System.out.println("后置增强——门卫开门");
    }
}
