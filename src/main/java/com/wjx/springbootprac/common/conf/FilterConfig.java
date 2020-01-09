package com.wjx.springbootprac.common.conf;

import com.wjx.springbootprac.common.filter.MyHandleInterceptor1;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandleInterceptor1()).addPathPatterns("/**");
    }

}
