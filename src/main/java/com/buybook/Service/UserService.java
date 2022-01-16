package com.buybook.Service;

import java.util.*;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buybook.DAO.UserDAO;
import com.buybook.DTO.UserDTO;

@Service
public class UserService {
	private UserDAO userDAO;

	@Autowired
	public UserService(UserDAO _userDAO) {
		this.userDAO = _userDAO;
	}

	// 회원 한 명 찾기
	public UserDTO findOneUser(String inputUserEmail) {
		UserDTO userDTO = userDAO.selectByUserEmail(inputUserEmail);

		if (userDTO == null)
			return null;
		else
			return userDTO;
	}

	// 회원 목록
	public List<UserDTO> showAll() {
		return userDAO.showAll();
	}

	// 회원가입
	public UserDTO userSignUp(String inputUserEmail, String inputUserPwd, String inputUserName, String inputUserPhone,
			String inputUserAddress) {
		UserDTO userDTO = userDAO.selectByUserEmail(inputUserEmail);

		if (userDTO == null) { // 회원이 존재하지 않음 --> 회원가입 진행
			userDTO = new UserDTO(inputUserEmail, inputUserPwd, inputUserName, inputUserPhone, inputUserAddress);

			userDAO.insertUser(userDTO);

			return userDTO; // 가입한 계정 반환
		} else { // 회원이 이미 존재함
			System.out.println("이미 존재하는 계정입니다.");

			return null;
		}
	}

	// 로그인
	public UserDTO userSignIn(String inputUserEmail, String inputUserPwd) {
		UserDTO userDTO = userDAO.selectByUserEmail(inputUserEmail); // 입력된 정보로 회원 찾기

		if (userDTO == null) { // 회원이 존재하지 않음
			System.out.println("회원이 존재하지 않습니다.");

			return null;
		} else if (!userDTO.getUserPwd().equals(inputUserPwd)) { // 비밀번호 불일치
			System.out.println("비밀번호 오류입니다.");

			return null;
		}

		return userDTO;
	}

	// 비밀번호 변경
	public void changeUserPwd(UserDTO userDTO, String inputUserNewPwd) {
		userDAO.updatePwd(userDTO, inputUserNewPwd);
	}
	
	// 이름 변경
	public void changeUserName(UserDTO userDTO, String inputUserName) {
		userDAO.updateName(userDTO, inputUserName);
	}
	
	// 연락처 변경
	public void changeUserPhone(UserDTO userDTO, String inputUserPhone) {
		userDAO.updatePhone(userDTO, inputUserPhone);
	}
	
	// 주소 변경
	public void changeUserAddress(UserDTO userDTO, String inputUserAddress) {
		userDAO.updateAddress(userDTO, inputUserAddress);
	}

	// 임시 비밀번호 생성
	public UserDTO changeUserPwd(UserDTO userDTO) {
		StringBuffer newPwd = new StringBuffer();

		Random rand = new Random();

		// 임시 비밀번호 생성
		for (int i = 0; i < 10; i++) {
			int index = rand.nextInt(3);
			switch (index) {
			case 0:
				newPwd.append((char) ((int) (rand.nextInt(26)) + 97));
				break;
			case 1:
				newPwd.append((char) ((int) (rand.nextInt(26)) + 65));
				break;
			case 2:
				newPwd.append((rand.nextInt(10)));
				break;
			}
		}

		userDAO.updatePwd(userDTO, newPwd.toString());

		return userDAO.selectByUserEmail(userDTO.getUserEmail());
	}

	public void sendPasswordEmail(UserDTO userDTO, String div) throws Exception {
		// 비밀번호를 잊었을 시에 초기화 해주는 기능
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com"; // 지메일 이용시 smtp.gmail.com
		String hostSMTPid = ""; // "서버 이메일 주소(보내는 사람 이메일 주소)";
		String hostSMTPpwd = ""; // "서버 이메일 비번(보내는 사람 이메일 비번)";

		// 보내는 사람 Email, 제목, 내용
		String fromEmail = "admin@admin"; // "보내는 사람 이메일주소(받는 사람 이메일에 표시됨)";
		String fromName = ""; // "프로젝트이름 또는 보내는 사람 이름";
		String subject = "";
		String msg = "";

		if (div.equals("userForgotPwd")) {
			subject = "Buy Book 임시 비밀번호입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += userDTO.getUserEmail() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += userDTO.getUserPwd() + "</p></div>";
		}

		// 받는 사람 E-Mail 주소
		String mail = userDTO.getUserEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465); // 지메일 이용시 587

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}
}
