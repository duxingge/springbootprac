package com.wjx.springbootprac.service;

import com.wjx.springbootprac.po.Apple;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppleServiceTest {

    @Autowired
    private AppleService appleService;


    @Test
    void getApple() {
        appleService.saveApple(new Apple(12,"green"));
        List<Apple> selected = appleService.getApple((Apple a) -> a.getColor().equals("green"));
        Assert.assertNotNull(selected);
    }

}