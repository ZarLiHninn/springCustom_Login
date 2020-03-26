package com.example.demo.controller;


import com.example.demo.Services.MyUserDetails;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;import com.example.demo.constants.RequestUrl;
import com.example.demo.constants.ViewName;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value=RequestUrl.LOGIN)
	public String showLoginForm(
			@AuthenticationPrincipal MyUserDetails user,
			@RequestAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false)AuthenticationException exception){
		if(user != null){
			return "redirect:" + RequestUrl.INDEX;
		}
		return ViewName.LOGIN;
	}

}
