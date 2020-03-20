package com.example.demo;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class LoginController {
	@GetMapping(value=RequestUrl.LOGIN)
   public String showLoginForm(@AuthenticationPrincipal User user){
		while(user != null){
			return "redirect:/";
		}
	   return ViewName.LOGIN;
   }
	
   
   
   
   
}
