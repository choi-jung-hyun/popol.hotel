package com.web.test.login.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

@Controller
public class NaverLoginBO {

    private final static String CLIENT_ID = "cHqamEdUFcq4gyp_wgAS";
    private final static String CLIENT_SECRET = "h5MMmPX4lO";
    private final static String REDIRECT_URI = "http://localhost:8079/login/naverLoginProc.do";

    /* 프로필 조회 API URL */
    private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
    
    public String generateState()
	{
	    SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(32);
	}

    /* 네아로 인증  URL 생성  Method */
    public String getAuthorizationUrl(HttpSession session) {

    String state = generateState();
    session.setAttribute("state", state);

    /* Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성 */
    OAuth20Service oauthService = new ServiceBuilder()
    		.apiKey(CLIENT_ID)
    		.apiSecret(CLIENT_SECRET)
    		.callback(REDIRECT_URI)
    		.state(state)
    		.build(NaverLoginApi.instance());

    return oauthService.getAuthorizationUrl();
    }
    
    /* 네아로 Callback 처리 및  AccessToken 획득 Method */
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws Exception{
		
		/* Callback으로 전달받은 세선검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인 */
		String sessionState = (String)session.getAttribute("state");
		if(StringUtils.pathEquals(sessionState, state)){
		
			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(CLIENT_ID)
					.apiSecret(CLIENT_SECRET)
					.callback(REDIRECT_URI)
					.state(state)
					.build(NaverLoginApi.instance());
					
			/* Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득 */
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}

    /* Access Token을 이용하여 네이버 사용자 프로필 API를 호출 */
    public String getUserProfile(OAuth2AccessToken oauthToken) throws Exception{

      OAuth20Service oauthService =new ServiceBuilder()
            .apiKey(CLIENT_ID)
            .apiSecret(CLIENT_SECRET)
            .callback(REDIRECT_URI).build(NaverLoginApi.instance());

        OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
      oauthService.signRequest(oauthToken, request);
      Response response = request.send();
      return response.getBody();
    }
	
	
  }           
