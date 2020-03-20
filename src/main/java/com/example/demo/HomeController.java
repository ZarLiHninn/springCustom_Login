package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping(value=RequestUrl.SLASH)
public class HomeController {
	@GetMapping(RequestUrl.INDEX)
	   public String showIndexPage(){
		   return ViewName.INDEX;
	   }
}
