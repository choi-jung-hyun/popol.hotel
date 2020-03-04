package com.web.test.include.controller;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.test.login.controller.RSAUtil;
import com.web.test.login.vo.RSA;

@Controller
@RequestMapping("/include")
public class IncludeController {

	@RequestMapping(value = "/getKey.do")
	public @ResponseBody
	Map<String, Object> encgetKey( HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String,Object> map = RSAUtil.createRSA();
		PrivateKey privateKey = (PrivateKey) map.get("privateKey");//생성한 비밀키를 꺼냄
		//개인키를 session 에 저장한다.
		request.getSession().setAttribute("_RSA_WEB_Key_", privateKey);//꺼낸 비밀키를 세션에 저장

		HashMap<String, Object> rsa = new HashMap<String, Object>();
		rsa.put("pubKeyModule", map.get("pubKeyModule"));
		rsa.put("pubKeyExponent", map.get("pubKeyExponent"));
		return rsa;
	}
	
	
}
