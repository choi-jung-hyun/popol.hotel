package com.web.test.login.controller;



import java.sql.Date;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.web.test.login.service.LoginService;
import com.web.test.login.vo.LoginVO;
import com.web.test.member.vo.MemberVO;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/loginView.do")
	public String login(HttpSession session, Model model) {
		
		return "/login/login";
	}
	//�α����ؾ��ϴ±�� ���� 
	@RequestMapping("/loginWarning.do")
	public String loginWarning() {
		
		return "/login/loginWarning";
	}
	
	@RequestMapping("/loginProc.do")
	@ResponseBody
	public HashMap<String, Object> loginProc(LoginVO vo,HttpSession session,HttpServletRequest request,HttpServletResponse response, Model model)throws Exception {
		//��� ���� map ����
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		//���� loginId ���� ���� �����Ѵٸ� ������ ����
		if(session.getAttribute("loginId") != null) {
			session.removeAttribute("loginId");
		}
		
		MemberVO login =  loginService.memberInfo(vo);// service���� rsa sha ��ȣȭ �� ��ȣȭ ����
		
		//�α��� ���н�
		if(login == null) {
			resultMap.put("resultCode", -1);
			resultMap.put("msg", "ȸ�������� ��ȿ���� �ʽ��ϴ�. �α����� �ٽ� �õ����ּ���.");
		
		//�α��� ������
		}else {
		
			//�α��� ���� ���� �� ����� ������ ����
			request.getSession().setAttribute("loginId", login);
			request.getSession().setMaxInactiveInterval(60 * 30);
			// �α��ν� �ڵ��α��� Ŭ�� ���� Ȯ��
			if(vo.getKeepLogin().equals("Y")) {
				System.out.println("�ڵ��α��� Ŭ��");
				
				// ��Ű�� �����ϰ� ������ ������ id�� ��Ű�� ����.
				Cookie cookie = new Cookie("loginCookie", request.getSession().getId());// interceptor���� ���
				// ��Ű�� ã�� ��θ� ���ؽ�Ʈ ��� ����.
				cookie.setPath("/");
				//��ȿ�Ⱓ 7�Ϸ� ����
				int amount = 60 * 60 * 24 * 7;
				cookie.setMaxAge(amount);
				//��Ű ����
				response.addCookie(cookie);
				//����ð� + 7���� ���Ѱ�
				Date sessionLimit = new Date(System.currentTimeMillis() + (1000*amount));
				// ���� ���� id�� ��ȿ�ð��� ����� ���̺� ����
				
				HashMap<String, Object> map = new HashMap<String, Object>();	
				map.put("SESSIONID", request.getSession().getId()); //���� ID
				map.put("SESSIONLIMIT", sessionLimit);//���Ǹ��� �Ⱓ
				map.put("loginId", login);//�α��� �õ��ϴ� ������ ����
				loginService.keepLogin(map);
			}

			resultMap.put("resultCode", 1);
			resultMap.put("msg", "�α��� ����!");
		}
		 System.out.println(resultMap);
			
		return resultMap;
	}
	
	@RequestMapping("/logout.do")
	public ModelAndView loginProc(LoginVO vo,HttpSession session,HttpServletRequest request,HttpServletResponse response)throws Exception {
	
		//������ �����ִ°��, �α��ε� ��Ȳ
		if(session.getAttribute("loginId") != null) {
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			//��� ������ �����ϴ� ���
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				
				//�α��ε� ȸ�� ���� �ð� �缳��
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("SESSIONID", session.getId());
				Date sessionLimit = new Date(System.currentTimeMillis());
				map.put("SESSIONLIMIT", sessionLimit);
				map.put("loginId", session.getAttribute("loginId"));
				loginService.keepLogin(map);
			}
			// loginId ���� ����
			request.getSession().removeAttribute("loginId");
			
		}
		ModelAndView mav = new ModelAndView("redirect:/main/main.do");
		return mav;
	}
	
}
