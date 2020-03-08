package com.web.test.login.controller;

import java.sql.Date;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.web.test.login.service.LoginService;
import com.web.test.login.vo.LoginVO;
import com.web.test.member.service.MemberService;
import com.web.test.member.vo.MemberVO;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	MemberService memberService;

	@Autowired
	NaverLoginBO NaverLoginBO;

	@RequestMapping("/loginView.do")
	public String login(HttpSession session, Model model) {

		String naverAuthUrl = NaverLoginBO.getAuthorizationUrl(session);

		/* 생성한 인증 URL을 View로 전달 */
		model.addAttribute("naver_url", naverAuthUrl);

		return "/login/login";
	}

	// 로그인해야하는기능 사용시
	@RequestMapping("/loginWarning.do")
	public String loginWarning() {

		return "/login/loginWarning";
	}

	// 일반로그인
	@RequestMapping("/loginProc.do")
	@ResponseBody
	public HashMap<String, Object> loginProc(LoginVO vo, HttpSession session, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		// 결과 리턴 map 생성
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		// 기존 loginId 세션 값이 존재한다면 기존값 제거
		if (session.getAttribute("loginId") != null) {
			session.removeAttribute("loginId");
		}

		MemberVO login = loginService.memberInfo(vo);// service에서 rsa sha 복호화 및 암호화 진행

		// 로그인 실패시
		if (login == null) {
			resultMap.put("resultCode", -1);
			resultMap.put("msg", "회원정보가 유효하지 않습니다. 로그인을 다시 시도해주세요.");

			// 로그인 성공시
		} else {

			// 로그인 세션 생성 후 사용자 정보를 담음
			request.getSession().setAttribute("loginId", login);
			request.getSession().setMaxInactiveInterval(60 * 30);
			// 로그인시 자동로그인 클릭 여부 확인
			if (vo.getKeepLogin().equals("Y")) {
				System.out.println("자동로그인 클릭");

				// 쿠키를 생성하고 생성한 세션의 id를 쿠키에 저장.
				Cookie cookie = new Cookie("loginCookie", request.getSession().getId());// interceptor에서 사용
				// 쿠키를 찾을 경로를 컨텍스트 경로 변경.
				cookie.setPath("/");
				// 유효기간 7일로 설정
				int amount = 60 * 60 * 24 * 7;
				cookie.setMaxAge(amount);
				// 쿠키 적용
				response.addCookie(cookie);
				// 현재시간 + 7일을 더한값
				Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
				// 현재 세션 id와 유효시간을 사용자 테이블에 저장

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("SESSIONID", request.getSession().getId()); // 세션 ID
				map.put("SESSIONLIMIT", sessionLimit);// 세션만료 기간
				map.put("loginId", login);// 로그인 시도하는 계정의 정보
				loginService.keepLogin(map);
			}

			resultMap.put("resultCode", 1);
			resultMap.put("msg", "로그인 성공!");
		}
		System.out.println(resultMap);

		return resultMap;
	}

	// 일반로그아웃
	@RequestMapping("/logout.do")
	public ModelAndView loginProc(LoginVO vo, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 세션이 남아있는경우, 로그인된 상황
		if (session.getAttribute("loginId") != null) {
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

			// 쿠기 정보가 존재하는 경우
			if (loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);

				// 로그인된 회원 세션 시간 재설정
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("SESSIONID", session.getId());
				Date sessionLimit = new Date(System.currentTimeMillis());
				map.put("SESSIONLIMIT", sessionLimit);
				map.put("loginId", session.getAttribute("loginId"));
				loginService.keepLogin(map);
			}
			// loginId 세션 삭제
			request.getSession().removeAttribute("loginId");

		}
		ModelAndView mav = new ModelAndView("redirect:/main/main.do");
		return mav;
	}

	// 네이버 로그인, 회원가입
	@RequestMapping("/naverLoginProc.do")
	public String naverLoginProc(Model model, String code, String state, HttpSession session,
			HttpServletRequest request) throws Exception {


		/* 네아로 인증이 성공적으로 완료되면 code 파라미터가 전달되며 이를 통해 access token을 발급 */
		OAuth2AccessToken oauthToken = NaverLoginBO.getAccessToken(session, code, state);
		String apiResult = NaverLoginBO.getUserProfile(oauthToken);
		System.out.println(apiResult);

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(apiResult);

		String resultCode = (String) obj.get("resultcode");

		if (resultCode.equals("00")) {// api 콜백받은 사용자 프로필을 DB에 저장. -- 00 로그인성공 로그인 연동 개념이아님. 각자의 아이디라는 생각.

			JSONObject response = (JSONObject) obj.get("response");

			String name = (String) response.get("name");
			String id = (String) response.get("id");// 이게 userEmail로 사용할예정
			String email = (String) response.get("email");// snsEmail 이라는 컬럼추가할꺼임. 네이버,카카오로 로그인한사람의 이메일주소

			MemberVO vo = new MemberVO();
			vo.setUserEmail(id);
			// sns 로그인한사람 디비 정보 기입 확인
			int snsSignUp_check = memberService.email_check(vo);
			// sns 데이터 정보 기입안되있는경우
			if (snsSignUp_check < 1) {

				MemberVO vo2 = new MemberVO();
				vo2.setUse_yn("Y");
				vo2.setUserNm(name);
				vo2.setUserEmail(id);
				vo2.setSnsEmail(email);
				vo2.setSnsType("NAVER");
				
				int result = memberService.snsSign_upAct(vo2);
				if (result > 0) {
					System.out.println("네이버 로그인 프로필 저장성공.");
				} else {
					System.out.println("네이버 로그인 프로필 저장 실패.");
				}

			}

			// 기존 loginId 세션 값이 존재한다면 기존값 제거
			if (session.getAttribute("loginId") != null) {
				session.removeAttribute("loginId");
			}

			// 디비 id와 api에서 받은 id 비교후 같으면 회원정보를 세션에 담음.
			MemberVO login = memberService.snsMemberInfo(vo);
		
				// 로그인 세션 생성 후 사용자 정보를 담음
				request.getSession().setAttribute("loginId", login);
				request.getSession().setMaxInactiveInterval((60 * 30) * 24 );
			}
		
		return "/main/main";

	}
}
