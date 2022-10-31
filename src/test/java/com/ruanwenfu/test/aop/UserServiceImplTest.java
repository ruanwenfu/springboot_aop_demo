package com.ruanwenfu.test.aop;

import com.ruanwenfu.test.aop.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void eat(){
        userService.eat("晚餐");
        userService.out();
        userService.drink();
    }
}
