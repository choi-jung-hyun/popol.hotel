package com.web.test.member.service;


import com.web.test.member.vo.MemberVO;

public interface MemberService  {

	int insertMember(MemberVO vo)throws Exception;
	
	int email_check(MemberVO vo)throws Exception;
	
	int snsSign_upAct(MemberVO vo)throws Exception;

	MemberVO snsMemberInfo(MemberVO vo) throws Exception;
}
