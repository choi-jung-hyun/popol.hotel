package com.web.test.member.controller;

import java.security.PrivateKey;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.test.login.controller.RSAUtil;
import com.web.test.member.service.MemberService;
import com.web.test.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/goSign_up.do")
	public String goSign_up() {
		
		return "/login/sign_up";
	}
	
	@RequestMapping("/sign_upAct.do")
	@ResponseBody
	public HashMap<String, Object> sign_upAct(MemberVO vo, HttpSession session,HttpServletRequest request, Model model)throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		//개인키 세션에서 가져옴
		PrivateKey key = (PrivateKey) request.getSession().getAttribute("_RSA_WEB_Key_");
		String Email = RSAUtil.getDecryptText(key, vo.getUserEmail());//RSA 아이디 복호화
		String pw = RSAUtil.getDecryptText(key, vo.getUserPass());//RSA 비밀번호 복호화
		vo.setUserEmail(Email);
		vo.setUserPass(RSAUtil.sha256_enc(pw));//sha256 암호화후 DB저장
		vo.setUse_yn("Y");
		int result = memberService.insertMember(vo);
		
		if(result > 0) {
			map.put("Msg", "회원가입에 성공 했습니다.");
			map.put("resultCode","1");
		}else {
			map.put("Msg", "회원가입에 실패 했습니다.");
			map.put("resultCode","-1");
		}
		return map;
	}
	
	@RequestMapping("/email_check.do")
	@ResponseBody
	public HashMap<String, Object> email_check(MemberVO vo, HttpSession session, Model model)throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int result = memberService.email_check(vo);
		
		if(result > 0) {
			map.put("Msg", "이미 중복된 이메일 입니다.");
			map.put("resultCode","1");
		}else {
			map.put("Msg", "사용할 수 있는 이메일입니다.");
			map.put("resultCode","-1");
		}
		return map;
	}
}
