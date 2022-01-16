package com.buybook.DAO;

import java.util.*;

import javax.sql.*;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import com.buybook.DTO.*;

@Component
public class GoodDAO {
	private JdbcTemplate jdbcTemplate;

	public GoodDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public GoodDTO selectByGoodNo(int inputGoodNo) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM GOOD WHERE GOODNO = " + inputGoodNo + ";",
					(rs, rowNum) -> new GoodDTO(rs.getInt("GOODNO"), rs.getString("GOODISBN"),
							rs.getString("GOODTITLE"), rs.getString("GOODCONTENT"), rs.getString("GOODIMAGE"),
							rs.getTimestamp("GOODDATE")));
		} catch (Exception ex) {
			ex.printStackTrace();

			return null;
		}
	}

	public List<GoodDTO> showAll() {
		List<GoodDTO> result = jdbcTemplate.query("SELECT * FROM GOOD;", (rs, rowNum) -> {
			GoodDTO goodDTO = new GoodDTO(rs.getInt("GOODNO"), rs.getString("GOODISBN"), rs.getString("GOODTITLE"),
					rs.getString("GOODCONTENT"), rs.getString("GOODIMAGE"), rs.getTimestamp("GOODDATE"));
			return goodDTO;
		});

		return result;
	}

	public List<GoodDTO> showThree() {
		List<GoodDTO> result = jdbcTemplate.query("SELECT * FROM GOOD ORDER BY GOODNO DESC LIMIT 3;", (rs, rowNum) -> {
			GoodDTO goodDTO = new GoodDTO(rs.getInt("GOODNO"), rs.getString("GOODISBN"), rs.getString("GOODTITLE"),
					rs.getString("GOODCONTENT"), rs.getString("GOODIMAGE"), rs.getTimestamp("GOODDATE"));
			return goodDTO;
		});

		return result;
	}

	public void insertGood(GoodDTO goodDTO) {
		jdbcTemplate.update("INSERT INTO GOOD(GOODISBN, GOODTITLE, GOODCONTENT, GOODIMAGE, GOODDATE) VALUES('"
				+ goodDTO.getGoodISBN() + "', '" + goodDTO.getGoodTitle() + "', '" + goodDTO.getGoodContent() + "', '"
				+ goodDTO.getGoodImage() + "', NOW());");
	}

	public void deleteGood(int inputGoodNo) {
		jdbcTemplate.update("DELETE FROM GOOD WHERE GOODNO = " + inputGoodNo + ";");
	}
}
