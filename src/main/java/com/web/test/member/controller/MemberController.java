package com.web.test.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@RequestMapping("/goSign_in.do")
	public String goSign_in() {
		
		return "/login/sign_in";
	}
}
