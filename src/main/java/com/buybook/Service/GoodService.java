package com.buybook.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.buybook.DAO.*;
import com.buybook.DTO.*;

@Service
public class GoodService {
	private GoodDAO goodDAO;

	@Autowired
	public GoodService(GoodDAO _goodDAO) {
		this.goodDAO = _goodDAO;
	}

	public List<GoodDTO> showAll() {
		return goodDAO.showAll();
	}

	public GoodDTO insertGood(GoodDTO _goodDTO) {
		GoodDTO goodDTO = goodDAO.selectByGoodNo(_goodDTO.getGoodNo());

		if (goodDTO == null) {
			goodDAO.insertGood(_goodDTO);

			return _goodDTO;
		} else {
			System.out.println("이미 업로드된 도서");

			return null;
		}
	}

	public void deleteGood(int inputGoodNo) {
		goodDAO.deleteGood(inputGoodNo);
	}

	public List<GoodDTO> showThree() {
		return goodDAO.showThree();
	}

	public GoodDTO selectByGoodNo(int inputGoodNo) {
		return goodDAO.selectByGoodNo(inputGoodNo);
	}
}
