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

    /* ������ ��ȸ API URL */
    private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
    
    public String generateState()
	{
	    SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(32);
	}

    /* �׾Ʒ� ����  URL ����  Method */
    public String getAuthorizationUrl(HttpSession session) {

    String state = generateState();
    session.setAttribute("state", state);

    /* Scribe���� �����ϴ� ���� URL ���� ����� �̿��Ͽ� �׾Ʒ� ���� URL ���� */
    OAuth20Service oauthService = new ServiceBuilder()
    		.apiKey(CLIENT_ID)
    		.apiSecret(CLIENT_SECRET)
    		.callback(REDIRECT_URI)
    		.state(state)
    		.build(NaverLoginApi.instance());

    return oauthService.getAuthorizationUrl();
    }
    
    /* �׾Ʒ� Callback ó�� ��  AccessToken ȹ�� Method */
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws Exception{
		
		/* Callback���� ���޹��� ���������� �������� ���ǿ� ����Ǿ��ִ� ���� ��ġ�ϴ��� Ȯ�� */
		String sessionState = (String)session.getAttribute("state");
		if(StringUtils.pathEquals(sessionState, state)){
		
			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(CLIENT_ID)
					.apiSecret(CLIENT_SECRET)
					.callback(REDIRECT_URI)
					.state(state)
					.build(NaverLoginApi.instance());
					
			/* Scribe���� �����ϴ� AccessToken ȹ�� ������� �׾Ʒ� Access Token�� ȹ�� */
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}

    /* Access Token�� �̿��Ͽ� ���̹� ����� ������ API�� ȣ�� */
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
