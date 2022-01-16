package com.buybook.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.buybook.Service.*;
import com.buybook.DTO.*;

@Controller
public class MainController {

	@Autowired
	BookService bookService;
	@Autowired
	BoardService boardService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	GoodService goodService;

	// 메인
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		// 공지, 게시글, 신규 도서, 추천 도서, 인기 도서
		List<NoticeDTO> noticeList = noticeService.showFive();
		List<BoardDTO> boardList = boardService.showVisibleThree();
		List<BookDTO> bookList = bookService.showFive();
		List<GoodDTO> goodList = goodService.showThree();
		List<BookDTO> hitBookList = bookService.showHitBookThree();

		model.addAttribute("noticeList", noticeList);
		model.addAttribute("boardList", boardList);
		model.addAttribute("bookList", bookList);
		model.addAttribute("goodList", goodList);
		model.addAttribute("hitBookList", hitBookList);

		return "index";
	}

	// 상점 소개 페이지
	@GetMapping("/storeIntroduce")
	public String storeIntroduce() {
		return "storeIntroduce";
	}

}