package com.ugisoftware.hotelmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.BeanIds;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ugisoftware.hotelmanagement.security.jwt.JWTAuthenticationEntryPoint;
import com.ugisoftware.hotelmanagement.security.jwt.JWTAuthenticationFilter;
import com.ugisoftware.hotelmanagement.security.service.UserDetailsServiceImp;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
private UserDetailsServiceImp userDetailsService;
private JWTAuthenticationEntryPoint handler;
public SecurityConfig(UserDetailsServiceImp userDetailsService, JWTAuthenticationEntryPoint handler) {

	this.userDetailsService = userDetailsService;
	this.handler = handler;
}

 
@Bean
public JWTAuthenticationFilter jwtAuthenticationFilter() {
	return new JWTAuthenticationFilter();
}

@Bean(BeanIds.AUTHENTICATION_MANAGER)
@Override
public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
}

@Bean
public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

@Override
public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
}

@Bean
public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("HEAD");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("DELETE");
    config.addAllowedMethod("PATCH");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
}

@Override
public void configure(HttpSecurity httpSecurity) throws Exception {
	httpSecurity
		.cors().and().csrf().disable();
	//	.exceptionHandling().authenticationEntryPoint(handler).and()
	//	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	//	.authorizeRequests()
	//	.antMatchers(HttpMethod.GET, "/emloyee").permitAll()
	//	.antMatchers(HttpMethod.GET, "/room").permitAll()
	//	.antMatchers("/auth/**").permitAll()
	//	.anyRequest().authenticated();
		
	httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
}
}
