package com.buybook.DAO;

import java.util.*;

import javax.sql.*;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import com.buybook.DTO.*;

@Component
public class OrderDAO {
	private JdbcTemplate jdbcTemplate;

	public OrderDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 주문 추가
	public void insertOrder(OrderDTO orderDTO) {
		jdbcTemplate.update(
				"INSERT INTO ORDERED(ORDEREDEMAIL, ORDEREDISBN, ORDEREDTITLE, ORDEREDCOUNT, ORDEREDPRICE, ORDEREDDATE) VALUES('"
						+ orderDTO.getOrderedEmail() + "', '" + orderDTO.getOrderedISBN() + "', '"
						+ orderDTO.getOrderedTitle() + "', " + orderDTO.getOrderedCount() + ", "
						+ orderDTO.getOrderedPrice() + ", NOW());");
	}

	// 모든 주문 출력
	public List<OrderDTO> showAll(String inputUserEmail) {
		List<OrderDTO> result = jdbcTemplate
				.query("SELECT * FROM ORDERED WHERE ORDEREDEMAIL = '" + inputUserEmail + "';", (rs, rowNum) -> {
					OrderDTO orderDTO = new OrderDTO(rs.getInt("ORDEREDID"), rs.getString("ORDEREDEMAIL"),
							rs.getString("ORDEREDISBN"), rs.getString("ORDEREDTITLE"), rs.getInt("ORDEREDCOUNT"),
							rs.getInt("ORDEREDPRICE"), rs.getTimestamp("ORDEREDDATE"));
					return orderDTO;
				});

		return result;
	}
}
