package com.example.demo.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.constants.RequestUrl;
import com.example.demo.constants.ViewName;

@Controller
public class MyErrorController implements ErrorController {

	@RequestMapping(value=RequestUrl.ERROR)
    public String handleError() {
       return ViewName.ERROR;
    }
	@Override
	public String getErrorPath() {
		return RequestUrl.ERROR;
	}
	
	

}
