package com.example.demo.config;

import com.example.demo.constant.RequestUrl;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {

		httpServletRequest.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, e);
		httpServletRequest.getRequestDispatcher(RequestUrl.LOGIN).forward(httpServletRequest, httpServletResponse);

	}
}
