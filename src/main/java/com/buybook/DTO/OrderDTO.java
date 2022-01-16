package com.buybook.DTO;

import java.sql.*;

public class OrderDTO {
	private int orderedId; // 주문번호
	private String orderedEmail; // 주문한 회원
	private String orderedISBN; // 주문한 도서의 ISBN
	private String orderedTitle; // 주문한 도서 제목
	private int orderedCount; // 주문한 도서 권 수
	private int orderedPrice; // 주문한 도서 가격
	private Timestamp orderedDate; // 주문한 날짜

	public OrderDTO(String orderedEmail, String orderedISBN, String orderedTitle, int orderedCount, int orderedPrice,
			Timestamp orderedDate) {
		super();
		this.orderedEmail = orderedEmail;
		this.orderedISBN = orderedISBN;
		this.orderedTitle = orderedTitle;
		this.orderedCount = orderedCount;
		this.orderedPrice = orderedPrice;
		this.orderedDate = orderedDate;
	}

	public OrderDTO(int orderedId, String orderedEmail, String orderedISBN, String orderedTitle, int orderedCount,
			int orderedPrice, Timestamp orderedDate) {
		super();
		this.orderedId = orderedId;
		this.orderedEmail = orderedEmail;
		this.orderedISBN = orderedISBN;
		this.orderedTitle = orderedTitle;
		this.orderedCount = orderedCount;
		this.orderedPrice = orderedPrice;
		this.orderedDate = orderedDate;
	}

	public int getOrderedId() {
		return orderedId;
	}

	public void setOrderedId(int orderedId) {
		this.orderedId = orderedId;
	}

	public String getOrderedEmail() {
		return orderedEmail;
	}

	public void setOrderedEmail(String orderedEmail) {
		this.orderedEmail = orderedEmail;
	}

	public String getOrderedISBN() {
		return orderedISBN;
	}

	public void setOrderedISBN(String orderedISBN) {
		this.orderedISBN = orderedISBN;
	}

	public String getOrderedTitle() {
		return orderedTitle;
	}

	public void setOrderedTitle(String orderedTitle) {
		this.orderedTitle = orderedTitle;
	}

	public int getOrderedCount() {
		return orderedCount;
	}

	public void setOrderedCount(int orderedCount) {
		this.orderedCount = orderedCount;
	}

	public int getOrderedPrice() {
		return orderedPrice;
	}

	public void setOrderedPrice(int orderedPrice) {
		this.orderedPrice = orderedPrice;
	}

	public Timestamp getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Timestamp orderedDate) {
		this.orderedDate = orderedDate;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderedId=" + orderedId + ", orderedEmail=" + orderedEmail + ", orderedISBN=" + orderedISBN
				+ ", orderedTitle=" + orderedTitle + ", orderedCount=" + orderedCount + ", orderedPrice=" + orderedPrice
				+ ", orderedDate=" + orderedDate + "]";
	}

}
