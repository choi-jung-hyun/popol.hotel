package com.web.test.login.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.web.test.login.service.LoginService;
import com.web.test.member.vo.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Inject
	LoginService loginService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		try {
			// 로그인 상태인지 판단 유무
			if (request.getSession().getAttribute("loginId") == null) {

				// 세션이 null이라면 쿠키 정보 확인
				Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
				if (loginCookie != null) {
					//쿠키에서 세션ID를 꺼내옴.
					HashMap<String, Object> sessionMap = new HashMap<String, Object>();
					sessionMap.put("SESSIONID", loginCookie.getValue());
					
					// 세션 ID값을 기준으로 DB에 저장되있는 로그인 정보를 스캔
					MemberVO resultMap = (MemberVO) loginService.checkUserInfoWithCookie(sessionMap);
					//로그인 정보가 존재
					if(resultMap != null) {
						
						//세션에 로그인 정보를 저장
						request.getSession().setAttribute("loginId", resultMap);
						request.getSession().setMaxInactiveInterval(60 * 30);
						return true;
					}
				//세션도 null 쿠키도 null인경우 로그인페이지로 보냄
				}else {
					response.sendRedirect(request.getContextPath() + "/login/loginWarning.do");
					return false;
				}
			//로그인 상태 세션이 있음.	
			}else {
				return true;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.preHandle(request, response, handler);
	}
	
}
