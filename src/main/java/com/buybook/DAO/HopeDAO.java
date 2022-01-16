package com.buybook.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.buybook.DTO.*;

@Component
public class HopeDAO {
	private JdbcTemplate jdbcTemplate;

	public HopeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<HopeDTO> showAll() {
		List<HopeDTO> result = jdbcTemplate.query("SELECT * FROM HOPE;", (rs, rowNum) -> {
			HopeDTO hopeDTO = new HopeDTO(rs.getString("HOPEISBN"), rs.getString("HOPETITLE"), rs.getInt("HOPENUMBER"),
					rs.getString("HOPELINK"));
			return hopeDTO;
		});

		return result;
	}

	// 희망 도서 ISBN 으로 한 권 가져오기
	public HopeDTO selectByHopeISBN(String inputHopeISBN) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM HOPE WHERE HOPEISBN = '" + inputHopeISBN + "';",
					(rs, rowNum) -> new HopeDTO(rs.getString("HOPEISBN"), rs.getString("HOPETITLE"),
							rs.getInt("HOPENUMBER"), rs.getString("HOPELINK")));
		} catch (Exception ex) {
			ex.printStackTrace();

			return null;
		}
	}

	// 희망 도서 추가
	public void insertHope(HopeDTO hopeDTO) {
		jdbcTemplate.update("INSERT INTO HOPE(HOPEISBN, HOPETITLE, HOPELINK) VALUES('" + hopeDTO.getHopeISBN() + "', '"
				+ hopeDTO.getHopeTitle() + "', '" + hopeDTO.getHopeLink() + "');");
	}

	// 희망 도서 인원수 + 1
	public void updateHope(String inputHopeISBN) {
		jdbcTemplate.update("UPDATE HOPE SET HOPENUMBER = HOPENUMBER + 1 WHERE HOPEISBN = '" + inputHopeISBN + "';");
	}
}
