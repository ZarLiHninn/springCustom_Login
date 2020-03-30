package com.example.demo.config;

import com.example.demo.handler.CustomAuthenticationFailureHandler;
import com.example.demo.handler.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;import com.example.demo.constant.RequestUrl;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers(
				RequestUrl.LOGIN + "/**"
				, RequestUrl.LOGOUT + "/**"
				, RequestUrl.ERROR + "/**"
				, "/css/**"
				, "/js/**"
				, "/img/**"
			).permitAll()
			.antMatchers(RequestUrl.SLASH).hasAnyRole("USER","ADMIN")
			.antMatchers(RequestUrl.HOME).hasAnyRole("USER", "ADMIN")
			.antMatchers(RequestUrl.ADMIN).hasAnyRole("ADMIN")
			.anyRequest()
			.authenticated();

		http.formLogin()
			.loginPage(RequestUrl.LOGIN)
			.defaultSuccessUrl(RequestUrl.HOME, true);

		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher(RequestUrl.LOGOUT))
			.logoutSuccessUrl(RequestUrl.LOGIN)
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.permitAll();

		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

		http.sessionManagement()
			.maximumSessions(1)
			.expiredUrl(RequestUrl.LOGIN)
			.maxSessionsPreventsLogin(true);

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

	@Bean
	public static ServletListenerRegistrationBean httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
		return new MyAccessDeniedHandler();
	}

}
