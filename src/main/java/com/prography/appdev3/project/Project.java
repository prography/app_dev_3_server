package com.prography.appdev3.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.*;

import com.prography.appdev3.mapper.dataMapper;
import com.prography.appdev3.vo.DeleteFreeResultVO;
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

@RestController // �� Ŭ������ �ۼ����� ����ϴ� Ŭ������ ����
				// �� ���̸� ����ؼ� rest api�� ����� ���� �並 ������ �� �� �ƴ϶� ������ ó���� ���� ��Ʈ�ѷ��� ���� �� ����
public class Project {
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired // bean�̶� ����� �� �̰Ÿ� �����ϸ� getter setter�� �ڵ����� ������
	private dataMapper dataMapper;// dataMapper����

	// member=====================================================================================================================================

	// �α���
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody LoginResultVO userCheck(@RequestBody Map<String, Object> json) {// �ۿ��� ��ü�� �ִ� ���� ���̽����� �޾ƿ�
		// @RequestBody ������̼��� @RequestMapping�� ���� POST ������� ���۵� HTTP ��û �����͸� String Ÿ����
		// body �Ķ���ͷ� ���޵ȴ�.(����)
		// �׸��� @ResponseBody ������̼��� @RequestMapping �޼��忡�� ����Ǹ� �ش� �޼����� ���� ���� HTTP ����
		// �����ͷ� ����Ѵ�.
		// simpleTest() �޼����� ���� ���� String Ÿ���̹Ƿ� String �����͸� HTTP ���� �����ͷ� �����Ѵ�.(�۽�)

		logger.debug("user check > " + json.get("id") + "/" + json.get("pw"));

		String id = (String) json.get("id");// �̸� dataMapper.java�̸��� �����ϰ�
		String pw = (String) json.get("pw");

		LoginResultVO login = new LoginResultVO();
		ArrayList<LoginVO> loginresult = new ArrayList<LoginVO>();

		try {
			loginresult = dataMapper.userCheck(id, pw);

			if (loginresult.size() > 0) {
				login.setSuccess(true);
				login.setMessage(id);
			} else {
				login.setSuccess(false);
				login.setMessage("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return login;

	}

	// ID�ߺ�Ȯ��
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody IdCheckResultVO idCheck(@RequestBody Map<String, Object> json) {

		String id = (String) json.get("id");

		IdCheckResultVO idCheck = new IdCheckResultVO();
		ArrayList<UserInfoVO> idCheckResult = new ArrayList<UserInfoVO>();

		try {

			idCheckResult = dataMapper.idCheck(id);
			// logger.debug("user check > " + idCheckResult.size());
			if (idCheckResult.isEmpty()) {
				idCheck.setSuccess(true);
				idCheck.setMessage("����� �� �ִ� ���̵��Դϴ�");

			} else {
				idCheck.setSuccess(false);
				idCheck.setMessage("�Է��Ͻ� ���̵�� �̹� �ִ� ���̵� �Դϴ�. �ٸ� ���̵� �Է����ּ���");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return idCheck;

	}

	// ȸ������
	@RequestMapping(value = "/member", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody SignUpResultVO signUpCheck(@RequestBody Map<String, Object> json) {

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

			dataMapper.signUpCheck(memCode, id, pw, name, nickname, tmCode, birth, recBalloon, balloon, icon, sesAbsent,
					stuAbsent, totPenalty);
			signUp.setSuccess(true);
			signUp.setMessage("ȯ���մϴ�^_^");

		} catch (Exception e) {

			signUp.setSuccess(false);
			signUp.setMessage("�Է��� ���̵�� �̹� �ִ� ���̵��Դϴ�. �ٸ� ���̵�� �������ּ���");
			e.printStackTrace();
		}
		return signUp;

	}

	// ȸ������(member table)���
	@RequestMapping(value = "/member", method = RequestMethod.GET) // #getUsersInfo => getUserInfo
	public @ResponseBody UserInfoResultVO getUserInfo(
			@RequestParam(value = "memCode", required = false) Integer memCode,
			@RequestParam(value = "tmCode", required = false) Integer tmCode) {
		UserInfoResultVO result = new UserInfoResultVO();
		List<UserInfoVO> userInfoList = new ArrayList<UserInfoVO>();
		if (memCode != null) {
			try {

				userInfoList = dataMapper.getUserInfoByMemCode(memCode);//memCode�� �������� �����ð��� ���

				result.setSuccess(true);
				result.setResultUserInfo(userInfoList);
			} catch (Exception e) {

				e.printStackTrace();

				result.setSuccess(false);
				result.setResultUserInfo(null);
			}
		} else if (tmCode != null) {
			try {

				userInfoList = dataMapper.getUserInfoByTeam(tmCode); //���͵� �޸� ����Ҷ� ������ �ĺ� ���

				result.setSuccess(true);
				result.setResultUserInfo(userInfoList);
			} catch (Exception e) {

				e.printStackTrace();

				result.setSuccess(false);
				result.setResultUserInfo(null);
			}
		} else {
			try {

				userInfoList = dataMapper.getUserInfo(); // ��ü ȸ������ ���

				result.setSuccess(true);
				result.setResultUserInfo(userInfoList);
			} catch (Exception e) {

				e.printStackTrace();

				result.setSuccess(false);
				result.setResultUserInfo(null);
			}
		}

		return result;
	}
	
	// ���� ���� ���͵� ��� ���
		@RequestMapping(value = "/getStuAbsent", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody UserInfoResultVO getStuAbsent(@RequestBody Map<String, Object> json) {// ���̽����� �������

			int memCode = (int) json.get("memCode");

			UserInfoResultVO GetStuAbsent = new UserInfoResultVO();// �Լ�
			ArrayList<UserInfoVO> totStuAbsent = new ArrayList<UserInfoVO>();// ����Ʈ

			try {

				totStuAbsent = dataMapper.getStuAbsent(memCode);

				if (totStuAbsent.size() > 0) {

					GetStuAbsent.setSuccess(true);
					GetStuAbsent.setGetStuAbsent(totStuAbsent);

				}

				else {
					GetStuAbsent.setSuccess(false);
				}

			} catch (Exception e) {
				e.printStackTrace();

			}

			return GetStuAbsent;
		}
	
		// ���͵� ������ ����Ʈ ���
		@RequestMapping(value = "/selectAbsentee", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody UserInfoResultVO SelectAbsentee(@RequestBody Map<String, Object> json) {// ���̽����� �������

			int tmCode = (int) json.get("tmCode");

			UserInfoResultVO SelectAbsentee = new UserInfoResultVO();// �Լ�
			ArrayList<UserInfoVO> tmMemberList = new ArrayList<UserInfoVO>();// ����Ʈ

			try {

				tmMemberList = dataMapper.selectAbsentee(tmCode);

				if (tmMemberList.size() > 0) {

					SelectAbsentee.setSuccess(true);
					SelectAbsentee.setSelectAbsentee(tmMemberList);

				}

				else {
					SelectAbsentee.setSuccess(false);
				}

			} catch (Exception e) {
				e.printStackTrace();

			}

			return SelectAbsentee;
		}
	
		
		//������ ��ŷ
		@RequestMapping(value = "/getPenaltyRanking", method = RequestMethod.GET)
		public @ResponseBody UserInfoResultVO getPenaltyRanking() {

			UserInfoResultVO result = new UserInfoResultVO();
			
			List<UserInfoVO> penaltyRankingList = new ArrayList<UserInfoVO>();

			try {

				penaltyRankingList = dataMapper.getPenaltyRanking();

				result.setSuccess(true);
				result.setResultUserInfo(penaltyRankingList);
			} catch (Exception e) {

				e.printStackTrace();

				result.setSuccess(false);
				result.setResultUserInfo(null);
			}

			return result;
		}
		
		
		
		
		
	
	
	
		
	// team=========================================================================================================================================

	@RequestMapping(value = "/team", method = RequestMethod.GET)
	public @ResponseBody TeamInfoResultVO getTeamInfo() {

		TeamInfoResultVO result = new TeamInfoResultVO();
		List<TeamInfoVO> teamList = new ArrayList<TeamInfoVO>();

		try {

			teamList = dataMapper.getTeamsInfo();

			result.setSuccess(true);
			result.setResultTeamInfo(teamList);
		} catch (Exception e) {

			e.printStackTrace();

			result.setSuccess(false);
			result.setResultTeamInfo(null);
		}

		return result;
	}
	
	
	
	
	
	
	

	
	// studyAttendance===============================================================================================================
	
	//���͵���� ���
	@RequestMapping(value = "/studyAttendance", method = RequestMethod.GET)
	public @ResponseBody StudyAttendanceResultVO getStudyAttendance() {

		StudyAttendanceResultVO result = new StudyAttendanceResultVO();
		
		List<StudyAttendanceVO> stuAttendanceList = new ArrayList<StudyAttendanceVO>();

		try {

			stuAttendanceList = dataMapper.getStudyAttendance();

			result.setSuccess(true);
			result.setResultStuAttendance(stuAttendanceList);
		} catch (Exception e) {

			e.printStackTrace();

			result.setSuccess(false);
			result.setResultStuAttendance(null);
		}

		return result;
	}
	
	// ���͵�޸� �۾���
		@RequestMapping(value = "/studyAttendance", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody PostStuMemoResultVO postStudyAttendance(@RequestBody Map<String, Object> json) {

			PostStuMemoResultVO postStuMemo = new PostStuMemoResultVO();

			int stuCode = (int) json.get("stuCode");
			int tmCode = (int) json.get("tmCode");
			String picture = (String) json.get("picture");
			String absentee = (String) json.get("absentee");
			String memo = (String) json.get("memo");
			String uploadTime = (String) json.get("uploadTime");

			try {

				dataMapper.postStudyAttendance(stuCode, tmCode, picture, absentee, memo, uploadTime);
				postStuMemo.setSuccess(true);
				postStuMemo.setMessage("���� ��ϵǾ����ϴ�");

				String[] absentee_array = absentee.split(","); // *****json �Ľ��ؼ� �̸��� ����
				for (String name : absentee_array) { // *****�Ľ̵� �̸��� ����� �ἮȽ���� 1 ����
					try { // *****sql ������ �� �� ������ try catch.
						dataMapper.updateStuAbsent(name);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			} catch (Exception e) {

				postStuMemo.setSuccess(false);
				postStuMemo.setMessage("���� ������� ���߽��ϴ�");
				e.printStackTrace();
			}
			return postStuMemo;

		}
	
	
	
	
	
	
	// study========================================================================================================
	
	//������ ���͵� ���
	@RequestMapping(value = "/study", method = RequestMethod.GET) 
	public @ResponseBody StudyManageResultVO getStudy() {

		StudyManageResultVO result = new StudyManageResultVO();

		List<StudyManageVO> studyList = new ArrayList<StudyManageVO>();

		try {

			studyList = dataMapper.getStudy();

			result.setSuccess(true);
			result.setResultStudyManage(studyList);
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();

			result.setSuccess(false);
			result.setResultStudyManage(null);
		}

		return result;
	}
	
	

	
	
	
	// sessinAttendance===============================================================================================================

	// ���� ������� ���
	@RequestMapping(value = "/sessionAttendance", method = RequestMethod.GET)
	public @ResponseBody SessionAttendanceResultVO getSessionAttendance() {

		SessionAttendanceResultVO result = new SessionAttendanceResultVO();

		List<SessionAttendanceVO> sesAttendanceList = new ArrayList<SessionAttendanceVO>();
		try {

			sesAttendanceList = dataMapper.getSessionAttendance();

			result.setSuccess(true);
			result.setResultSesAttendance(sesAttendanceList);
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();

			result.setSuccess(false);
			result.setResultSesAttendance(null);
		}

		return result;
	}

	// ���� ������� �Է�
	@RequestMapping(value = "/sessionAttendance", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody SessionAttendanceResultVO postSessionAttendance(@RequestBody Map<String, Object> json) {

		SessionAttendanceResultVO PostSessionAttendance = new SessionAttendanceResultVO();

		int sesCode = (int) json.get("sesCode");
		int memCode = (int) json.get("memCode");
		int sesAttendance = (int) json.get("sesAttendance");
		int late = (int) json.get("late");
		int penalty = (int) json.get("penalty");

		try {

			dataMapper.postSessionAttendance(sesCode, memCode, sesAttendance, late, penalty);
			PostSessionAttendance.setSuccess(true);
			PostSessionAttendance.setMessage("���� ��������� ��ϵǾ����ϴ�");
			
			if (sesAttendance == 0) {//�⼮������ 0�̸� �ἮȽ���� 1����
				try {
					dataMapper.updateSesAbsent(memCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {

			PostSessionAttendance.setSuccess(false);
			PostSessionAttendance.setMessage("���� ��������� ������� ���߽��ϴ�");
			e.printStackTrace();
		}
		return PostSessionAttendance;

	}

	
	
	
	
	
	// session============================================================================================================================================

	// �������� ���
	@RequestMapping(value = "/session", method = RequestMethod.GET)
	public @ResponseBody SessionManageResultVO getSession() {

		SessionManageResultVO result = new SessionManageResultVO();
		List<SessionManageVO> sessionList = new ArrayList<SessionManageVO>();

		try {

			sessionList = dataMapper.getSession();

			result.setSuccess(true);
			result.setResultSessionManage(sessionList);
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();

			result.setSuccess(false);
			result.setResultSessionManage(null);
		}

		return result;
	}

	// �������� �Է�
	@RequestMapping(value = "/session", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody SessionManageResultVO postSession(@RequestBody Map<String, Object> json) {

		SessionManageResultVO postSession = new SessionManageResultVO();

		int sesCode = (int) json.get("sesCode");
		String sesDate = (String) json.get("sesDate");
		String sesInfo = (String) json.get("sesInfo");
		String sesContent = (String) json.get("sesContent");

		try {

			dataMapper.postSession(sesCode, sesDate, sesInfo, sesContent);
			postSession.setSuccess(true);
			postSession.setMessage("���� ��ϵǾ����ϴ�");

		} catch (Exception e) {

			postSession.setSuccess(false);
			postSession.setMessage("���� ������� ���߽��ϴ�");
			e.printStackTrace();
		}
		return postSession;

	}
	
	
	

	
	
	

	// freeBoard=================================================================================================================================================

	// �����Խ���(free table)���
	@RequestMapping(value = "/freeBoard", method = RequestMethod.GET)
	public @ResponseBody FreeBoardResultVO getFreeBoard(
			@RequestParam(value = "memCode", required = false) Integer memCode, 
			@RequestParam(value = "keyword", required = false) String keyword) {

		FreeBoardResultVO result = new FreeBoardResultVO();
		List<FreeBoardVO> freeBoardList = new ArrayList<FreeBoardVO>();
		
		if (memCode != null) { //���� ���� ����

			try {

				freeBoardList = dataMapper.getFreeBoardByMemCode(memCode);

				result.setSuccess(true);
				result.setResultFreeBoard(freeBoardList);
			} catch (Exception e) {
				
				e.printStackTrace();

				result.setSuccess(false);
				result.setResultFreeBoard(null);
			}
		} else if (keyword != null) { //Ű����� �� �˻�(����,�������� ��ġ�ϴ� ��� ���)
			//########################ERROR##########################
			try {

				freeBoardList =  dataMapper.getFreeBoardByKeyword(keyword);

				result.setSuccess(true);
				result.setResultFreeBoard(freeBoardList);
			} catch (Exception e) {
				
				e.printStackTrace();

				result.setSuccess(false);
				result.setMessage("�˻� ����� �����ϴ�.");
				result.setResultFreeBoard(null);
			}
			
			
		} else { //�� ����Ʈ ���
			
			try {

				freeBoardList = dataMapper.getFreeBoard();

				result.setSuccess(true);
				result.setResultFreeBoard(freeBoardList);
			} catch (Exception e) {
				
				e.printStackTrace();

				result.setSuccess(false);
				result.setResultFreeBoard(null);
			}
			
		}
		
		
		
		return result;
	}

	// �����Խ��� �۾���
	@RequestMapping(value = "/freeBoard", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PostFreeResultVO PostFreeBoard(@RequestBody Map<String, Object> json) {

		PostFreeResultVO result = new PostFreeResultVO();

		int freNum = (int) json.get("freNum");
		String freTitle = (String) json.get("freTitle");
		String freContent = (String) json.get("freContent");
		String freDate = (String) json.get("freDate");
		int memCode = (int) json.get("memCode");

		try {

			dataMapper.postFreeBoard(freNum, freTitle, freContent, freDate, memCode);
			result.setSuccess(true);
			result.setMessage("���� ��ϵǾ����ϴ�");

		} catch (Exception e) {

			result.setSuccess(false);
			result.setMessage("���� ������� ���߽��ϴ�");
			e.printStackTrace();
		}
		return result;

	}

	@RequestMapping(value = "/freeBoard", method = RequestMethod.DELETE)
	public @ResponseBody DeleteFreeResultVO DeleteFreeBoard(
			@RequestParam(value = "freNum", required = false) Integer freNum,
			@RequestParam(value = "memCode", required = false) Integer memCode) {

		DeleteFreeResultVO result = new DeleteFreeResultVO();

		int authorCode = dataMapper.getFreeBoardByFreNum(freNum).getMemCode();
		if (memCode == authorCode) { // ���⿡ ��� �� ���� ���� �߰��ϱ�!
			try {
				dataMapper.deleteFreeBoard(freNum);
				result.setSuccess(true);
				result.setMessage("���� �����Ǿ����ϴ�.");
			} catch (Exception e) {
				// TODO: handle exception

				e.printStackTrace();

				result.setSuccess(false);
				result.setMessage("���� �������� ���߽��ϴ�.");
			}
		} else {
			result.setSuccess(false);
			result.setMessage("�ۼ��� �� ����� ���� ������ �� �ֽ��ϴ�.");
		}

		return result;
	}

}
