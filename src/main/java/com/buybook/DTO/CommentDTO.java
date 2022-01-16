package com.buybook.DTO;

public class CommentDTO {
	private int commentNo; // 댓글 번호
	private int commentBoardNo; // 댓글 게시글 번호
	private String commentName; // 댓글 작성자 이름
	private String commentContent; // 댓글 내용

	public CommentDTO(int commentNo, int commentBoardNo, String commentName, String commentContent) {
		super();
		this.commentNo = commentNo;
		this.commentBoardNo = commentBoardNo;
		this.commentName = commentName;
		this.commentContent = commentContent;
	}

	public CommentDTO(int commentBoardNo, String commentName, String commentContent) {
		super();
		this.commentBoardNo = commentBoardNo;
		this.commentName = commentName;
		this.commentContent = commentContent;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getCommentBoardNo() {
		return commentBoardNo;
	}

	public void setCommentBoardNo(int commentBoardNo) {
		this.commentBoardNo = commentBoardNo;
	}

	public String getCommentName() {
		return commentName;
	}

	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	@Override
	public String toString() {
		return "CommentDTO [commentNo=" + commentNo + ", commentBoardNo=" + commentBoardNo + ", commentName="
				+ commentName + ", commentContent=" + commentContent + "]";
	}

}
