package com.prography.appdev3.vo;

import java.util.ArrayList;
import java.util.List;

public class FreeBoardResultVO {	//free table�� arrayList�� ������

	
	private boolean success = false;
	public String Message = "";
	private List<FreeBoardVO> freeBoardList = new ArrayList<FreeBoardVO>();
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<FreeBoardVO> getResultFreeBoard() {
		return freeBoardList;
	}
	public void setResultFreeBoard(List<FreeBoardVO> freeBoardList) {
		this.freeBoardList = freeBoardList;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
	
	
}
