package bback.test.proviider.config;

import bback.test.proviider.interceptor.ServletInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
@Slf4j
public class ServletConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor servletInterceptor() {
        return new ServletInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(servletInterceptor());
    }
}
