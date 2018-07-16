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
import com.prography.appdev3.vo.PostFreeResultVO;
import com.prography.appdev3.vo.PostStuMemoResultVO;
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
	
	
	
	
	//ȸ������=====================================================================================================================================
	
	
	
	//�α���
	@RequestMapping(value = "/Login", method = RequestMethod.POST,consumes = "application/json")
	public @ResponseBody LoginResultVO UserCheck(@RequestBody Map<String, Object> json) {//�ۿ��� ��ü�� �ִ� ���� ���̽����� �޾ƿ�
		// @RequestBody ������̼��� @RequestMapping�� ���� POST ������� ���۵� HTTP ��û �����͸� String Ÿ���� body �Ķ���ͷ� ���޵ȴ�.(����)
		// �׸��� @ResponseBody ������̼��� @RequestMapping �޼��忡�� ����Ǹ� �ش� �޼����� ���� ���� HTTP ���� �����ͷ� ����Ѵ�.
		// simpleTest() �޼����� ���� ���� String Ÿ���̹Ƿ� String �����͸� HTTP ���� �����ͷ� �����Ѵ�.(�۽�)


		
		logger.debug("user check > " + json.get("id") + "/" + json.get("pw"));
		
		
		String id = (String)json.get("id");//�̸� dataMapper.java�̸��� �����ϰ�
		String pw = (String)json.get("pw");
		
		
		LoginResultVO login = new LoginResultVO();
		ArrayList<LoginVO> loginresult = new ArrayList<LoginVO>();
		
		try {
			loginresult = dataMapper.UserCheck(id, pw);
			
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
	
	
	//ȸ������
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
	
	
	//ID�ߺ�Ȯ��
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
	

	//ȸ������(member table)���
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
	
	
	
	
	
	
	
	//��������=========================================================================================================================================
		
	
	//������(team table) ���
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
	
	
	//���� ���͵����(study attendance table) 
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
	
	
	//������ ���͵�(study table)
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
	
	
	//���͵�޸� �۾���
	@RequestMapping(value="/postStuMemo", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PostStuMemoResultVO PostStuMemo (@RequestBody Map<String, Object> json) {
		
		PostStuMemoResultVO postStuMemo = new PostStuMemoResultVO();
		
		int stuCode = (int) json.get("stuCode");
		int tmCode = (int) json.get("tmCode");
		String picture = (String) json.get("picture");
		String absentee = (String) json.get("absentee");
		String memo = (String) json.get("memo");
		String uploadTime = (String) json.get("uploadTime");
		
	

		try {

			dataMapper.PostStuMemo(stuCode, tmCode, picture, absentee, memo, uploadTime);
			postStuMemo.setSuccess(true);
			postStuMemo.setMessage("���� ��ϵǾ����ϴ�");

		} catch (Exception e) {

			postStuMemo.setSuccess(false);
			postStuMemo.setMessage("���� ������� ���߽��ϴ�");
			e.printStackTrace();
		}
		return postStuMemo;
		
		
	}
	
	
	
	
	//���͵� ������ ����Ʈ ���
	@RequestMapping(value = "/selectAbsentee", method = RequestMethod.POST, consumes = "application/json")    
	public @ResponseBody UserInfoResultVO SelectAbsentee(@RequestBody Map<String, Object> json) {//���̽����� �������
		
		int tmCode = (int)json.get("tmCode");
        
		UserInfoResultVO SelectAbsentee = new UserInfoResultVO();//�Լ�
		ArrayList<UserInfoVO> tmMemberList= new ArrayList<UserInfoVO>();//����Ʈ
		
		
		try {

			 tmMemberList = dataMapper.selectAbsentee(tmCode);
			 
			 if(tmMemberList.size() > 0) {
				 
				 SelectAbsentee.setSuccess(true);
				 SelectAbsentee.setSelectAbsentee(tmMemberList);
				 
			 }
			 
			 else {
				 SelectAbsentee.setSuccess(false);
			 }
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
	           
		return SelectAbsentee;
   }
	
	
	
	//���� ���� ���͵� ��� ���
		@RequestMapping(value = "/getStuAbsent", method = RequestMethod.POST, consumes = "application/json")    
		public @ResponseBody UserInfoResultVO GetStuAbsent(@RequestBody Map<String, Object> json) {//���̽����� �������
			
			int memCode = (int)json.get("memCode");
	        
			UserInfoResultVO GetStuAbsent = new UserInfoResultVO();//�Լ�
			ArrayList<UserInfoVO> totStuAbsent= new ArrayList<UserInfoVO>();//����Ʈ
			
			
			try {

				 totStuAbsent = dataMapper.getStuAbsent(memCode);
				 
				 if(totStuAbsent.size() > 0) {
					 
					 GetStuAbsent.setSuccess(true);
					 GetStuAbsent.setGetStuAbsent(totStuAbsent);
					 
				 }
				 
				 else {
					 GetStuAbsent.setSuccess(false);
				 }
				
				
			}catch (Exception e) {
				e.printStackTrace();
				
			}
			
		           
			return GetStuAbsent;
	   }
	
	
	
	
	
	//����������============================================================================================================================================
	
	
	//������ ���� ���κ� �������(session attendance table)
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

	
	//������ ����, ��������(session table)
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

	
	
	//���������Է�
	@RequestMapping(value="/postSesInfo", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody SessionManageResultVO PostSesInfo (@RequestBody Map<String, Object> json) {
		
		SessionManageResultVO postSesInfo = new SessionManageResultVO();
		
		int sesCode = (int) json.get("sesCode");
		String sesDate = (String) json.get("sesDate");
		String sesInfo = (String) json.get("sesInfo");
		String sesContent = (String) json.get("sesContent");
		
	

		try {

			dataMapper.PostSesInfo(sesCode, sesDate, sesInfo, sesContent);
			postSesInfo.setSuccess(true);
			postSesInfo.setMessage("���� ��ϵǾ����ϴ�");

		} catch (Exception e) {

			postSesInfo.setSuccess(false);
			postSesInfo.setMessage("���� ������� ���߽��ϴ�");
			e.printStackTrace();
		}
		return postSesInfo;
		
		
	}
	
	
	
	//�����Խ���=================================================================================================================================================
	
	
	//�����Խ���(free table)���
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
	
	
	//�����Խ��� �۾���
	@RequestMapping(value="/postFreeBoard", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PostFreeResultVO PostFreeBoard (@RequestBody Map<String, Object> json) {
		
		PostFreeResultVO postFreeBoard = new PostFreeResultVO();
			
		
		int freNum = (int) json.get("freNum");
		String freTitle = (String) json.get("freTitle");
		String freContent = (String) json.get("freContent");
		String freDate = (String) json.get("freDate");
		int memCode = (int) json.get("memCode");
		
	

		try {

			dataMapper.PostFreeBoard(freNum, freTitle, freContent, freDate, memCode);
			postFreeBoard.setSuccess(true);
			postFreeBoard.setMessage("���� ��ϵǾ����ϴ�");

		} catch (Exception e) {

			postFreeBoard.setSuccess(false);
			postFreeBoard.setMessage("���� ������� ���߽��ϴ�");
			e.printStackTrace();
		}
		return postFreeBoard;
		
		
	}
	

}
