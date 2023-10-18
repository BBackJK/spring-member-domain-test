package bback.test.proviider.config;

import bback.test.proviider.interceptor.CustomLocaleChangeInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class I18nConfig implements WebMvcConfigurer {

    public static final String I18N_COOKIE_VALUE = "lang";

    @Bean("localeResolver")
    public LocaleResolver cookieLocaleResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.KOREA);
        resolver.setCookieName(I18N_COOKIE_VALUE);
        return resolver;
    }

    @Bean
    public HandlerInterceptor localeChangeInterceptor() {
        return new CustomLocaleChangeInterceptor();
    }

    @Bean
    public MessageSource messageSource() {
        AbstractResourceBasedMessageSource resourceLoaderAware = new ReloadableResourceBundleMessageSource();
        resourceLoaderAware.setBasename("classpath:/i18n/messages");
        resourceLoaderAware.setDefaultEncoding("UTF-8");
        return resourceLoaderAware;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/res/**")
                .excludePathPatterns("/files/**")
                .excludePathPatterns("/cms/**")
                .excludePathPatterns("/.well-known/**")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/sitemap.xml")
                ;
    }
}
