package com.example.demo.controller;

import com.example.demo.constant.MessageId;
import com.example.demo.constant.ViewAttribute;
import com.example.demo.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import com.example.demo.constant.RequestUrl;
import com.example.demo.constant.ViewName;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;
import java.util.Objects;

@Controller
public class LoginController {

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value=RequestUrl.LOGIN)
	public String showLoginForm(
			@AuthenticationPrincipal User user,
			@RequestAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false) AuthenticationException exception,
			Model model){
		if (Objects.nonNull(user)){
			return "redirect:" + RequestUrl.INDEX;
		}
		if (Objects.nonNull(exception)) {
			setErrorMessage(exception, model);
		}
		return ViewName.LOGIN;
	}

	private void setErrorMessage(AuthenticationException exception, Model model){
		if (exception instanceof SessionAuthenticationException) {
			model.addAttribute(ViewAttribute.ERROR, messageSource.getMessage(MessageId.LOGIN_MAXIMUM_SESSION, null, Locale.JAPAN));
		}
		else if (exception instanceof BadCredentialsException) {
			model.addAttribute(ViewAttribute.ERROR, messageSource.getMessage(MessageId.LOGIN_BAD_CREDENTIALS, null, Locale.JAPAN));
		}
		else {
			throw new SystemException("ログイン処理で予期せぬエラーが発生しました。");
		}
	}

}
