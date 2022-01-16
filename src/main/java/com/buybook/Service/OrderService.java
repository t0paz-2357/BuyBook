package com.buybook.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.buybook.DAO.*;
import com.buybook.DTO.*;

@Service
public class OrderService {
	private OrderDAO orderDAO;

	@Autowired
	public OrderService(OrderDAO _orderDAO) {
		this.orderDAO = _orderDAO;
	}

	// 모든 주문 반환
	public List<OrderDTO> showAll(String inputUserEmail) {
		List<OrderDTO> orderList = orderDAO.showAll(inputUserEmail);

		return orderList;
	}
	
	// 주문 추가
	public void addOrder(OrderDTO orderDTO) {
		orderDAO.insertOrder(orderDTO);
	}

}
