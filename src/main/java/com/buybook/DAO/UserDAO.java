package com.buybook.DAO;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;
import java.util.*;
import javax.sql.*;

import com.buybook.DTO.*;

@Component
public class UserDAO {
	private JdbcTemplate jdbcTemplate;

	public UserDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public UserDTO selectByUserEmail(String inputUserEmail) {
		// 한 명의 회원 조회
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE USEREMAIL='" + inputUserEmail + "';",
					(rs, rowNum) -> new UserDTO(rs.getString("USEREMAIL"), rs.getString("USERPWD"),
							rs.getString("USERNAME"), rs.getString("USERPHONE"), rs.getString("USERADDRESS")));
		} catch (Exception ex) {
			return null;
		}
	}

	public void insertUser(UserDTO userDTO) {
		// 회원가입
		jdbcTemplate.update("INSERT INTO USER(USEREMAIL, USERPWD, USERNAME, USERPHONE, USERADDRESS) VALUES('"
				+ userDTO.getUserEmail() + "', '" + userDTO.getUserPwd() + "', '" + userDTO.getUserName() + "', '"
				+ userDTO.getUserPhone() + "', '" + userDTO.getUserAddress() + "');");
	}

	public void deleteUser(String inputUserEmail) {
		// 회원 삭제
		jdbcTemplate.update("DELETE FROM USER WHERE USEREMAIL='" + inputUserEmail + "';");
	}

	public void updatePwd(UserDTO userDTO, String inputUserNewPwd) {
		// 회원 비밀번호 수정
		jdbcTemplate.update("UPDATE USER SET USERPWD = '" + inputUserNewPwd + "' WHERE USEREMAIL = '"
				+ userDTO.getUserEmail() + "';");
	}

	public void updateName(UserDTO userDTO, String inputUserName) {
		// 회원 이름 변경
		jdbcTemplate.update("UPDATE USER SET USERNAME = '" + inputUserName + "' WHERE USEREMAIL = '"
				+ userDTO.getUserEmail() + "';");
	}

	public void updatePhone(UserDTO userDTO, String inputUserPhone) {
		// 회원 연락처 변경
		jdbcTemplate.update("UPDATE USER SET USERPHONE = '" + inputUserPhone + "' WHERE USEREMAIL = '"
				+ userDTO.getUserEmail() + "';");
	}

	public void updateAddress(UserDTO userDTO, String inputUserAddress) {
		// 회원 주소 변경
		jdbcTemplate.update("UPDATE USER SET USERADDRESS = '" + inputUserAddress + "' WHERE USEREMAIL = '"
				+ userDTO.getUserEmail() + "';");
	}

	public List<UserDTO> showAll() {
		List<UserDTO> result = jdbcTemplate.query("SELECT * FROM USER;", (rs, rowNum) -> {
			UserDTO userDTO = new UserDTO(rs.getString("USEREMAIL"), rs.getString("USERPWD"), rs.getString("USERNAME"),
					rs.getString("USERPHONE"), rs.getString("USERADDRESS"));
			return userDTO;
		});

		return result;
	}
}
