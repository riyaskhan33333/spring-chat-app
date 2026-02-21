package com.livechat.chat.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.livechat.chat.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityCongfiguration{ 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http.authorizeHttpRequests(authz->authz
				.requestMatchers("/ws/**").authenticated()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/register").permitAll()
				.requestMatchers("/users/**").permitAll()
				.requestMatchers("/home").hasRole("USER")
				.anyRequest().permitAll())
		.formLogin(form->form.loginPage("/login").defaultSuccessUrl("/home"));
	            
		return http.build();
	}
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
	      DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider(userDetailsService());
	      authProvider.setPasswordEncoder(passwordEncoder());
	      return authProvider;
	}
	
	@Bean 
	public AuthenticationManager authenticationManager() {
		ProviderManager authManager=new ProviderManager(daoAuthenticationProvider());
		return authManager;
	}
	
	
	


	

}
