package br.com.reward.application;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.reward.security.CORSFilter;

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
    // @Bean
    // public FilterRegistrationBean<CORSFilter> loggingFilter() {
    //     FilterRegistrationBean<CORSFilter> registrationBean = new FilterRegistrationBean<>();

    //     registrationBean.setFilter(new CORSFilter());
    //     registrationBean.addUrlPatterns("/api/v1/*");

    //     return registrationBean;
    // }
}