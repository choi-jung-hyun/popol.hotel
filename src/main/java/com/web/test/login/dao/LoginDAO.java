package com.web.test.login.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.test.login.vo.LoginVO;
import com.web.test.member.vo.MemberVO;

@Repository
public class LoginDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public MemberVO memberInfo(LoginVO vo)throws Exception {
		
		return (MemberVO) sqlSession.selectOne("MemberDAO.memberInfo", vo);
	}
	
	@Autowired
	public void keepLogin(HashMap<String, Object> map) throws Exception{
		
		sqlSession.update("LoginDAO.keepLogin", map);
	}
	
	@Autowired
	public MemberVO checkUserInfoWithCookie(HashMap<String, Object> sessionMap)throws Exception {
		return (MemberVO)sqlSession.selectOne("LoginDAO.checkUserInfoWithCookie", sessionMap);
	}
}
