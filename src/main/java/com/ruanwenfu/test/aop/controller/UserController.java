package com.ruanwenfu.test.aop.controller;

import com.ruanwenfu.test.aop.aspect.annotation.MyAspectAnnotation;
import com.ruanwenfu.test.aop.aspect.annotation.YMLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/test")
    public Object test(){
        return "hello world";
    }

    @GetMapping("/annotation")
    @MyAspectAnnotation
    public String annotationTest(@RequestHeader("token") String token){
        return "登录用户为"+token;
    }

    @YMLog("这是自定义日志切入")
    @GetMapping("/log")
    public String testlog(){
        return "打印了日志";
    }
}
