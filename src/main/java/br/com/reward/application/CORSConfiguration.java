package br.com.reward.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author filipe.pinheiro, 06/01/2020
 * 
 *         All configurations go here
 */

//  @Configuration
//  @EnableWebMvc
public class CORSConfiguration implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("OPTIONS")
            .allowedOrigins("*")
            .allowedHeaders("*");
    }
}