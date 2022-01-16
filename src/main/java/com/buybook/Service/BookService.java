package com.buybook.Service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

import com.buybook.DAO.*;
import com.buybook.DTO.*;

@Service
public class BookService {
	private BookDAO bookDAO;

	@Autowired
	public BookService(BookDAO _bookDAO) {
		this.bookDAO = _bookDAO;
	}

	// 모든 도서 목록 출력
	public List<BookDTO> showAll() {
		List<BookDTO> bookList = bookDAO.showAll();

		return bookList;
	}

	// ISBN 으로 도서 한 권 가져오기
	public BookDTO selectByBookISBN(String inputBookISBN) {
		return bookDAO.selectByBookISBN(inputBookISBN);
	}

	// 도서 장르로 도서 가져오기
	public List<BookDTO> selectGenreBookList(String inputBookISBN, String inputBookGenre) {
		return bookDAO.selectGenreBookList(inputBookISBN, inputBookGenre);
	}

	// 도서 추가하기
	public BookDTO insertBook(BookDTO _bookDTO) {
		BookDTO bookDTO = bookDAO.selectByBookISBN(_bookDTO.getBookISBN());

		if (bookDTO == null) { // 도서 존재하지 않음 --> 추가 진행
			bookDAO.insertBook(_bookDTO);

			return _bookDTO; // 추가한 도서 반환
		} else {
			System.out.println("이미 존재하는 도서입니다.");

			return null;
		}
	}

	// 도서 삭제하기
	public void deleteBook(BookDTO _bookDTO) { // 도서 삭제
		BookDTO bookDTO = bookDAO.selectByBookISBN(_bookDTO.getBookISBN());

		if (bookDTO == null) {
			System.out.println("존재하지 않는 도서입니다.");
		} else {
			bookDAO.deleteBook(bookDTO);
		}
	}

	// 다섯 권 도서 가져오기
	public List<BookDTO> showFive() {
		return bookDAO.showFive();
	}

	// 신규 도서 10권 가져오기
	public List<BookDTO> newBookList() {
		return bookDAO.newBookList();
	}

	// 같은 장르 도서 4권 가져오기
	public List<BookDTO> genreBookList(String inputBookISBN, String inputBookGenre) {
		return bookDAO.selectGenreBookList(inputBookISBN, inputBookGenre);
	}

	// 인기 도서 가져오기
	public List<BookDTO> showHitBookList() {
		return bookDAO.showHitBookList();
	}

	// 인기 도서 세 권 가져오기
	public List<BookDTO> showHitBookThree() {
		return bookDAO.showHitBookThree();
	}

	// 도서 수정하기
	public void updateBook(BookDTO bookDTO) { // 도서 수정
		bookDAO.updateBook(bookDTO);
	}

	// 도서 구매 처리
	public void updateCountAndHit(String inputBookISBN) {
		bookDAO.updateCount(inputBookISBN);
		bookDAO.updateHit(inputBookISBN);
	}

}
