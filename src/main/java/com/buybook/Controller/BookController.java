package com.buybook.Controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.*;

import com.buybook.Exception.*;
import com.buybook.Service.*;
import com.buybook.DTO.*;

@Controller
@RequestMapping(value = "/book/*")
public class BookController {

	@Autowired
	BookService bookService;
	@Autowired
	CartService cartService;
	@Autowired
	GoodService goodService;
	@Autowired
	HopeService hopeService;

	// 도서 상세
	@RequestMapping(value = "/bookDetail", method = RequestMethod.GET)
	public String bookDetail(Model model, @RequestParam String bookISBN, @RequestParam String bookGenre) {
		BookDTO bookDTO = bookService.selectByBookISBN(bookISBN);
		List<BookDTO> genreBookList = bookService.selectGenreBookList(bookDTO.getBookISBN(), bookDTO.getBookGenre());

		model.addAttribute("bookDTO", bookDTO);
		model.addAttribute("genreBookList", genreBookList);

		return "bookDetail";
	}

	// 도서 장바구니 담기
	@PostMapping("/bookDetail")
	public void bookDetail(HttpSession session, @RequestParam String bookISBN, @RequestParam String bookGenre,
			HttpServletResponse response) throws Exception {
		try {
			UserDTO userDTO = (UserDTO) session.getAttribute("userSessionDTO");
			BookDTO bookDTO = bookService.selectByBookISBN(bookISBN);

			
			System.out.println(bookISBN + bookGenre);
			
			CartDTO cartDTO = new CartDTO(userDTO.getUserEmail(), bookISBN, bookDTO.getBookImage(),
					bookDTO.getBookTitle(), 1, bookDTO.getBookPrice());

			cartService.insertCart(cartDTO);

			response.sendRedirect("/book/bookDetail?bookISBN=" + bookISBN + "&bookGenre=" + bookGenre);
		} catch (Exception ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('중복되는 도서가 있습니다.'); location.href='/book/bookDetail?bookISBN=" + bookISBN
					+ "&bookGenre=" + bookGenre + "';</script>");

			out.flush();
		}
	}

	// 도서 검색 페이지
	@RequestMapping(value = "/bookSearch", method = RequestMethod.GET)
	public String bookSearch(Model model) {
		List<BookDTO> bookList = bookService.showAll();

		model.addAttribute("bookList", bookList);

		return "bookSearch";
	}

	// 희망 도서 신청
	@RequestMapping(value = "/userHope", method = RequestMethod.GET)
	public String userHope(Model model) {
		List<BookDTO> bookList = bookService.showAll();

		model.addAttribute("bookList", bookList);

		return "userHope";
	}

	// 추천 도서 업로드
	@PostMapping(value = "/userHope")
	public void userHope(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String inputBookISBN = request.getParameter("inputBookISBN");
			String inputBookTitle = request.getParameter("inputBookTitle");
			String inputBookLink = request.getParameter("inputBookLink");

			BookDTO bookDTO = bookService.selectByBookISBN(inputBookISBN);

			if (bookDTO == null) {
				HopeDTO hopeDTO = hopeService.selectByHopeISBN(inputBookISBN);
				if (hopeDTO == null) {
					hopeDTO = new HopeDTO(inputBookISBN, inputBookTitle, inputBookLink);
					hopeService.insertHope(hopeDTO);

					response.sendRedirect("/book/userHope");
				} else {
					hopeService.updateHope(hopeDTO.getHopeISBN());

					response.sendRedirect("/book/userHope");
				}
			} else
				throw new AlreadyExistingException("이미 존재하는 도서입니다.");
		} catch (AlreadyExistingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('이미 존재하는 도서입니다.'); location.href='/book/userHope';</script>");

			out.flush();
		}
	}

	// 신작 도서
	@RequestMapping(value = "/newBookSearch", method = RequestMethod.GET)
	public String newBookSearch(Model model) {
		List<BookDTO> newBookList = bookService.newBookList();

		model.addAttribute("newBookList", newBookList);

		return "newBookSearch";
	}

	// 인기 도서 10권
	@RequestMapping(value = "/hitBookSearch", method = RequestMethod.GET)
	public String hitBookSearch(Model model) {
		List<BookDTO> hitBookList = bookService.showHitBookList();

		model.addAttribute("hitBookList", hitBookList);

		return "hitBookSearch";
	}

	// 추천 도서 게시판
	@RequestMapping(value = "/goodSearch", method = RequestMethod.GET)
	public String goodSearch(Model model) {
		List<GoodDTO> goodList = goodService.showAll();

		model.addAttribute("goodList", goodList);

		return "goodSearch";
	}

	// 추천 도서 게시글
	@RequestMapping(value = "/goodDetail", method = RequestMethod.GET)
	public String goodDetail(Model model, @RequestParam int goodNo) {
		GoodDTO goodDTO = goodService.selectByGoodNo(goodNo);

		model.addAttribute("goodDTO", goodDTO);

		return "goodDetail";
	}

}
