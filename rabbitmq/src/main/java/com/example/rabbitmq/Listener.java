package com.example.rabbitmq;

import com.example.rabbitmq.service.AccountService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Author wangjiaxing
 * @Date 2021/5/30
 */
@Component
public class Listener {
    @Autowired
    private AccountService accountService;
    @RabbitListener(queues = {"account.add"})
    public void onMsg(String s) {
        System.out.println("receive msg:" + s);

        accountService.add(1,new BigDecimal(20.00));
    }
}
