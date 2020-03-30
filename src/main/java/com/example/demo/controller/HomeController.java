package com.example.demo.controller;

import com.example.demo.constant.ViewAttribute;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.constant.RequestUrl;
import com.example.demo.constant.ViewName;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = {RequestUrl.HOME, RequestUrl.SLASH})
	public String home(@AuthenticationPrincipal User user, Model model){
		model.addAttribute(ViewAttribute.USERNAME, user.getUsername());
		return ViewName.HOME;
	}

	@GetMapping(RequestUrl.ADMIN)
	public String admin(@AuthenticationPrincipal User user, Model model){
		model.addAttribute(ViewAttribute.USERNAME, user.getUsername());
		return ViewName.ADMIN;
	}
}
