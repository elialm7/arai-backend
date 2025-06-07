package org.arai.DConfiguration;

import org.arai.Security.JwtAuthFilter;
import org.arai.Annotations.JwtClaimsArgumentResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class JwtAuthConfig implements WebMvcConfigurer {

    private final JwtAuthFilter jwtAuthFilter;
    private final JwtClaimsArgumentResolver jwtClaimsArgumentResolver;

    public JwtAuthConfig(JwtAuthFilter jwtAuthFilter, JwtClaimsArgumentResolver jwtClaimsArgumentResolver) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.jwtClaimsArgumentResolver = jwtClaimsArgumentResolver;
    }

    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtFilter(){
        FilterRegistrationBean<JwtAuthFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(jwtAuthFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(jwtClaimsArgumentResolver);
    }
}
