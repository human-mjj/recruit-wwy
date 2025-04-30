package com.example.recruit_page_wwy._core.config;

import com.example.recruit_page_wwy._core.intercetor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/user/**")
                .addPathPatterns("/mypage")
                .addPathPatterns("/mypage/**")
                .addPathPatterns("/match")
                .addPathPatterns("/match/**")
                .addPathPatterns("/board/**")
                // 예외
                .excludePathPatterns("/board")
                .excludePathPatterns("/board/{id:\\d+}")
        ;
    }
}
