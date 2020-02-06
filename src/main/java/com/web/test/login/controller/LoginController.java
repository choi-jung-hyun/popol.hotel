package com.web.test.login.controller;


import java.security.PrivateKey;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.test.login.vo.RSA;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	RSAUtil rsaUtil;
	
	@RequestMapping("/loginView.do")
	public String login(HttpSession session, Model model) {
		

		return "/login/login";
	}
	
	
}
