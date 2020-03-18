package com.example.demo;





import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{

	
    
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(RequestUrl.SLASH).permitAll()
		.antMatchers(RequestUrl.SLASH).hasAnyRole("USER")
		
		.and()
		.formLogin()
		.loginPage(RequestUrl.LOGIN);
		
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
        .withUser("user").password(passwordEncoder().encode("pass")).roles("USER")
        .and()
        .withUser("admin").password(passwordEncoder().encode("adpass")).roles("USER");
        
	}

	
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addViewController(RequestUrl.LOGIN).setViewName(ViewPage.LOGIN);
		registry.addViewController(RequestUrl.SLASH).setViewName(ViewPage.INDEX);
	}



	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
   
}
