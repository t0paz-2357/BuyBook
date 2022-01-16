package com.buybook.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.buybook.DAO.*;
import com.buybook.DTO.*;

@Service
public class CartService {
	private CartDAO cartDAO;
	
	@Autowired
	public CartService(CartDAO _cartDAO) {
		this.cartDAO = _cartDAO;
	}
	
	// 해당 회원의 장바구니 출력
	public List<CartDTO> showUserCart(String inputUserEmail) {
		List<CartDTO> cartList = cartDAO.showUserCart(inputUserEmail);
		
		return cartList;
	}
	
	// 장바구니 항목 한 개 가져오기
	public CartDTO getCartItem(String inputUserEmail, String inputBookISBN) {
		return cartDAO.getCartItem(inputUserEmail, inputBookISBN);
	}
	// 장바구니 비우기
	public void deleteCart(String inputUserEmail) {
		cartDAO.deleteCart(inputUserEmail);
	}
	
	// 장바구니 담기
	public void insertCart(CartDTO cartDTO) {
		cartDAO.insertCart(cartDTO);
	}
	
	// 장바구니 항목 지우기
	public void deleteCartItem(String inputUserEmail, String inputBookISBN) {
		cartDAO.deleteCartItem(inputUserEmail, inputBookISBN);
	}
	
	// 장바구니에서 주문 내역으로
	public void insertOrder(CartDTO cartDTO) {
		cartDAO.insertOrder(cartDTO);
	}

}
