package com.prography.appdev3.vo;

import java.util.ArrayList;
import java.util.List;

public class TeamInfoResultVO { 	//team table�� arrayList�� ������

	private boolean success = false;
	public String message = "";
	
	private List<TeamInfoVO> teamInfoList = new ArrayList<TeamInfoVO>();
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<TeamInfoVO> getResultTeamInfo() {
		return teamInfoList;
	}
	public void setResultTeamInfo(List<TeamInfoVO> teamInfoList) {
		this.teamInfoList = teamInfoList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
