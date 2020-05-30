package br.com.reward.security;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.reward.handler.MyAccessHandler;
import br.com.reward.util.HTTPUtil;
import br.com.reward.util.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

	// @Autowired
	// private Environment env;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private HTTPUtil httpUtil;

	// public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
	// 	this.userDetailsService = userDetailsService;
	// 	this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	// }

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		JWTAuthenticationFilter jwtAuth = new JWTAuthenticationFilter(authenticationManager(), jwtUtil);
		jwtAuth.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);

		http.exceptionHandling().accessDeniedHandler(myAccessHandler()).and()
			.exceptionHandling().authenticationEntryPoint(myAccessHandler()).and()
			.headers().frameOptions().disable().and() // Disable header X-Frame-Options, Sites can use this to avoid clickjacking attacks, by ensuring that their content is not embedded into other sites in an iframe content.
			.cors().and().csrf().disable()
			// .cors().and().csrf().disable() // Interesting, the attacker makes the client trigger the request he wants to
			// .cors().and().csrf().and()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
			.antMatchers(HttpMethod.POST, SecurityConstants.LOGIN_URL).permitAll()
			.antMatchers(HttpMethod.GET, SecurityConstants.POSTAL_CODES_URL).permitAll()
			.antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_URL).permitAll()
			// .antMatchers(HttpMethod.GET, SecurityConstants.INDICATIONS_URL).permitAll()
			.antMatchers("*", SecurityConstants.H2_URL).permitAll()
			.antMatchers("*", "/favicon.ico").permitAll()
			.anyRequest().authenticated().and()
			.addFilter(jwtAuth)
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, httpUtil, userDetailsService))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // this disables session creation on Spring Security

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		// Enable default cross-orign from any origin
		// Check annotation @CrossOrigin
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowCredentials(true);
		configuration.setAllowedMethods(
			Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedOrigins(
			Arrays.asList("http://localhost:4200", "http://192.168.99.100"));
		configuration.setAllowedHeaders(
			Arrays.asList("location", "authorization", "content-type", "access-control-request-headers", 
				"access-control-request-method", "accept", "origin", "x-requested-with", "remember-me" ));
		
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
	}
	
	@Bean
	public MyAccessHandler myAccessHandler() {
		return new MyAccessHandler();
	}

	// @Bean
	// public JWTAuthenticationFilter getJWTAuthenticationFilter() throws Exception
	// {
	// final JWTAuthenticationFilter filter = new
	// JWTAuthenticationFilter(authenticationManager());
	// filter.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);
	// return filter;
	// }
}
