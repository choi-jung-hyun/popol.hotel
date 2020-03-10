package com.web.test.member.service;


import com.web.test.member.vo.MemberVO;

public interface MemberService  {

	int insertMember(MemberVO vo)throws Exception;
	
	int email_check(MemberVO vo)throws Exception;
	
	int naverSign_upAct(MemberVO vo)throws Exception;

	MemberVO snsMemberInfo(MemberVO vo) throws Exception;
	
	int kakaoSign_upAct(MemberVO vo)throws Exception;
	
	int facebookSign_upAct(MemberVO vo) throws Exception;
}
