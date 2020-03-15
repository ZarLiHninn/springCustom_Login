package com.example.demo.controller;

import com.example.demo.constants.RequestUrl;
import com.example.demo.constants.ViewName;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping(RequestUrl.LOGOUT) //
  public String logout(){
	  return ViewName.LOGIN;
  }

}
