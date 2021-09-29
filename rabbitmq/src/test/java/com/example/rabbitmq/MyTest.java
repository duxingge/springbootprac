package com.example.rabbitmq;

import com.example.rabbitmq.service.AccountService;
import com.example.rabbitmq.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void testjosn() {
        accountService.add(1,new BigDecimal(20.00));
        assert 1==1;
    }
}
