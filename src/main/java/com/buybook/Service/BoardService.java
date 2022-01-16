package com.buybook.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.buybook.DAO.*;
import com.buybook.DTO.*;

@Service
public class BoardService {
	private BoardDAO boardDAO;

	@Autowired
	public BoardService(BoardDAO _boardDAO) {
		this.boardDAO = _boardDAO;
	}

	public void updatePublic(int inputBoardNo, String inputBoardPublic) {
		// 게시글 공개 여부
		boardDAO.updatePublic(inputBoardNo, inputBoardPublic);
	}

	public BoardDTO selectByBoardNo(int inputBoardNo) {
		// 게시글
		return boardDAO.selectByBoardNo(inputBoardNo);
	}

	public List<BoardDTO> showAll() {
		// 게시글 전체보기
		return boardDAO.showAll();
	}

	public List<BoardDTO> showVisibleBoard() {
		// 공개 게시글 전체보기
		return boardDAO.showVisibleBoard();
	}

	public List<BoardDTO> showVisibleThree() {
		// 공개 게시글 최근 세 개 보기
		return boardDAO.showVisibleThree();
	}

	public void insertBoard(BoardDTO boardDTO) {
		// 게시글 업로드
		boardDAO.insertBoard(boardDTO);
	}

	public void deleteBoard(int inputBoardNo) {
		// 게시글 삭제
		boardDAO.deleteBoard(inputBoardNo);
	}
}
