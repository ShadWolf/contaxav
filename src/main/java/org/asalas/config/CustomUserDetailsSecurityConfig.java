package org.asalas.config;

import org.asalas.services.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * 
 * Created by gkatzioura on 9/27/16.
 * 
 */

@Configuration
@EnableWebSecurity
public class CustomUserDetailsSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	 @Bean 
	 public UserDetailsService mongoUserDetails() { return new
	 CustomerUserDetailsService(); }
	
	/*@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// ALTHOUGH THIS SEEMS LIKE USELESS CODE,
		// ITS REQUIRED TO PREVENT SPRING BOOT AUTO-CONFIGURATION
		return super.authenticationManagerBean();
	}*/

	@Autowired
	public  void configAuthBuilder (AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("En configureGlobal!!!");
		UserDetailsService userDetailsService = mongoUserDetails();
		auth.userDetailsService(userDetailsService);
	}

	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
				.password("password").roles("ADMIN");
	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**", "/login", "/templates/**", "/webjars/**", "/vendor/**", "/css/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.successForwardUrl("/dashboard").permitAll().and().logout().permitAll();

		http.csrf().disable();
		http.headers().frameOptions().disable();

	}
}