package com.example.demo.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;import com.example.demo.constants.RequestUrl;
import com.example.demo.constants.ViewName;

@Controller
public class LoginController {
	@GetMapping(value=RequestUrl.LOGIN)
	public String showLoginForm(@AuthenticationPrincipal User user){
		while(user != null){
			return "redirect:/";
		}
		return ViewName.LOGIN;
	}

}