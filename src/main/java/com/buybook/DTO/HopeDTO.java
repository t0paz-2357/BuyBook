package com.buybook.DTO;

public class HopeDTO {
	private String hopeISBN;
	private String hopeTitle;
	private int hopeNumber;
	private String hopeLink;

	public HopeDTO(String hopeISBN, String hopeTitle, String hopeLink) {
		super();
		this.hopeISBN = hopeISBN;
		this.hopeTitle = hopeTitle;
		this.hopeLink = hopeLink;
	}

	public HopeDTO(String hopeISBN, String hopeTitle, int hopeNumber, String hopeLink) {
		super();
		this.hopeISBN = hopeISBN;
		this.hopeTitle = hopeTitle;
		this.hopeNumber = hopeNumber;
		this.hopeLink = hopeLink;
	}

	public String getHopeISBN() {
		return hopeISBN;
	}

	public void setHopeISBN(String hopeISBN) {
		this.hopeISBN = hopeISBN;
	}

	public String getHopeTitle() {
		return hopeTitle;
	}

	public void setHopeTitle(String hopeTitle) {
		this.hopeTitle = hopeTitle;
	}

	public int getHopeNumber() {
		return hopeNumber;
	}

	public void setHopeNumber(int hopeNumber) {
		this.hopeNumber = hopeNumber;
	}

	public String getHopeLink() {
		return hopeLink;
	}

	public void setHopeLink(String hopeLink) {
		this.hopeLink = hopeLink;
	}
}