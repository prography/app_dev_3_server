package com.prography.appdev3.vo;

public class LoginResultVO { //login���� �����Ǵ� �� �޼��� ���
	
	public boolean success;
	public int memCode;
	public int tmCode;
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getMemCode() {
		return memCode;
	}
	public void setMemCode(int memCode) {
		this.memCode= memCode;
	}
	public int getTmCode() {
		return tmCode;
	}
	public void setTmCode(int tmCode) {
		this.tmCode= tmCode;
	}

}
