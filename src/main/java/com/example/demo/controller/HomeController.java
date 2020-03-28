package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import com.example.demo.constant.RequestUrl;
import com.example.demo.constant.ViewName;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = {RequestUrl.INDEX, RequestUrl.SLASH})
	public String showIndexPage(){
		return ViewName.INDEX;
	}
}
