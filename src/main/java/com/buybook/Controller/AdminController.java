package com.buybook.Controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.buybook.DTO.*;
import com.buybook.Service.*;
import com.buybook.Exception.*;

@Controller
@RequestMapping(value = "/admin/*")
public class AdminController {
	@Autowired
	BookService bookService;
	@Autowired
	HopeService hopeService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	BoardService boardService;
	@Autowired
	GoodService goodService;
	@Autowired
	UserService userService;

	// 관리자 페이지
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String adminIndex() {
		return "adminIndex";
	}

	// 도서 추가 페이지
	@RequestMapping(value = "/bookAdd", method = RequestMethod.GET)
	public String bookAdd(Model model) {
		List<BookDTO> bookList = bookService.showAll();

		model.addAttribute("bookList", bookList);

		return "adminBookAdd";
	}

	// 도서 추가 페이지
	@PostMapping(value = "/bookAdd")
	public void bookAdd(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("inputBookImage") MultipartFile _inputBookImage) throws Exception {
		try {
			String inputBookISBN = request.getParameter("inputBookISBN");
			String inputBookGenre = request.getParameter("inputBookGenre");
			String inputBookTitle = request.getParameter("inputBookTitle");
			String inputBookAuthor = request.getParameter("inputBookAuthor");
			String inputBookPublisher = request.getParameter("inputBookPublisher");
			String inputBookPrice = request.getParameter("inputBookPrice");
			String inputBookCountString = request.getParameter("inputBookCount");
			String inputBookSummary = request.getParameter("inputBookSummary").replaceAll("\r\n", "<br />");
			String inputBookImage = null;

			int inputBookCount;

			BookDTO bookDTO = bookService.selectByBookISBN(inputBookISBN);

			if (bookDTO != null)
				throw new AlreadyExistingException("이미 존재하는 도서입니다.");

			if (!_inputBookImage.isEmpty()) {
				try {
					String uploadDir = "/bookImageStorage/";
					String realPathUpload = request.getServletContext().getRealPath(uploadDir);

					String fileName = _inputBookImage.getOriginalFilename();
					String filePath = realPathUpload + fileName;

					File files = new File(filePath);
					_inputBookImage.transferTo(files);

					inputBookImage = fileName;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (inputBookISBN.equals("") || inputBookGenre.equals("") || inputBookTitle.equals("")
					|| inputBookAuthor.equals("") || inputBookPublisher.equals("") || inputBookPrice.equals("")
					|| inputBookSummary.equals(""))
				throw new FillOutInformationException("모든 정보를 입력해주세요.");

			if (inputBookCountString.equals(""))
				inputBookCount = 1;
			else
				inputBookCount = Integer.parseInt(inputBookCountString);

			bookDTO = new BookDTO(inputBookISBN, inputBookTitle, inputBookAuthor, Integer.parseInt(inputBookPrice),
					inputBookGenre, inputBookPublisher, inputBookImage, inputBookCount, inputBookSummary);

			bookDTO = bookService.insertBook(bookDTO);

			System.out.println(bookDTO.toString());

			response.sendRedirect("/admin/bookAdd");
		} catch (AlreadyExistingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('이미 존재하는 도서입니다.'); location.href='/admin/bookAdd';</script>");

			out.flush();
		} catch (FillOutInformationException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/admin/bookAdd';</script>");

			out.flush();
		}
	}

	// 도서 삭제 페이지
	@RequestMapping(value = "/bookDelete", method = RequestMethod.GET)
	public String bookDelete(Model model) {
		List<BookDTO> bookList = bookService.showAll();

		model.addAttribute("bookList", bookList);

		return "adminBookDelete";
	}

	// 도서 삭제 페이지
	@PostMapping(value = "/bookDelete")
	public void bookDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String inputBookISBN = request.getParameter("inputBookISBN");
			String inputBookTitle = request.getParameter("inputBookTitle");
			String inputBookTitleConfirm = request.getParameter("inputBookTitleConfirm");

			BookDTO bookDTO = bookService.selectByBookISBN(inputBookISBN);

			if (inputBookTitle.equals(inputBookTitleConfirm)) {
				if (bookDTO.getBookTitle().equals(inputBookTitle)) {
					bookService.deleteBook(bookDTO);

					response.sendRedirect("/admin/bookDelete");
				} else
					throw new NotMatchingException("책의 제목과 맞지 않습니다.");
			} else
				throw new NotMatchingException("책의 제목과 맞지 않습니다.");
		} catch (NotMatchingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('책의 제목과 맞지 않습니다.'); location.href='/admin/bookDelete';</script>");

			out.flush();
		}
	}

	// 도서 수정 페이지
	@RequestMapping(value = "/bookUpdate", method = RequestMethod.GET)
	public String bookUpdate(Model model) {
		List<BookDTO> bookList = bookService.showAll();

		model.addAttribute("bookList", bookList);

		return "adminBookUpdate";
	}

