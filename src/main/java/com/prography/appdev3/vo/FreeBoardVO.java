package com.prography.appdev3.vo;

public class FreeBoardVO {	//free table: 자유게시판 관리
	
	private int freNum = 0;
	private String freTitle = "";
	private String freContent = "";
	private String freDate = "";
	private int memCode = 0;
	private String nickName="";
	
	public String getNickName() {
		return nickName;
	}
	public int getFreNum() {
		return freNum;
	}
	public void setFreNum(int freNum) {
		this.freNum = freNum;
	}
	public String getFreTitle() {
		return freTitle;
	}
	public void setFreTitle(String freTitle) {
		this.freTitle = freTitle;
	}
	public String getFreContent() {
		return freContent;
	}
	public void setFreContent(String freContent) {
		this.freContent = freContent;
	}
	public String getFreDate() {
		return freDate;
	}
	public void setFreDate(String freDate) {
		this.freDate = freDate;
	}
	public int getMemCode() {
		return memCode;
	}
	public void setMemCode(int memCode) {
		this.memCode = memCode;
	}
	
	
}
