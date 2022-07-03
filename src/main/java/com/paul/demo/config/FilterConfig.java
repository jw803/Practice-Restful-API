package com.paul.demo.config;

import com.paul.demo.filter.LogApiFilter;
import com.paul.demo.filter.LogProcessTimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean logAPIFIlter() {
        FilterRegistrationBean<LogApiFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LogApiFilter());
        bean.addUrlPatterns("/*");
        bean.setName("logApiFilter");
        bean.setOrder(0);

        return bean;
    }
}
