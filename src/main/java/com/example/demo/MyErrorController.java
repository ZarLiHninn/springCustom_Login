package com.example.demo;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

	@RequestMapping(RequestUrl.ERROR)
    public String handleError() {
       return ViewName.ERROR;
    }
	@Override
	public String getErrorPath() {
		return RequestUrl.ERROR;
	}
	
	

}
