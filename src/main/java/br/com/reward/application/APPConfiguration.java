package br.com.reward.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author filipe.pinheiro, 29/09/2018
 * 
 * All configurations go here
 */
@Configuration
public class APPConfiguration {
    
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
	}

}