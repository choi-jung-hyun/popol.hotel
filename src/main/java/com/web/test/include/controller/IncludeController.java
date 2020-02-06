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
		PrivateKey privateKey = (PrivateKey) map.get("privateKey");
		//����Ű�� session �� �����Ѵ�.
		request.getSession().setAttribute("_RSA_WEB_Key_", privateKey);

		return map;
	}
	
	
}
