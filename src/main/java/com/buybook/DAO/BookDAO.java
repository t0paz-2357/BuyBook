package com.buybook.DAO;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.buybook.DTO.BookDTO;

@Component
public class BookDAO {
	private JdbcTemplate jdbcTemplate;

	public BookDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 한 권의 도서 조회
	public BookDTO selectByBookISBN(String inputBookISBN) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM BOOK WHERE BOOKISBN = '" + inputBookISBN + "';",
					(rs, rowNum) -> new BookDTO(rs.getString("BOOKISBN"), rs.getString("BOOKTITLE"),
							rs.getString("BOOKAUTHOR"), rs.getInt("BOOKPRICE"), rs.getString("BOOKGENRE"),
							rs.getString("BOOKPUBLISHER"), rs.getString("BOOKIMAGE"), rs.getInt("BOOKCOUNT"),
							rs.getString("BOOKSUMMARY"), rs.getTimestamp("BOOKDATE"), rs.getInt("BOOKHIT")));
		} catch (Exception ex) {
			return null;
		}
	}

	// 현재 조회 중인 책을 제외한 동일 장르의 도서 4권
	public List<BookDTO> selectGenreBookList(String inputBookISBN, String inputBookGenre) {
		List<BookDTO> result = jdbcTemplate.query("SELECT * FROM BOOK WHERE BOOKISBN != '" + inputBookISBN
				+ "' AND BOOKGENRE = '" + inputBookGenre + "' ORDER BY RAND() LIMIT 4;", (rs, rowNum) -> {
					BookDTO bookDTO = new BookDTO(rs.getString("BOOKISBN"), rs.getString("BOOKTITLE"),
							rs.getString("BOOKAUTHOR"), rs.getInt("BOOKPRICE"), rs.getString("BOOKGENRE"),
							rs.getString("BOOKPUBLISHER"), rs.getString("BOOKIMAGE"), rs.getInt("BOOKCOUNT"),
							rs.getString("BOOKSUMMARY"), rs.getTimestamp("BOOKDATE"), rs.getInt("BOOKHIT"));
					return bookDTO;
				});

		return result;
	}

	// 신작 도서 10권 목록
	public List<BookDTO> newBookList() {
		List<BookDTO> result = jdbcTemplate.query("SELECT * FROM BOOK ORDER BY BOOKDATE DESC LIMIT 10;",
				(rs, rowNum) -> {
					BookDTO bookDTO = new BookDTO(rs.getString("BOOKISBN"), rs.getString("BOOKTITLE"),
							rs.getString("BOOKAUTHOR"), rs.getInt("BOOKPRICE"), rs.getString("BOOKGENRE"),
							rs.getString("BOOKPUBLISHER"), rs.getString("BOOKIMAGE"), rs.getInt("BOOKCOUNT"),
							rs.getString("BOOKSUMMARY"), rs.getTimestamp("BOOKDATE"), rs.getInt("BOOKHIT"));
					return bookDTO;
				});

		return result;
	}

	// 모든 도서 목록 조회
	public List<BookDTO> showAll() {
		List<BookDTO> result = jdbcTemplate.query("SELECT * FROM BOOK;", (rs, rowNum) -> {
			BookDTO bookDTO = new BookDTO(rs.getString("BOOKISBN"), rs.getString("BOOKTITLE"),
					rs.getString("BOOKAUTHOR"), rs.getInt("BOOKPRICE"), rs.getString("BOOKGENRE"),
					rs.getString("BOOKPUBLISHER"), rs.getString("BOOKIMAGE"), rs.getInt("BOOKCOUNT"),
					rs.getString("BOOKSUMMARY"), rs.getTimestamp("BOOKDATE"), rs.getInt("BOOKHIT"));
			return bookDTO;
		});

		return result;
	}

	// 다섯 권 책 조회
	public List<BookDTO> showFive() {
		List<BookDTO> result = jdbcTemplate.query("SELECT * FROM BOOK ORDER BY BOOKDATE DESC LIMIT 5;",
				(rs, rowNum) -> {
					BookDTO bookDTO = new BookDTO(rs.getString("BOOKISBN"), rs.getString("BOOKTITLE"),
							rs.getString("BOOKAUTHOR"), rs.getInt("BOOKPRICE"), rs.getString("BOOKGENRE"),
							rs.getString("BOOKPUBLISHER"), rs.getString("BOOKIMAGE"), rs.getInt("BOOKCOUNT"),
							rs.getString("BOOKSUMMARY"), rs.getTimestamp("BOOKDATE"), rs.getInt("BOOKHIT"));
					return bookDTO;
				});

		return result;
	}

	// 도서 추가
	public void insertBook(BookDTO bookDTO) {
		jdbcTemplate.update(
				"INSERT INTO BOOK(BOOKISBN, BOOKTITLE, BOOKAUTHOR, BOOKPRICE, BOOKGENRE, BOOKPUBLISHER, BOOKIMAGE, BOOKCOUNT, BOOKSUMMARY, BOOKDATE) VALUES('"
						+ bookDTO.getBookISBN() + "', '" + bookDTO.getBookTitle() + "', '" + bookDTO.getBookAuthor()
						+ "', '" + bookDTO.getBookPrice() + "', '" + bookDTO.getBookGenre() + "', '"
						+ bookDTO.getBookPublisher() + "', '" + bookDTO.getBookImage() + "', '" + bookDTO.getBookCount()
						+ "', '" + bookDTO.getBookSummary() + "', NOW());");
	}

	// 도서 삭제
	public void deleteBook(BookDTO bookDTO) {
		jdbcTemplate.update("DELETE FROM BOOK WHERE BOOKISBN = '" + bookDTO.getBookISBN() + "';");
	}

	// 판매부수 많은 도서 가져오기
	public List<BookDTO> showHitBookList() {
		List<BookDTO> result = jdbcTemplate.query("SELECT * FROM BOOK WHERE BOOKHIT >= 5 ORDER BY BOOKHIT DESC;",
				(rs, rowNum) -> {
					BookDTO bookDTO = new BookDTO(rs.getString("BOOKISBN"), rs.getString("BOOKTITLE"),
							rs.getString("BOOKAUTHOR"), rs.getInt("BOOKPRICE"), rs.getString("BOOKGENRE"),
							rs.getString("BOOKPUBLISHER"), rs.getString("BOOKIMAGE"), rs.getInt("BOOKCOUNT"),
							rs.getString("BOOKSUMMARY"), rs.getTimestamp("BOOKDATE"), rs.getInt("BOOKHIT"));
					return bookDTO;
				});

		return result;
	}

	// 판매부수 많은 도서 3권 가져오기
	public List<BookDTO> showHitBookThree() {
		List<BookDTO> result = jdbcTemplate
				.query("SELECT * FROM BOOK WHERE BOOKHIT >= 5 ORDER BY BOOKHIT DESC LIMIT 3;", (rs, rowNum) -> {
					BookDTO bookDTO = new BookDTO(rs.getString("BOOKISBN"), rs.getString("BOOKTITLE"),
							rs.getString("BOOKAUTHOR"), rs.getInt("BOOKPRICE"), rs.getString("BOOKGENRE"),
							rs.getString("BOOKPUBLISHER"), rs.getString("BOOKIMAGE"), rs.getInt("BOOKCOUNT"),
							rs.getString("BOOKSUMMARY"), rs.getTimestamp("BOOKDATE"), rs.getInt("BOOKHIT"));
					return bookDTO;
				});

		return result;
	}

	// 도서 수정하기
	public void updateBook(BookDTO bookDTO) {
		jdbcTemplate.update("UPDATE BOOK SET BOOKTITLE = '" + bookDTO.getBookTitle() + "', BOOKAUTHOR = '"
				+ bookDTO.getBookAuthor() + "', BOOKPRICE = " + bookDTO.getBookPrice() + ", BOOKGENRE = '"
				+ bookDTO.getBookGenre() + "', BOOKPUBLISHER = '" + bookDTO.getBookPublisher() + "', BOOKIMAGE = '"
				+ bookDTO.getBookImage() + "', BOOKCOUNT = " + bookDTO.getBookCount() + ", BOOKSUMMARY = '"
				+ bookDTO.getBookSummary() + "', BOOKDATE = '" + bookDTO.getBookDate() + "', BOOKHIT = "
				+ bookDTO.getBookHit() + " WHERE BOOKISBN = '" + bookDTO.getBookISBN() + "';");
	}

	// 도서의 재고 수 업데이트
	public void updateCount(String inputBookISBN) {
		jdbcTemplate.update("UPDATE BOOK SET BOOKCOUNT = BOOKCOUNT - 1 WHERE BOOKISBN = '" + inputBookISBN + "';");
	}

	// 도서의 판매 수 업데이트
	public void updateHit(String inputBookISBN) {
		jdbcTemplate.update("UPDATE BOOK SET BOOKHIT = BOOKHIT + 1 WHERE BOOKISBN = '" + inputBookISBN + "';");
	}
}
