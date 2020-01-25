package com.web.test.login.controller;


import java.security.PrivateKey;

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
		
	       // RSA 키 생성
        PrivateKey key = (PrivateKey) session.getAttribute("RSAprivateKey");
        if (key != null) { // 기존 key 파기
            session.removeAttribute("RSAprivateKey");
        }
        RSA rsa = rsaUtil.createRSA();
        model.addAttribute("modulus", rsa.getModulus());
        model.addAttribute("exponent", rsa.getExponent());
        session.setAttribute("RSAprivateKey", rsa.getPrivateKey());
		return "/login/login";
	}
	
	
}
