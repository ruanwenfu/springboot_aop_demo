package com.ruanwenfu.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 切入点(pointCut)： 实际增强的地方
// 增强 给目标方法添加的额外操作
// 切面 指切面类
// 连接点（JoinPoint) 可以增强的地方（方法的前后某个阶段）
@Component
@Aspect // 切面注释
@Order(2)
public class AspectTest {

    // 定义切入点
    @Pointcut("execution(* com.ruanwenfu.test.aop.service.UserServiceImpl.eat(*))")
    private void Myeat(){};

    // 前置通知 value用于匹配规则，也就是执行切入点  这里用于匹配方法
    // @Before(value = "execution(* com.ruanwenfu.test.aop.service.UserServiceImpl.eat(*))")
    @Before(value = "Myeat()")  // 直接引用切入点
    public void before(JoinPoint joinPoint){
        System.out.println("当前方法名"+joinPoint.getSignature().getName());
        System.out.println("当前执行方法参数"+joinPoint.getArgs()[0]);
        System.out.println("目标对象target"+joinPoint.getTarget());
        System.out.println("前置增强——保姆做饭");
    }

    // @After(value = "execution(* com.ruanwenfu.test.aop.service.UserServiceImpl.eat(*))")
    @After(value = "Myeat()")
    public void after(JoinPoint joinPoint){
        System.out.println("当前方法名"+joinPoint.getSignature().getName());
        System.out.println("当前执行方法参数"+joinPoint.getArgs()[0]);
        System.out.println("目标对象target"+joinPoint.getTarget());
        System.out.println("后置增强——保姆洗碗");
    }

    // 环绕增强
    @Around(value="execution(* com.ruanwenfu.test.aop.service.UserServiceImpl.out())")  // 切入点 注意方法没有参数，所以不要写out(*)
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("当前执行的方法名"+joinPoint.getSignature().getName());
        System.out.println("目标对象target"+joinPoint.getTarget());
        System.out.println("环绕增强——保安开门"); // 目标方法执行前执行
        Object proceed = joinPoint.proceed(); // 执行的目标方法
        System.out.println("环绕挣钱——保安关门"); // 目标方法执行后
        return proceed; // 返回目标的执行方法 这里可以改变返回值
    }

    // 异常通知 对异常进行拦截
    // AfterThrowing匹配类的时候要匹配实现类，匹配接口是无效的
    @AfterThrowing(value = "within(com.ruanwenfu.test.aop.service.UserServiceImpl)",throwing = "e")
    public void ex(JoinPoint joinPoint,Exception e){
        System.out.println("出错了"+e.getMessage());
    }

    // 返回通知 方法正常返回则生效
    @AfterReturning(value = "within(com.ruanwenfu.test.aop.service.UserServiceImpl)",returning = "result")
    public void ex(JoinPoint joinPoint,Object result){
        System.out.println(joinPoint.getSignature().getName()+"返回通知生效");
        System.out.println("返回了"+result);
    }
}
