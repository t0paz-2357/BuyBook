package com.buybook.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.buybook.DAO.*;
import com.buybook.DTO.*;

@Service
public class NoticeService {
	private NoticeDAO noticeDAO;

	@Autowired
	public NoticeService(NoticeDAO _noticeDAO) {
		this.noticeDAO = _noticeDAO;
	}

	public void insertNotice(NoticeDTO noticeDTO) {
		// 공지사항 업로드
		noticeDAO.insertNotice(noticeDTO);
	}

	public void deleteNotice(int inputNoticeNo) {
		// 공지사항 삭제
		noticeDAO.deleteNotice(inputNoticeNo);
	}

	public List<NoticeDTO> showFive() {
		return noticeDAO.showFive();
	}

	public List<NoticeDTO> showAll() {
		return noticeDAO.showAll();
	}

	public NoticeDTO selectByNoticeNo(int inputNoticeNo) {
		return noticeDAO.selectByNoticeNo(inputNoticeNo);
	}
}
