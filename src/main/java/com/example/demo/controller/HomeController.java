package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.constant.RequestUrl;
import com.example.demo.constant.ViewAttribute;
import com.example.demo.constant.ViewName;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = {RequestUrl.INDEX, RequestUrl.SLASH})
	public String showIndexPage(@AuthenticationPrincipal User user, Model model){
		model.addAttribute(ViewAttribute.USERNAME, "Hello "+user.getUsername());
		return ViewName.INDEX;
	}
}
