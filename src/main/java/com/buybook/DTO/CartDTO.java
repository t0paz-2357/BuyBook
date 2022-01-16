package com.buybook.DTO;

public class CartDTO {
	private String cartEmail; // 주문한 회원 이메일
	private String cartISBN; // 주문한 도서 ISBN
	private String cartImage; // 주문한 도서 이미지
	private String cartTitle; // 주문한 도서 제목
	private int cartCount; // 카트에 담은 개수
	private int cartPrice; // 카트의 금액

	public CartDTO(String cartEmail, String cartISBN, String cartImage, String cartTitle, int cartCount,
			int cartPrice) {
		super();
		this.cartEmail = cartEmail;
		this.cartISBN = cartISBN;
		this.cartImage = cartImage;
		this.cartTitle = cartTitle;
		this.cartCount = cartCount;
		this.cartPrice = cartPrice;
	}

	public String getCartEmail() {
		return cartEmail;
	}

	public void setCartEmail(String cartEmail) {
		this.cartEmail = cartEmail;
	}

	public String getCartISBN() {
		return cartISBN;
	}

	public void setCartISBN(String cartISBN) {
		this.cartISBN = cartISBN;
	}

	public String getCartImage() {
		return cartImage;
	}

	public void setCartImage(String cartImage) {
		this.cartImage = cartImage;
	}

	public String getCartTitle() {
		return cartTitle;
	}

	public void setCartTitle(String cartTitle) {
		this.cartTitle = cartTitle;
	}

	public int getCartCount() {
		return cartCount;
	}

	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}

	public int getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(int cartPrice) {
		this.cartPrice = cartPrice;
	}

	@Override
	public String toString() {
		return "CartDTO [cartEmail=" + cartEmail + ", cartISBN=" + cartISBN + ", cartImage=" + cartImage
				+ ", cartTitle=" + cartTitle + ", cartCount=" + cartCount + ", cartPrice=" + cartPrice + "]";
	}

}
