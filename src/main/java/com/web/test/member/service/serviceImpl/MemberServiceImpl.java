package com.web.test.member.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.test.member.dao.MemberDAO;
import com.web.test.member.service.MemberService;
import com.web.test.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memberDAO;
	
	@Override
	public int insertMember(MemberVO vo)throws Exception{
		
		return  memberDAO.insertMember(vo);
	}
	
	@Override
	public int email_check(MemberVO vo)throws Exception{
		
		return memberDAO.email_check(vo);
	}
}
