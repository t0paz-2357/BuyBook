package com.buybook.Controller;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.buybook.Service.*;
import com.buybook.DAO.*;
import com.buybook.DTO.*;
import com.buybook.Exception.*;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	CommentService commentService;

	// 게시판 메인
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String boardSearch() {
		return "boardSearch";
	}

	// 게시판 세부 페이지
	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public String boardDetail(Model model, @RequestParam int boardNo) {
		BoardDTO boardDTO = boardService.selectByBoardNo(boardNo);
		List<CommentDTO> commentList = commentService.selectByBoardNo(boardNo);

		model.addAttribute("commentList", commentList);
		model.addAttribute("boardDTO", boardDTO);

		return "boardDetail";
	}

	// 게시글 세부 페이지 댓글
	@PostMapping(value = "/boardDetail")
	public void uploadComment(HttpServletRequest request, @RequestParam int boardNo, HttpSession session,
			HttpServletResponse response) throws Exception {
		BoardDTO boardDTO = boardService.selectByBoardNo(boardNo);

		String inputCommentContent = request.getParameter("inputCommentContent");
		int latestCommentNo = commentService.findLatestCommentNo(boardNo);

		CommentDTO commentDTO = new CommentDTO(latestCommentNo, boardDTO.getBoardNo(), (String) session.getAttribute("userSessionName"),
				inputCommentContent);

		commentService.insertComment(commentDTO);

		response.sendRedirect("/board/boardDetail?boardNo=" + boardNo);
	}

	// 게시글 전체 검색
	@RequestMapping(value = "/boardSearch", method = RequestMethod.GET)
	public String boardSearch(Model model) {
		List<BoardDTO> boardList = boardService.showVisibleBoard();

		model.addAttribute("boardList", boardList);

		return "boardSearch";
	}

	// 게시글 쓰기
	@RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
	public String boardWrite() {
		return "boardWrite";
	}

	// 게시글 작성 처리
	@RequestMapping("/boardWrite")
	public void boardWrite(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		String inputBoardTitle = request.getParameter("inputBoardTitle");
		String inputBoardContent = request.getParameter("inputBoardContent").replaceAll("\r\n", "<br />");

		UserDTO userDTO = (UserDTO) session.getAttribute("userSessionDTO");

		BoardDTO boardDTO = new BoardDTO(userDTO.getUserEmail(), userDTO.getUserName(), inputBoardTitle,
				inputBoardContent);

		boardService.insertBoard(boardDTO);

		response.sendRedirect("/board/boardSearch");
	}

	// 게시글 삭제
	@RequestMapping("/boardDelete")
	public void boardDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam int boardNo) throws Exception {
		try {
			BoardDTO boardDTO = boardService.selectByBoardNo(boardNo);

			UserDTO userDTO = (UserDTO) session.getAttribute("userSessionDTO");

			if (userDTO == null)
				throw new NotAvailableException("삭제할 수 없습니다.");

			if (!boardDTO.getBoardEmail().equals(userDTO.getUserEmail()))
				throw new NotAvailableException("삭제할 수 없습니다.");

			boardService.deleteBoard(boardNo);

			response.sendRedirect("/board/boardSearch");
		} catch (NotAvailableException ex) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('삭제할 수 없습니다.'); location.href='/board/boardDetail?boardNo=" + boardNo
					+ "';</script>");

			out.flush();
		}
	}

	// 공지사항 게시판
	@RequestMapping(value = "/noticeSearch", method = RequestMethod.GET)
	public String noticeSearch(Model model) {
		List<NoticeDTO> noticeList = noticeService.showAll();

		model.addAttribute("noticeList", noticeList);

		return "noticeSearch";
	}

	// 공지사항 세부사항
	@RequestMapping(value = "/noticeDetail", method = RequestMethod.GET)
	public String noticeDetail(Model model, @RequestParam int noticeNo) {
		NoticeDTO noticeDTO = noticeService.selectByNoticeNo(noticeNo);

		model.addAttribute("noticeDTO", noticeDTO);

		return "noticeDetail";
	}
}
