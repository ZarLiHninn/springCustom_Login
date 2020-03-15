package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class HomeController {
	/*@PostMapping("/index")
  public String index(){
	  return "index";
  }*/
  @GetMapping("/logout")
  public String logout(){
	  return "login";
  }
}
