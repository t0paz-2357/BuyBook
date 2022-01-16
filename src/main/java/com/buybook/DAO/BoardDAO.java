package com.buybook.DAO;

import java.util.*;

import javax.sql.*;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import com.buybook.DTO.*;

@Component
public class BoardDAO {
	private JdbcTemplate jdbcTemplate;

	public BoardDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public BoardDTO selectByBoardNo(int inputBoardNo) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM BOARD WHERE BOARDNO = " + inputBoardNo + ";",
					(rs, rowNum) -> new BoardDTO(rs.getInt("BOARDNO"), rs.getString("BOARDEMAIL"),
							rs.getString("BOARDNAME"), rs.getString("BOARDTITLE"), rs.getString("BOARDCONTENT"),
							rs.getTimestamp("BOARDDATE"), rs.getString("BOARDPUBLIC")));
		} catch (Exception ex) {
			return null;
		}
	}

	public List<BoardDTO> showAll() {
		List<BoardDTO> result = jdbcTemplate.query("SELECT * FROM BOARD ORDER BY BOARDNO DESC;", (rs, rowNum) -> {
			BoardDTO boardDTO = new BoardDTO(rs.getInt("BOARDNO"), rs.getString("BOARDEMAIL"),
					rs.getString("BOARDNAME"), rs.getString("BOARDTITLE"), rs.getString("BOARDCONTENT"),
					rs.getTimestamp("BOARDDATE"), rs.getString("BOARDPUBLIC"));
			return boardDTO;
		});
		return result;
	}

	public List<BoardDTO> showVisibleBoard() {
		List<BoardDTO> result = jdbcTemplate.query("SELECT * FROM BOARD WHERE BOARDPUBLIC = 'Y' ORDER BY BOARDNO DESC;", (rs, rowNum) -> {
			BoardDTO boardDTO = new BoardDTO(rs.getInt("BOARDNO"), rs.getString("BOARDEMAIL"),
					rs.getString("BOARDNAME"), rs.getString("BOARDTITLE"), rs.getString("BOARDCONTENT"),
					rs.getTimestamp("BOARDDATE"), rs.getString("BOARDPUBLIC"));
			return boardDTO;
		});
		return result;
	}

	public List<BoardDTO> showVisibleThree() {
		List<BoardDTO> result = jdbcTemplate
				.query("SELECT * FROM BOARD WHERE BOARDPUBLIC = 'Y' ORDER BY BOARDNO DESC LIMIT 3;", (rs, rowNum) -> {
					BoardDTO boardDTO = new BoardDTO(rs.getInt("BOARDNO"), rs.getString("BOARDEMAIL"),
							rs.getString("BOARDNAME"), rs.getString("BOARDTITLE"), rs.getString("BOARDCONTENT"),
							rs.getTimestamp("BOARDDATE"), rs.getString("BOARDPUBLIC"));
					return boardDTO;
				});
		return result;
	}

	public void updatePublic(int inputBoardNo, String newBoardPublic) {
		jdbcTemplate.update(
				"UPDATE BOARD SET BOARDPUBLIC = '" + newBoardPublic + "' WHERE BOARDNO = " + inputBoardNo + ";");
	}

	public void insertBoard(BoardDTO boardDTO) {
		jdbcTemplate.update("INSERT INTO BOARD(BOARDEMAIL, BOARDNAME, BOARDTITLE, BOARDCONTENT, BOARDDATE) VALUES('"
				+ boardDTO.getBoardEmail() + "', '" + boardDTO.getBoardName() + "', '" + boardDTO.getBoardTitle()
				+ "', '" + boardDTO.getBoardContent() + "', NOW());");
	}

	public void deleteBoard(int inputBoardNo) {
		jdbcTemplate.update("DELETE FROM BOARD WHERE BOARDNO = " + inputBoardNo + ";");
	}
}
