package com.buybook.Controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.buybook.DTO.*;
import com.buybook.Service.*;

@Controller
@RequestMapping(value = "/cart/*")
public class CartController {

	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	OrderService orderService;
	@Autowired
	BookService bookService;

	// 장바구니
	@RequestMapping(value = "/Cart", method = RequestMethod.GET)
	public String bookCart(@RequestParam String cartEmail, Model model) {
		UserDTO userDTO = userService.findOneUser(cartEmail);

		List<CartDTO> cartList = cartService.showUserCart(userDTO.getUserEmail());

		model.addAttribute("cartList", cartList);

		return "Cart";
	}

	// 장바구니 항목 삭제
	@RequestMapping(value = "/Cart", method = RequestMethod.POST)
	public void bookCart(@RequestParam String cartEmail, @RequestParam String cartISBN, HttpServletResponse response)
			throws Exception {
		cartService.deleteCartItem(cartEmail, cartISBN);

		response.sendRedirect("/cart/Cart?cartEmail=" + cartEmail);
	}

	// 구매
	@PostMapping(value = "/cartPay")
	public String cartPay(HttpServletRequest request, Model model) {
		return "cartPay";
	}

	@RequestMapping(value = "/paySuccess")
	public String paySuccess(@RequestParam String inputUserEmail) {
		List<CartDTO> cartList = cartService.showUserCart(inputUserEmail);

		for (CartDTO cartDTO : cartList) {
			cartService.insertOrder(cartDTO);
			bookService.updateCountAndHit(cartDTO.getCartISBN());
		}

		cartService.deleteCart(inputUserEmail);

		return "paySuccess";
	}

	@RequestMapping(value = "/payFail")
	public String payFail() {
		return "payFail";
	}
}
