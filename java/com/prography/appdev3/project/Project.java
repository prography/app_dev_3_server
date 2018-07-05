package com.prography.appdev3.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.prography.appdev3.mapper.dataMapper;
import com.prography.appdev3.vo.FreeBoardResultVO;
import com.prography.appdev3.vo.FreeBoardVO;
import com.prography.appdev3.vo.IdCheckResultVO;
import com.prography.appdev3.vo.IdCheckVO;
import com.prography.appdev3.vo.LoginResultVO;
import com.prography.appdev3.vo.LoginVO;
import com.prography.appdev3.vo.SessionAttendanceResultVO;
import com.prography.appdev3.vo.SessionAttendanceVO;
import com.prography.appdev3.vo.SessionManageResultVO;
import com.prography.appdev3.vo.SessionManageVO;
import com.prography.appdev3.vo.SignUpResultVO;
import com.prography.appdev3.vo.StudyAttendanceResultVO;
import com.prography.appdev3.vo.StudyAttendanceVO;
import com.prography.appdev3.vo.StudyManageResultVO;
import com.prography.appdev3.vo.StudyManageVO;
import com.prography.appdev3.vo.TeamInfoResultVO;
import com.prography.appdev3.vo.TeamInfoVO;
import com.prography.appdev3.vo.UserInfoResultVO;
import com.prography.appdev3.vo.UserInfoVO;

@RestController //�� Ŭ������ �ۼ����� ����ϴ� Ŭ������ ����
				//�� ���̸� ����ؼ� rest api�� ����� ���� �並 ������ �� �� �ƴ϶� ������ ó���� ���� ��Ʈ�ѷ��� ���� �� ����
public class Project {
	
	Logger logger = LoggerFactory.getLogger(Project.class);

