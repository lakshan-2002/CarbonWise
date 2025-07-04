package com.lakshan.carbonwise.config;

import com.lakshan.carbonwise.interceptor.AuthRequiredInterceptor;
import com.lakshan.carbonwise.resolver.CurrentUserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CurrentUserArgumentResolver currentUserArgumentResolver;
    private final AuthRequiredInterceptor authRequiredInterceptor;

    @Autowired
    public WebConfig(CurrentUserArgumentResolver currentUserArgumentResolver,
                     AuthRequiredInterceptor authRequiredInterceptor) {
        this.currentUserArgumentResolver = currentUserArgumentResolver;
        this.authRequiredInterceptor = authRequiredInterceptor;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authRequiredInterceptor);
    }
}
