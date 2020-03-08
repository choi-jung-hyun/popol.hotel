package com.web.test.login.serviceImpl;

import java.security.PrivateKey;
import java.sql.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.test.login.controller.RSAUtil;
import com.web.test.login.dao.LoginDAO;
import com.web.test.login.service.LoginService;
import com.web.test.login.vo.LoginVO;
import com.web.test.member.vo.MemberVO;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	RSAUtil rsaUtil;
	
	@Autowired
	LoginDAO loginDAO;
	
	@Override
	public MemberVO memberInfo(LoginVO vo) throws Exception{
		
		PrivateKey key = (PrivateKey) request.getSession().getAttribute("_RSA_WEB_Key_");
		String Email = RSAUtil.getDecryptText(key, vo.getUserEmail());//RSA 복호화
		String pw = RSAUtil.getDecryptText(key, vo.getUserPw());//RSA 복호화
		vo.setUserEmail(Email);
		vo.setUserPw(RSAUtil.sha256_enc(pw));//sha256 암호화
		System.out.println(vo.getUserPw());
		return (MemberVO) loginDAO.memberInfo(vo);
	}
	
	//자동로그인 세션추가
	@Override
	public void keepLogin(HashMap<String, Object> map) throws Exception{
		loginDAO.keepLogin(map);
	}
	
	@Override
	public MemberVO checkUserInfoWithCookie(HashMap<String, Object> sessionMap)throws Exception{
		
		return (MemberVO)loginDAO.checkUserInfoWithCookie(sessionMap); 
	}
	
}
