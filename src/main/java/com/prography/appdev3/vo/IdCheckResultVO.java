package com.prography.appdev3.vo;

public class IdCheckResultVO { 	//ȸ�����Խ� ID �� �޼��� ���

	
	boolean success;
	String message = "";
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
