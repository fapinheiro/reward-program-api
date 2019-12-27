package br.com.reward.application;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.reward.security.BasicFilter;

/**
 * @author filipe.pinheiro, 29/09/2018
 * 
 *         All configurations go here
 */
@Configuration
public class APPConfiguration {

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Remove @Component from de FilterBean
    @Bean
    public FilterRegistrationBean<BasicFilter> loggingFilter() {
        FilterRegistrationBean<BasicFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new BasicFilter());
        registrationBean.addUrlPatterns("/api/v1/postal-codes/test");

        return registrationBean;
    }
}