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
        System.out.println(-1 << Integer.SIZE);
        System.out.println(1 << Integer.SIZE);
        System.out.println(2 << Integer.SIZE);
        System.out.println(3 << Integer.SIZE);
        System.out.println(4 << Integer.SIZE);

    }




    public static void main(String[] args) {
        int count = Integer.SIZE - 3;
        System.out.println(Integer.toBinaryString(-1 << count));
        System.out.println(Integer.toBinaryString(1 << count));
        System.out.println(Integer.toBinaryString(2 << count));
        System.out.println(Integer.toBinaryString(3 << count));
        System.out.println(Integer.toBinaryString(4 << count));
    }
}
