package com.example.demo.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.constant.RequestUrl;
import com.example.demo.constant.ViewName;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.View;

@Controller
public class MyErrorController implements ErrorController{

	@RequestMapping(value = RequestUrl.ERROR, method = RequestMethod.GET)
	public String renderErrorPage(HttpServletResponse httpServletResponse, HttpServletRequest httpRequest) {

		int httpErrorCode = getErrorCode(httpRequest);

		switch (httpErrorCode) {
			case 404: {
				return ViewName.NOT_FOUND;
			}
			default: {
				return ViewName.INTERNAL_SERVER_ERROR;
			}
		}
	}

	@RequestMapping(RequestUrl.ACCESS_DENIED)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String forbidden(){
		return ViewName.ACCESS_DENIED;
	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest
				.getAttribute("javax.servlet.error.status_code");
	}



	@Override
	public String getErrorPath() {

		return ViewName.INTERNAL_SERVER_ERROR;
	}
	

}
