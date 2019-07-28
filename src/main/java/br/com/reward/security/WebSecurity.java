package br.com.reward.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	// @Autowired
	private UserDetailsService userDetailsService;

	// @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JWTAuthenticationFilter jwtAuth = new JWTAuthenticationFilter(authenticationManager());
		jwtAuth.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);

		// Permit all only in login urls
		http.exceptionHandling().accessDeniedHandler(myAccessHandler()).and()
			.exceptionHandling().authenticationEntryPoint(myAccessHandler()).and()
			.headers().frameOptions().disable().and() // Disable header X-Frame-Options
			.cors().and().csrf().disable() // Interesting, the attacker makes the client trigger the request he wants to
			.authorizeRequests().antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
			.antMatchers(HttpMethod.POST, SecurityConstants.LOGIN_URL).permitAll()
			.antMatchers(HttpMethod.GET, SecurityConstants.POSTAL_CODES_URL).permitAll()
			.antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_URL).permitAll()
			.antMatchers("*", "/h2-console/*").permitAll()
			.antMatchers("*", "/favicon.ico").permitAll()
			.anyRequest().authenticated().and()
			.addFilter(jwtAuth)
			.addFilter(new JWTAuthorizationFilter(authenticationManager()))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // this disables session creation on Spring Security
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		// Enable cross-orign from any origin
		// Check annotation @CrossOrigin
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
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
