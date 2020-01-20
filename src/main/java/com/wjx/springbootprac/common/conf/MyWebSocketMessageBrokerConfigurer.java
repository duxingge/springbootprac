package com.wjx.springbootprac.common.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class MyWebSocketMessageBrokerConfigurer implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //定义消息代理前缀，转发给订阅这些前缀的用户
        registry.enableSimpleBroker("/topic");
        //过滤出需要被注解@MessageMapping处理的消息
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //增加stomp端口
        //withSockJs解决浏览器对WebScoket支持的差异
        registry.addEndpoint("/chat").withSockJS();
    }
}
