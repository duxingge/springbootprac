package com.example.serviceclient.conf;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wangjiaxing
 * @Date 2021/12/2
 */
@Configuration
public class PostProcessConfig {

    @Bean
    BeanPostProcessor beanPostProcessor() {
        return new FeignPostProcess();
    }
}
