package com.buybook.DAO;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;
import javax.sql.*;

import com.buybook.DTO.*;

@Component
public class PhraseDAO {
	private JdbcTemplate jdbcTemplate;

	public PhraseDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public PhraseDTO selectOnePhrase() {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM PHRASE ORDER BY RAND() LIMIT 1;",
					(rs, rowNum) -> new PhraseDTO(rs.getInt("PHRASENO"), rs.getString("PHRASESTRING")));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
