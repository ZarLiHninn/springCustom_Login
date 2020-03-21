package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;import com.example.demo.constants.RequestUrl;
import com.example.demo.constants.ViewName;
@Controller
public class HomeController {
	@GetMapping(RequestUrl.INDEX)
	public String showIndexPage(){
		return ViewName.INDEX;
	}
}
