package com.buybook.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.buybook.DAO.*;
import com.buybook.DTO.*;

@Service
public class HopeService {
	private HopeDAO hopeDAO;

	@Autowired
	public HopeService(HopeDAO _hopeDAO) {
		this.hopeDAO = _hopeDAO;
	}

	public List<HopeDTO> showAll() {
		return hopeDAO.showAll();
	}

	public HopeDTO selectByHopeISBN(String inputHopeISBN) {
		return hopeDAO.selectByHopeISBN(inputHopeISBN);
	}

	public void insertHope(HopeDTO hopeDTO) {
		hopeDAO.insertHope(hopeDTO);
	}

	public void updateHope(String inputHopeISBN) {
		hopeDAO.updateHope(inputHopeISBN);
	}
}
