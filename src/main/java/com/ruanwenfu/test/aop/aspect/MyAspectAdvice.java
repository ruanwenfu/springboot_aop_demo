package com.ruanwenfu.test.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(1)
public class MyAspectAdvice {
    private static final Logger log = LoggerFactory.getLogger(MyAspectAdvice.class);

    // 定义切入点 是一个注解
    @Pointcut("@annotation(com.ruanwenfu.test.aop.aspect.annotation.MyAspectAnnotation)")
    private void advice(){

    }

    // 配置环绕切面
    @Around(value = "advice()")
    public Object adviceCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取请求头
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("token");
        // 业务逻辑判断
        if(!"admin".equals(token)){
            return "not admin user";
        }
        // 如果通过上面的测试条件，才开始执行控制器的方法
        return joinPoint.proceed();
    }
}
