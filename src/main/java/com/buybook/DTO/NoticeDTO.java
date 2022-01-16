package com.buybook.DTO;

import java.sql.*;

public class NoticeDTO {
	private int noticeNo; // 공지 아이디 -- Primary Key, Auto_Increment
	private String noticeTitle; // 공지 제목
	private Timestamp noticeDate; // 공지 날짜
	private String noticeContent; // 공지 콘텐츠

	public NoticeDTO(String _noticeTitle, String _noticeContent) {
		// 공지 새로 추가할 때
		this.noticeTitle = _noticeTitle;
		this.noticeContent = _noticeContent;
	}

	public NoticeDTO(int _noticeNo, String _noticeTitle, Timestamp _noticeDate, String _noticeContent) {
		// 공지 받아올 때
		this.noticeNo = _noticeNo;
		this.noticeTitle = _noticeTitle;
		this.noticeDate = _noticeDate;
		this.noticeContent = _noticeContent;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public Timestamp getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Timestamp noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	@Override
	public String toString() {
		return "NoticeDTO [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeDate=" + noticeDate
				+ ", noticeContent=" + noticeContent + "]";
	}

}
