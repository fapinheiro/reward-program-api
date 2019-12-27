package br.com.reward.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

// If the filter should be applied in all requests,
// add the annotation @Component and comment @Bean in APPConfiguration
// TODO @Component
public class BasicFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
                
        // TODO Auto-generated method stub
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getRequestURI().contains("postal-codes/test")) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        }
        
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
 
    

}