	@Autowired	//bean�̶� ����� �� �̰Ÿ� �����ϸ�  getter setter�� �ڵ����� ������
    private dataMapper dataMapper;//dataMapper����
	
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST,consumes = "application/json")
	public @ResponseBody LoginResultVO UserCheck(@RequestBody Map<String, Object> json) {//�ۿ��� ��ü�� �ִ� ���� ���̽����� �޾ƿ�
		// @RequestBody ������̼��� @RequestMapping�� ���� POST ������� ���۵� HTTP ��û �����͸� String Ÿ���� body �Ķ���ͷ� ���޵ȴ�.(����)

		// �׸��� @ResponseBody ������̼��� @RequestMapping �޼��忡�� ����Ǹ� �ش� �޼����� ���� ���� HTTP ���� �����ͷ� ����Ѵ�.

		// simpleTest() �޼����� ���� ���� String Ÿ���̹Ƿ� String �����͸� HTTP ���� �����ͷ� �����Ѵ�.(�۽�)


		
		logger.debug("user check > " + json.get("user_id") + "/" + json.get("user_pw"));
		
		
		String id = (String)json.get("id");//�̸� dataMapper.java�̸��� �����ϰ�
		String pw = (String)json.get("pw");
		
		
		LoginResultVO login = new LoginResultVO();
		ArrayList<LoginVO> loginresult = new ArrayList<LoginVO>();
		
		try {
			loginresult = dataMapper.userCheck(id, pw);
			
			if(loginresult.size()>0) {
				login.setSuccess(true);
				login.setMessage(id);
			}
			else {
				login.setSuccess(false);
				login.setMessage("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return login;
		
	}
	
	
	@RequestMapping(value="/signUp", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody SignUpResultVO SignUpCheck (@RequestBody Map<String, Object> json) {
		
		SignUpResultVO signUp = new SignUpResultVO();
			
		
		int memCode = (int) json.get("memCode");
		String id = (String) json.get("id");
		String pw = (String) json.get("pw");
		String name = (String) json.get("name");
		String nickname = (String) json.get("nickname");
		int tmCode = (int) json.get("tmCode");
		String birth = (String) json.get("birth");
		int recBalloon = (int) json.get("recBalloon");
		int balloon = (int) json.get("balloon");
		String icon = (String) json.get("icon");
		int sesAbsent = (int) json.get("sesAbsent");
		int stuAbsent = (int) json.get("stuAbsent");
		int totPenalty = (int) json.get("totPenalty");

	

		try {

			dataMapper.SignUpCheck(memCode, id, pw, name, nickname, tmCode, birth, recBalloon, balloon, icon, sesAbsent, stuAbsent, totPenalty);
			signUp.setSuccess(true);
			signUp.setMessage("ȯ���մϴ�^_^");

		} catch (Exception e) {

			signUp.setSuccess(false);
			signUp.setMessage("�Է��� ���̵�� �̹� �ִ� ���̵��Դϴ�. �ٸ� ���̵�� �������ּ���");
			e.printStackTrace();
		}
		return signUp;
		
		
	}
	
	
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody IdCheckResultVO IdCheck(@RequestBody Map<String, Object> json) {

		String id = (String) json.get("id");

		IdCheckResultVO idCheck = new IdCheckResultVO();

		ArrayList<IdCheckVO> idCheckList = new ArrayList<IdCheckVO>();

		try {
			idCheck = dataMapper.IdCheck(id);

			if (idCheckList.size() > 0) {
				idCheck.setSuccess(false);
				idCheck.setMessage("�Է��Ͻ� ���̵�� �̹� �ִ� ���̵� �Դϴ�. �ٸ� ���̵� �Է����ּ���");
			} else {
				idCheck.setSuccess(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return idCheck;

	}
	

	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST, consumes = "application/json")    
	public @ResponseBody UserInfoResultVO getUserInfo(@RequestBody Map<String, Object> json) {
		
		String getUserInfo = (String)json.get("getUserInfo");//�̸� dataMapper.java�̸��� �����ϰ�
        
		UserInfoResultVO result = new UserInfoResultVO();
		
		ArrayList<UserInfoVO> userInfo = new ArrayList<UserInfoVO>();
		try {

			List<UserInfoVO> resultUserInfo = dataMapper.getUserInfo();
			
			result.setSuccess(true);
			result.setResultUserInfo(resultUserInfo);
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();

			result.setSuccess(false);
			result.setResultUserInfo(null);
		}
		
	           
		return result;
   }
	
	
	
		
	@RequestMapping(value = "/getTeamInfo", method = RequestMethod.POST, consumes = "application/json")    
	public @ResponseBody TeamInfoResultVO getTeamInfo(@RequestBody Map<String, Object> json) {
		
		String getTeamInfo = (String)json.get("getTeamInfo");
        
		TeamInfoResultVO result = new TeamInfoResultVO();
		
		try {

			List<TeamInfoVO> resultTeamInfo = dataMapper.getTeamInfo();
			
			result.setSuccess(true);
			result.setResultTeamInfo(resultTeamInfo);
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();

			result.setSuccess(false);
			result.setResultTeamInfo(null);
		}
		
	           
		return result;
   }
	
	
	@RequestMapping(value = "/getStudyAttendance", method = RequestMethod.POST, consumes = "application/json")    
	public @ResponseBody StudyAttendanceResultVO getStudyAttendance(@RequestBody Map<String, Object> json) {
		
		String getStudyAttendance = (String)json.get("getStudyAttendance");
        
		StudyAttendanceResultVO result = new StudyAttendanceResultVO();
		
		try {

			List<StudyAttendanceVO> resultStuAttendance = dataMapper.getStudyAttendance();
			
			result.setSuccess(true);
			result.setResultStuAttendance(resultStuAttendance);
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();

			result.setSuccess(false);
			result.setResultStuAttendance(null);
		}
		
	           
		return result;
   }
	
	
	@RequestMapping(value = "/getStudyManage", method = RequestMethod.POST, consumes = "application/json")    
	public @ResponseBody StudyManageResultVO getStudyManage(@RequestBody Map<String, Object> json) {
		
		String getStudyManage = (String)json.get("getStudyManage");
        
		StudyManageResultVO result = new StudyManageResultVO();
		
		try {

			List<StudyManageVO> resultStudyManage = dataMapper.getStudyManage();
			
			result.setSuccess(true);
			result.setResultStudyManage(resultStudyManage);
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();

			result.setSuccess(false);
			result.setResultStudyManage(null);
		}
		
	           
		return result;
   }
	
	
	@RequestMapping(value = "/getSessionAttendance", method = RequestMethod.POST, consumes = "application/json")    
	public @ResponseBody SessionAttendanceResultVO getSessionAttendance(@RequestBody Map<String, Object> json) {
		
		String getSessionAttendance = (String)json.get("getSessionAttendance");
        
		SessionAttendanceResultVO result = new SessionAttendanceResultVO();
		
		try {

			List<SessionAttendanceVO> resultSesAttendance = dataMapper.getSessionAttendance();
			
			result.setSuccess(true);
			result.setResultSesAttendance(resultSesAttendance);
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();

			result.setSuccess(false);
			result.setResultSesAttendance(null);
		}
		
	           
		return result;
   }

	
	
	@RequestMapping(value = "/getSessionManage", method = RequestMethod.POST, consumes = "application/json")    
	public @ResponseBody SessionManageResultVO getSessionManage(@RequestBody Map<String, Object> json) {
		
		String getSessionManage = (String)json.get("getSessionManage");
	         
		SessionManageResultVO result = new SessionManageResultVO();
		
		try {

			List<SessionManageVO> resultSessionManage = dataMapper.getSessionManage();
			
			result.setSuccess(true);
			result.setResultSessionManage(resultSessionManage);
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();

			result.setSuccess(false);
			result.setResultSessionManage(null);
		}
		
	           
		return result;
   }

	
	@RequestMapping(value = "/getFreeBoard", method = RequestMethod.POST, consumes = "application/json")    
	public @ResponseBody FreeBoardResultVO getFreeBoard(@RequestBody Map<String, Object> json) {
		
		String getFreeBoard = (String)json.get("getFreeBoard");
        
		FreeBoardResultVO result = new FreeBoardResultVO();
		
		try {

			List<FreeBoardVO> resultFreeBoard = dataMapper.getFreeBoard();
			
			result.setSuccess(true);
			result.setResultFreeBoard(resultFreeBoard);
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();

			result.setSuccess(false);
			result.setResultFreeBoard(null);
		}
		
	           
		return result;
   }
	
	
	

}