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
		//����Ű ���ǿ��� ������
		PrivateKey key = (PrivateKey) request.getSession().getAttribute("_RSA_WEB_Key_");
		String Email = RSAUtil.getDecryptText(key, vo.getUserEmail());//RSA ���̵� ��ȣȭ
		String pw = RSAUtil.getDecryptText(key, vo.getUserPass());//RSA ��й�ȣ ��ȣȭ
		vo.setUserEmail(Email);
		vo.setUserPass(RSAUtil.sha256_enc(pw));//sha256 ��ȣȭ�� DB����
		vo.setUse_yn("Y");
		int result = memberService.insertMember(vo);
		
		if(result > 0) {
			map.put("Msg", "회원가입 성공!");
			map.put("resultCode","1");
		}else {
			map.put("Msg", "회원가입 실패! 다시시도해주세요.");
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
			map.put("Msg", "중복확인 성공!");
			map.put("resultCode","1");
		}else {
			map.put("Msg", "이미사용중인 아이디입니다.");
			map.put("resultCode","-1");
		}
		return map;
	}
	
}
