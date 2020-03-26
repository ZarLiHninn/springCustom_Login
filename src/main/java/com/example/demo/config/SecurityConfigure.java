package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;import com.example.demo.constants.RequestUrl;

@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(RequestUrl.LOGIN).permitAll()
		.antMatchers(RequestUrl.INDEX).hasAnyRole("USER","ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage(RequestUrl.LOGIN)
		.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl(RequestUrl.LOGIN)
		.and()
		.exceptionHandling().accessDeniedPage("/error")
		.and()
		.sessionManagement()
		.maximumSessions(1)
		.expiredUrl(RequestUrl.LOGIN)
		.maxSessionsPreventsLogin(true)
		.sessionRegistry(sessionRegistry());
	}
	@Bean
	public SessionRegistry sessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}

}
