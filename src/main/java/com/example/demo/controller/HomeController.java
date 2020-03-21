package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.constants.RequestUrl;
import com.example.demo.constants.ViewName;
@Controller
@RequestMapping(value=RequestUrl.SLASH)
public class HomeController {
	@GetMapping(RequestUrl.INDEX)
	   public String showIndexPage(){
		   return ViewName.INDEX;
	   }
}
