package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;import com.example.demo.constant.RequestUrl;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers(
						RequestUrl.LOGIN + "/**"
						, RequestUrl.ERROR + "/**"
						, "/css/**"
						, "/js/**"
						, "/img/**"
				).permitAll()
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
				.exceptionHandling().accessDeniedPage("/error")
				.and()
				.sessionManagement()
				.maximumSessions(1)
				.expiredUrl(RequestUrl.LOGIN)
				.maxSessionsPreventsLogin(true)
				.sessionRegistry(sessionRegistry());
//
//		http.authorizeRequests()
//				.antMatchers(
//						RequestUrl.LOGIN + "/**"
//						, RequestUrl.ERROR + "/**"
//						, "/css/**"
//						, "/js/**"
//						, "/img/**"
//				).permitAll()
//				.antMatchers(RequestUrl.INDEX).hasAnyRole("ADMIN", "USER")
//				.antMatchers(RequestUrl.SLASH).hasAnyRole("ADMIN", "USER")
//				.anyRequest()
//				.authenticated();
//
//		http.formLogin()
//				.loginPage(RequestUrl.LOGIN)
//				.defaultSuccessUrl(RequestUrl.INDEX, true)
//				.usernameParameter("username")
//				.passwordParameter("password")
//				.failureHandler(customAuthenticationFailureHandler());
//
//		http.logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher(RequestUrl.LOGOUT))
//				.logoutSuccessUrl(RequestUrl.LOGIN)
//				.deleteCookies("JSESSIONID")
//				.invalidateHttpSession(true)
//				.permitAll();
//
//		http.sessionManagement()
//				.maximumSessions(1)
//				.maxSessionsPreventsLogin(true)
//				.expiredUrl(RequestUrl.LOGOUT);
//
//		http.headers().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

	@Bean
	public SessionRegistry sessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationFailureHandler customAuthenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

}
