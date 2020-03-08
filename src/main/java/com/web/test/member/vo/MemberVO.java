package com.web.test.member.vo;

public class MemberVO {

	private String userEmail;		// 이메일 , 네이버, 카카오 고유아이디
	private String userPass;		// 패스워드
	private String userNm;			// 이름
	private String userPhone;		// 휴대폰번호
	private String post_code;		// 우편번호
	private String addr;			// 주소
	private String detail_addr;		// 상세주소
	private String reg_dt;			// 회원가입일
	private String use_yn;			// 회원 탈퇴여부
	private String SESSIONKEY;		// 자동로그인 세션키
	private String SESSIONLIMIT;	// 자동로그인 기간
	private String snsEmail;		// 네이버, 카카오 로그인 email
	private String snsType;			// sns 타입
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getPost_code() {
		return post_code;
	}

	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getDetail_addr() {
		return detail_addr;
	}

	public void setDetail_addr(String detail_addr) {
		this.detail_addr = detail_addr;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	public String getSESSIONKEY() {
		return SESSIONKEY;
	}

	public void setSESSIONKEY(String sESSIONKEY) {
		SESSIONKEY = sESSIONKEY;
	}

	public String getSESSIONLIMIT() {
		return SESSIONLIMIT;
	}

	public void setSESSIONLIMIT(String sESSIONLIMIT) {
		SESSIONLIMIT = sESSIONLIMIT;
	}

	public String getSnsEmail() {
		return snsEmail;
	}

	public void setSnsEmail(String snsEmail) {
		this.snsEmail = snsEmail;
	}

	public String getSnsType() {
		return snsType;
	}

	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}
	
}