	// 도서 수정 페이지
	@PostMapping(value = "/bookUpdate")
	public void bookUpdate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("inputBookImage") MultipartFile _inputBookImage) throws Exception {
		try {
			String inputBookISBN = request.getParameter("inputBookISBN");
			String inputBookGenre = request.getParameter("inputBookGenre");
			String inputBookTitle = request.getParameter("inputBookTitle");
			String inputBookAuthor = request.getParameter("inputBookAuthor");
			String inputBookPublisher = request.getParameter("inputBookPublisher");
			String inputBookPrice = request.getParameter("inputBookPrice");
			String inputBookCountString = request.getParameter("inputBookCount");
			String inputBookSummary = request.getParameter("inputBookSummary").replaceAll("\r\n", "<br />");
			String inputBookImage = null;

			int inputBookCount;

			BookDTO bookDTO = bookService.selectByBookISBN(inputBookISBN);

			if (bookDTO == null)
				throw new NotExistingException("수정할 도서가 없습니다.");
			else if (!_inputBookImage.isEmpty()) {
				try {
					String uploadDir = "/bookImageStorage/";
					String realPathUpload = request.getServletContext().getRealPath(uploadDir);

					String fileName = _inputBookImage.getOriginalFilename();
					String filePath = realPathUpload + fileName;

					File files = new File(filePath);
					_inputBookImage.transferTo(files);

					inputBookImage = fileName;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (inputBookISBN.equals("") || inputBookGenre.equals("") || inputBookTitle.equals("")
					|| inputBookAuthor.equals("") || inputBookPublisher.equals("") || inputBookPrice.equals("")
					|| inputBookSummary.equals("") || inputBookImage.equals(""))
				throw new FillOutInformationException("모든 정보를 입력해주세요.");

			if (inputBookCountString.equals(""))
				inputBookCount = bookDTO.getBookCount();
			else
				inputBookCount = Integer.parseInt(inputBookCountString);

			bookDTO = new BookDTO(inputBookISBN, inputBookTitle, inputBookAuthor, Integer.parseInt(inputBookPrice),
					inputBookGenre, inputBookPublisher, inputBookImage, inputBookCount, inputBookSummary,
					bookDTO.getBookDate(), bookDTO.getBookHit());

			bookService.updateBook(bookDTO);

			System.out.println(bookDTO.toString());

			response.sendRedirect("/admin/bookUpdate");
		} catch (NotExistingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('수정할 도서가 없습니다.'); location.href='/admin/bookUpdate';</script>");

			out.flush();
		} catch (FillOutInformationException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/admin/bookUpdate';</script>");

			out.flush();
		}
	}

	/////////////////////////////////////////////////////////////////////////////
	// 공지사항 추가
	@RequestMapping(value = "/noticeAdd", method = RequestMethod.GET)
	public String noticeAdd(Model model) {
		List<NoticeDTO> noticeList = noticeService.showAll();

		model.addAttribute("noticeList", noticeList);

		return "adminNoticeAdd";
	}

	// 공지사항 추가
	@PostMapping(value = "/noticeAdd")
	public void noticeAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String inputNoticeTitle = request.getParameter("inputNoticeTitle");
		String inputNoticeContent = request.getParameter("inputNoticeContent").replaceAll("\r\n", "<br />");

		NoticeDTO noticeDTO = new NoticeDTO(inputNoticeTitle, inputNoticeContent);

		noticeService.insertNotice(noticeDTO);

		response.sendRedirect("/admin/noticeAdd");
	}

	// 공지사항 삭제
	@RequestMapping(value = "/noticeDelete", method = RequestMethod.GET)
	public String noticeDelete(Model model) {
		List<NoticeDTO> noticeList = noticeService.showAll();

		model.addAttribute("noticeList", noticeList);

		return "adminNoticeDelete";
	}

	// 공지사항 삭제
	@PostMapping(value = "/noticeDelete")
	public void noticeDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String inputNoticeNo = request.getParameter("inputNoticeNo");
			String inputNoticeNoConfirm = request.getParameter("inputNoticeNoConfirm");

			NoticeDTO noticeDTO = noticeService.selectByNoticeNo(Integer.parseInt(inputNoticeNo));

			if (noticeDTO == null)
				throw new NotExistingException("존재하지 않는 공지입니다.");
			else {
				if (inputNoticeNo.equals(inputNoticeNoConfirm)) {
					noticeService.deleteNotice(Integer.parseInt(inputNoticeNo));

					response.sendRedirect("/admin/noticeDelete");
				} else
					throw new NotMatchingException("확인 번호가 맞지 않습니다.");
			}
		} catch (NotExistingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('존재하지 않는 공지입니다.'); location.href='/admin/noticeDelete';</script>");

			out.flush();
		} catch (NotMatchingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('확인 번호가 맞지 않습니다.'); location.href='/admin/noticeDelete';</script>");

			out.flush();
		}
	}

	// 추천 도서 추가
	@RequestMapping(value = "/goodAdd", method = RequestMethod.GET)
	public String goodAdd(Model model) {
		List<GoodDTO> goodList = goodService.showAll();
		List<BookDTO> bookList = bookService.showAll();

		model.addAttribute("goodList", goodList);
		model.addAttribute("bookList", bookList);

		return "adminGoodAdd";
	}

	// 추천 도서 추가
	@PostMapping(value = "/goodAdd")
	public void goodAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String inputGoodISBN = request.getParameter("inputGoodISBN");
			String inputGoodTitle = request.getParameter("inputGoodTitle");
			String inputGoodContent = request.getParameter("inputGoodContent");

			BookDTO bookDTO = bookService.selectByBookISBN(inputGoodISBN);

			if (bookDTO == null)
				throw new NotExistingException("존재하지 않는 도서입니다.");

			GoodDTO goodDTO = new GoodDTO(inputGoodISBN, inputGoodTitle, inputGoodContent, bookDTO.getBookImage());

			goodDTO = goodService.insertGood(goodDTO);

			if (goodDTO == null)
				throw new AlreadyExistingException("이미 존재하는 추천 도서입니다.");
			else
				response.sendRedirect("/admin/goodAdd");
		} catch (AlreadyExistingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('이미 존재하는 추천 도서입니다.'); location.href='/admin/goodAdd';</script>");

			out.flush();
		} catch (NotExistingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('존재하지 않는 도서입니다.'); location.href='/admin/goodAdd';</script>");

			out.flush();
		}
	}

	// 추천 도서 삭제
	@RequestMapping(value = "/goodDelete", method = RequestMethod.GET)
	public String goodDelete(Model model) {
		List<GoodDTO> goodList = goodService.showAll();

		model.addAttribute("goodList", goodList);

		return "adminGoodDelete";
	}

	// 추천 도서 삭제
	@PostMapping(value = "/goodDelete")
	public void goodDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String inputGoodNo = request.getParameter("inputGoodNo");
			String inputGoodTitle = request.getParameter("inputGoodTitle");
			String inputGoodTitleConfirm = request.getParameter("inputGoodTitleConfirm");

			GoodDTO goodDTO = goodService.selectByGoodNo(Integer.parseInt(inputGoodNo));

			if (goodDTO == null)
				throw new NotExistingException("존재하지 않는 추천 도서입니다.");
			else {
				if (inputGoodTitle.equals(inputGoodTitleConfirm)) {
					goodService.deleteGood(Integer.parseInt(inputGoodNo));

					response.sendRedirect("/admin/goodDelete");
				} else
					throw new NotMatchingException("게시글 제목이 맞지 않습니다.");
			}
		} catch (NotExistingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('존재하지 않는 추천 도서입니다.'); location.href='/admin/goodDelete';</script>");

			out.flush();
		} catch (NotMatchingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('게시글 제목이 맞지 않습니다.'); location.href='/admin/goodDelete';</script>");

			out.flush();
		}
	}

	// 게시판 관리
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.GET)
	public String boardUpdate(Model model) {
		List<BoardDTO> boardList = boardService.showAll();

		model.addAttribute("boardList", boardList);

		return "adminBoardUpdate";
	}

	// 게시판 관리
	@PostMapping(value = "/boardUpdate")
	public void boardUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			int inputBoardNo = Integer.parseInt(request.getParameter("inputBoardNo"));
			String inputBoardPublic = request.getParameter("inputBoardPublic");

			BoardDTO boardDTO = boardService.selectByBoardNo(inputBoardNo);

			if (boardDTO == null)
				throw new NotExistingException("존재하지 않는 게시글입니다.");
			else {
				boardService.updatePublic(inputBoardNo, inputBoardPublic);

				response.sendRedirect("/admin/boardUpdate");
			}
		} catch (NotExistingException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('존재하지 않는 게시글입니다.'); location.href='/admin/boardUpdate';</script>");

			out.flush();
		}
	}

	/////////////////////////////////////////////////////////////////////////////
	// 회원 목록 조회
	@RequestMapping(value = "/userShow", method = RequestMethod.GET)
	public String userShow(Model model) {
		List<UserDTO> userList = userService.showAll();

		model.addAttribute("userList", userList);

		return "adminUserShow";
	}

	// 희망 도서 페이지
	@RequestMapping(value = "/userHope", method = RequestMethod.GET)
	public String userHope(Model model) {
		List<HopeDTO> hopeList = hopeService.showAll();

		model.addAttribute("hopeList", hopeList);

		return "adminUserHope";
	}
}
