package com.example.rabbitmq.config;

import com.example.rabbitmq.util.JsonUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    RabbitTemplate getRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        //消息转换器（Object转化为josn，防止有未序列化的对象）
        rabbitTemplate.setMessageConverter(new SimpleMessageConverter(){
            @Override
            protected Message createMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
                String stringMsg = JsonUtil.toString(object);
                return super.createMessage(stringMsg, messageProperties);
            }
        });
        return rabbitTemplate;
    }


}
