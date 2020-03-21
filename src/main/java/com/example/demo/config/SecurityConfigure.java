package com.example.demo.config;



import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.example.demo.constants.RequestUrl;






@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(RequestUrl.SLASH).permitAll()
		.antMatchers(RequestUrl.SLASH).hasAnyRole("USER","ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage(RequestUrl.LOGIN)
		.permitAll()
		.defaultSuccessUrl(RequestUrl.INDEX)
		.and()
		.logout()
		.logoutSuccessUrl(RequestUrl.LOGIN)
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

	@Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
        .withUser("user").password(passwordEncoder().encode("pass")).roles("USER")
        .and()
        .withUser("admin").password(passwordEncoder().encode("adpass")).roles("ADMIN");
        
	}

	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
   
}
