package com.web.test.member.vo;

public class MemberVO {

	private String userEmail;
	private String userPass;
	private String userNm;
	private String userPhone;
	private String post_code;
	private String addr;
	private String detail_addr;
	private String reg_dt;
	private String use_yn;
	
	public MemberVO() {
	}

	public MemberVO(String userEmail, String userPass, String userNm, String userPhone, String post_code, String addr,
			String detail_addr, String reg_dt, String use_yn) {
		this.userEmail = userEmail;
		this.userPass = userPass;
		this.userNm = userNm;
		this.userPhone = userPhone;
		this.post_code = post_code;
		this.addr = addr;
		this.detail_addr = detail_addr;
		this.reg_dt = reg_dt;
		this.use_yn = use_yn;
	}

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
	
	

}
