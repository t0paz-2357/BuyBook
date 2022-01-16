package com.buybook.DAO;

import java.util.*;

import javax.sql.*;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import com.buybook.DTO.*;

@Component
public class CartDAO {
	private JdbcTemplate jdbcTemplate;

	public CartDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 해당 회원의 장바구니 목록 출력
	public List<CartDTO> showUserCart(String inputCartEmail) {
		List<CartDTO> result = jdbcTemplate.query("SELECT * FROM CART WHERE CARTEMAIL = '" + inputCartEmail + "';",
				(rs, rowNum) -> {
					CartDTO cartDTO = new CartDTO(rs.getString("CARTEMAIL"), rs.getString("CARTISBN"),
							rs.getString("CARTIMAGE"), rs.getString("CARTTITLE"), rs.getInt("CARTCOUNT"),
							rs.getInt("CARTPRICE"));
					return cartDTO;
				});

		return result;
	}

	// 장바구니 항목 한 개 가져오기
	public CartDTO getCartItem(String inputCartEmail, String inputCartISBN) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM CART WHERE CARTEMAIL = '" + inputCartEmail + "' AND CARTISBN = '" + inputCartISBN
							+ "';",
					(rs, rowNum) -> new CartDTO(rs.getString("CARTEMAIL"), rs.getString("CARTISBN"),
							rs.getString("CARTIMAGE"), rs.getString("CARTTITLE"), rs.getInt("CARTCOUNT"),
							rs.getInt("CARTPRICE")));
		} catch (Exception ex) {
			return null;
		}
	}

	// 장바구니에 추가하기
	public void insertCart(CartDTO cartDTO) {
		jdbcTemplate.update("INSERT INTO CART(CARTEMAIL, CARTISBN, CARTIMAGE, CARTTITLE, CARTCOUNT, CARTPRICE) VALUES('"
				+ cartDTO.getCartEmail() + "', '" + cartDTO.getCartISBN() + "', '" + cartDTO.getCartImage() + "', '"
				+ cartDTO.getCartTitle() + "', " + cartDTO.getCartCount() + ", " + cartDTO.getCartPrice() + ");");

	}

	// 장바구니에서 해당 회원의 기록 없애기
	public void deleteCart(String inputCartEmail) {
		jdbcTemplate.update("DELETE FROM CART WHERE CARTEMAIL = '" + inputCartEmail + "';");
	}

	// 주문 내역으로 추가하기
	public void insertOrder(CartDTO cartDTO) {
		jdbcTemplate.update(
				"INSERT INTO ORDERED(ORDEREDEMAIL, ORDEREDISBN, ORDEREDTITLE, ORDEREDCOUNT, ORDEREDPRICE, ORDEREDDATE) VALUES('"
						+ cartDTO.getCartEmail() + "', '" + cartDTO.getCartISBN() + "', '" + cartDTO.getCartTitle()
						+ "', " + cartDTO.getCartCount() + ", " + cartDTO.getCartPrice() + ", NOW());");
	}

	// 장바구니 항목 하나 지우기
	public void deleteCartItem(String inputCartEmail, String inputCartISBN) {
		jdbcTemplate.update(
				"DELETE FROM CART WHERE CARTEMAIL = '" + inputCartEmail + "' AND CARTISBN = '" + inputCartISBN + "';");
	}

}
