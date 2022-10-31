package com.ruanwenfu.test.aop.service;

import org.springframework.stereotype.Service;

/**
 * 雇主类
 */

@Service
public class UserServiceImpl {

    public void eat(String meals){
        System.out.println("主任吃饭"+meals);
    }

    public void out(){
        System.out.println("主人外出");
    }

    public void drink(){
        System.out.println("取酒杯");
        // int i = 1/0;
        System.out.println("主人喝酒");
    }
}
