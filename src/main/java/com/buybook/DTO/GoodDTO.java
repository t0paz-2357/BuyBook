package com.buybook.DTO;

import java.sql.*;

public class GoodDTO {
	private int goodNo; // 사서 추천 도서 번호
	private String goodISBN; // 사서 추천 도서 ISBN
	private String goodTitle; // 사서 추천 도서 게시글 제목
	private String goodContent; // 사서 추천 도서 게시글 내용
	private String goodImage; // 사서 추천 도서 이미지
	private Timestamp goodDate; // 사소 추천 도서 게시글 날짜

	public GoodDTO(int goodNo, String goodISBN, String goodTitle, String goodContent, String goodImage, Timestamp goodDate) {
		super();
		this.goodNo = goodNo;
		this.goodISBN = goodISBN;
		this.goodTitle = goodTitle;
		this.goodContent = goodContent;
		this.goodImage = goodImage;
		this.goodDate = goodDate;
	}

	public GoodDTO(String goodISBN, String goodTitle, String goodContent, String goodImage) {
		super();
		this.goodISBN = goodISBN;
		this.goodTitle = goodTitle;
		this.goodContent = goodContent;
		this.goodImage = goodImage;
	}

	public int getGoodNo() {
		return goodNo;
	}

	public void setGoodNo(int goodNo) {
		this.goodNo = goodNo;
	}

	public String getGoodISBN() {
		return goodISBN;
	}

	public void setGoodISBN(String goodISBN) {
		this.goodISBN = goodISBN;
	}

	public String getGoodTitle() {
		return goodTitle;
	}

	public void setGoodTitle(String goodTitle) {
		this.goodTitle = goodTitle;
	}

	public String getGoodContent() {
		return goodContent;
	}

	public void setGoodContent(String goodContent) {
		this.goodContent = goodContent;
	}

	public String getGoodImage() {
		return goodImage;
	}

	public void setGoodImage(String goodImage) {
		this.goodImage = goodImage;
	}

	public Timestamp getGoodDate() {
		return goodDate;
	}

	public void setGoodDate(Timestamp goodDate) {
		this.goodDate = goodDate;
	}

	@Override
	public String toString() {
		return "GoodDTO [goodNo=" + goodNo + ", goodISBN=" + goodISBN + ", goodTitle=" + goodTitle + ", goodContent="
				+ goodContent + ", goodImage=" + goodImage + ", goodDate=" + goodDate + "]";
	}

}