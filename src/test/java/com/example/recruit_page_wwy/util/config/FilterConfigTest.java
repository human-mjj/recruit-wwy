package com.example.recruit_page_wwy.util.config;

import com.example.recruit_page_wwy._core.filter.AuthorizationFilter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class FilterConfigTest {

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authFilter() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter());
        registrationBean.addUrlPatterns("/*"); // 전체 요청에 대해 필터 적용
        return registrationBean;
    }
}

