package com.nt.rookie.post.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nt.rookie.post.service.impl.UserDetailsServiceImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenBuilder) throws Exception{
		authenBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
					.csrf().disable()
					.authorizeRequests()
					.antMatchers("/resources/**").permitAll()
					.antMatchers("/home", "/login", "/registration", "/logout","/postdetails").permitAll()
					.antMatchers("/postedit**").hasRole("AUTHOR")
					.antMatchers("/createpost").hasRole("AUTHOR")
					.antMatchers("**/admin/**").hasRole("ADMIN")
					.and().exceptionHandling().accessDeniedPage("/403")
					.and().formLogin()
									.loginPage("/login")
									.defaultSuccessUrl("/home")
									;
		httpSecurity.sessionManagement().maximumSessions(1).expiredUrl("/home");
	}
	
}
