package com.web.test.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.test.member.vo.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insertMember(MemberVO vo){
		return sqlSession.insert("MemberDAO.insertMember", vo);
	}
	
	public int email_check(MemberVO vo) {
		return sqlSession.selectOne("MemberDAO.email_check", vo);
	}
} 
