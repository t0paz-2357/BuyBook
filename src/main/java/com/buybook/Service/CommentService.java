package com.buybook.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.buybook.DAO.*;
import com.buybook.DTO.*;

@Service
public class CommentService {
	private CommentDAO commentDAO;

	@Autowired
	public CommentService(CommentDAO _commentDAO) {
		this.commentDAO = _commentDAO;
	}

	public void insertComment(CommentDTO commentDTO) {
		// 댓글 업로드
		commentDAO.insertComment(commentDTO);
	}

	public void deleteComment(int inputCommentNo, int inputCommentBoardNo) {
		// 댓글 삭제
		commentDAO.deleteComment(inputCommentNo, inputCommentBoardNo);
	}
	
	public List<CommentDTO> selectByBoardNo(int inputBoardNo) {
		// 댓글 리스트
		return commentDAO.selectByBoardNo(inputBoardNo);
	}
	
	public int findLatestCommentNo(int inputBoardNo) {
		return commentDAO.findLatestCommentNo(inputBoardNo);
	}
}
