package com.example.serviceclient.conf;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

/**
 * @Author wangjiaxing
 * @Date 2021/12/2
 */
@Component
public class FeignPostProcess implements InstantiationAwareBeanPostProcessor {
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if(bean instanceof RedisScript) {
            System.out.println("【postProcessAfterInstantiation】"+ beanName);
        }
        return true;
    }

}
