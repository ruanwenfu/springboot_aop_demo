package com.ruanwenfu.test.aop.aspect;

import com.ruanwenfu.test.aop.aspect.annotation.YMLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

@Component
@Aspect
public class YMLogAspect {
    private final static Logger log = LoggerFactory.getLogger(YMLogAspect.class);

    // 定义切入点
    @Pointcut("@annotation(com.ruanwenfu.test.aop.aspect.annotation.YMLog)")
    private void pointcut() {}

    // 定义方法增强
    @Before("pointcut() && @annotation(logger)")  // 切入点匹配注解 并且 将注解注入变量
    public void advice(JoinPoint joinPoint, YMLog logger){
        log.info("注解作用的方法名: " + joinPoint.getSignature().getName());

        log.info("所在类的简单类名: " +
                joinPoint.getSignature().getDeclaringType().getSimpleName());

        log.info("所在类的完整类名: " +
                joinPoint.getSignature().getDeclaringType());

        log.info("目标方法的声明类型: " + Modifier.toString(joinPoint.getSignature().getModifiers()));

        log.info("--- 日志的内容为" + logger.value() + " ---");
    }

    // 还可以省略pointcut()方法
    // @Before(value="@annotation(logger)")
    public void advice1(JoinPoint joinPoint,YMLog logger) {
        log.info("注解作用的方法名: " + joinPoint.getSignature().getName());

        log.info("所在类的简单类名: " +
                joinPoint.getSignature().getDeclaringType().getSimpleName());

        log.info("所在类的完整类名: " +
                joinPoint.getSignature().getDeclaringType());

        log.info("目标方法的声明类型: " +
                Modifier.toString(joinPoint.getSignature().getModifiers()));

        log.info("--- 日志的内容为" + logger.value() + " ---");
    }

}
