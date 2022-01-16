package com.buybook.DAO;

import java.util.*;

import javax.sql.*;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import com.buybook.DTO.*;

@Component
public class CommentDAO {
	private CommentDTO commentDTO;
	private JdbcTemplate jdbcTemplate;

	public CommentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<CommentDTO> selectByBoardNo(int inputBoardNo) {
		List<CommentDTO> result = jdbcTemplate
				.query("SELECT * FROM COMMENT WHERE COMMENTBOARDNO = " + inputBoardNo + ";", (rs, rowNum) -> {
					CommentDTO commentDTO = new CommentDTO(rs.getInt("COMMENTNO"), rs.getInt("COMMENTBOARDNO"),
							rs.getString("COMMENTNAME"), rs.getString("COMMENTCONTENT"));
					return commentDTO;
				});
		return result;
	}

	public int findLatestCommentNo(int inputBoardNo) {
		try {
			this.commentDTO = jdbcTemplate.queryForObject(
					"SELECT * FROM COMMENT WHERE COMMENTBOARDNO = " + inputBoardNo
							+ " ORDER BY COMMENTNO DESC LIMIT 1;",
					(rs, rowNum) -> new CommentDTO(rs.getInt("COMMENTNO"), rs.getInt("COMMENTBOARDNO"),
							rs.getString("COMMENTNAME"), rs.getString("COMMENTCONTENT")));
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}

		return commentDTO.getCommentNo();
	}

	public void insertComment(CommentDTO commentDTO) {
		jdbcTemplate.update("INSERT INTO COMMENT(COMMENTNO, COMMENTBOARDNO, COMMENTNAME, COMMENTCONTENT) VALUES("
				+ commentDTO.getCommentNo() + ", " + commentDTO.getCommentBoardNo() + ", '"
				+ commentDTO.getCommentName() + "', '" + commentDTO.getCommentContent() + "');");
	}

	public void deleteComment(int inputCommentNo, int inputCommentBoardNo) {
		jdbcTemplate.update("DELETE FROM COMMENT WHERE COMMENTNO = " + inputCommentNo + " AND COMMENTBOARDNO = "
				+ inputCommentBoardNo + ";");
	}
}
