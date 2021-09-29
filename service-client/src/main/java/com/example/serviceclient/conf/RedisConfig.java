package com.example.serviceclient.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @Author wangjiaxing
 * @Date 2021/9/29
 */
@Configuration
public class RedisConfig {

    @Bean
    RedisTemplate redisTemplate() {
        return new RedisTemplate();
    }

    @Bean(name = "rateLimitScript")
    RedisScript rateLimitScript() {
        DefaultRedisScript<Boolean> rateLimitScript = new DefaultRedisScript<>();
        PathMatchingResourcePatternResolver matcher = new PathMatchingResourcePatternResolver();
        rateLimitScript.setScriptSource(new ResourceScriptSource(matcher.getResource("/lua/")));
        return rateLimitScript;
    }

}
