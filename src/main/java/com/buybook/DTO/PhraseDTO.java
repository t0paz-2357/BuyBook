package com.buybook.DTO;

public class PhraseDTO {
	private int phraseNo;
	private String phraseString;

	public PhraseDTO(int phraseNo, String phraseString) {
		super();
		this.phraseNo = phraseNo;
		this.phraseString = phraseString;
	}

	public int getPhraseNo() {
		return phraseNo;
	}

	public void setPhraseNo(int phraseNo) {
		this.phraseNo = phraseNo;
	}

	public String getPhraseString() {
		return phraseString;
	}

	public void setPhraseString(String phraseString) {
		this.phraseString = phraseString;
	}

	@Override
	public String toString() {
		return "PhraseDTO [phraseNo=" + phraseNo + ", phraseString=" + phraseString + "]";
	}

}
