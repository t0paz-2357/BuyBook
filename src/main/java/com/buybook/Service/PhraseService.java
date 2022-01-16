package com.buybook.Service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.buybook.DAO.*;
import com.buybook.DTO.*;

@Service
public class PhraseService {
	private PhraseDAO phraseDAO;

	@Autowired
	public PhraseService(PhraseDAO _phraseDAO) {
		this.phraseDAO = _phraseDAO;
	}

	public PhraseDTO selectOnePhrase() {
		return phraseDAO.selectOnePhrase();
	}
}
