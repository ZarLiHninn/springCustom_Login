package com.example.demo.controller;

import com.example.demo.Model.User;
import com.example.demo.Services.MyUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.constants.RequestUrl;
import com.example.demo.constants.ViewName;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = {RequestUrl.INDEX, RequestUrl.SLASH})
	public String showIndexPage(@AuthenticationPrincipal MyUserDetails user, Model model){
		model.addAttribute("username", user.getUsername());
		return ViewName.INDEX;
	}
}
