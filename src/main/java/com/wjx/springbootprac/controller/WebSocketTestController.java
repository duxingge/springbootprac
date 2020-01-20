package com.wjx.springbootprac.controller;

import lombok.Data;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketTestController {

    @MessageMapping("/greetings")
    @SendTo("/topic/hello")
    public Object Hello(Message message) {
        return message;
    }
    @Data
    class Message{
        private String name;
        private String content;

    }

}
