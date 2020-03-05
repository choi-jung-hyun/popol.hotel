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
			// �α��� �������� �Ǵ� ����
			if (request.getSession().getAttribute("loginId") == null) {

				// ������ null�̶�� ��Ű ���� Ȯ��
				Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
				if (loginCookie != null) {
					//��Ű���� ����ID�� ������.
					HashMap<String, Object> sessionMap = new HashMap<String, Object>();
					sessionMap.put("SESSIONID", loginCookie.getValue());
					
					// ���� ID���� �������� DB�� ������ִ� �α��� ������ ��ĵ
					MemberVO resultMap = (MemberVO) loginService.checkUserInfoWithCookie(sessionMap);
					//�α��� ������ ����
					if(resultMap != null) {
						
						//���ǿ� �α��� ������ ����
						request.getSession().setAttribute("loginId", resultMap);
						request.getSession().setMaxInactiveInterval(60 * 30);
						return true;
					}
				//���ǵ� null ��Ű�� null�ΰ�� �α����������� ����
				}else {
					response.sendRedirect(request.getContextPath() + "/login/loginWarning.do");
					return false;
				}
			//�α��� ���� ������ ����.	
			}else {
				return true;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.preHandle(request, response, handler);
	}
	
}
