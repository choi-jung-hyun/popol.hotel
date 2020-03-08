
package com.web.test.login.service;


import java.util.HashMap;

import com.web.test.login.vo.LoginVO;
import com.web.test.member.vo.MemberVO;

public interface LoginService {

	MemberVO memberInfo(LoginVO vo) throws Exception; 
	
	void keepLogin(HashMap<String, Object> map) throws Exception;
	
	MemberVO checkUserInfoWithCookie(HashMap<String, Object> sessionMap)throws Exception;

	
}
