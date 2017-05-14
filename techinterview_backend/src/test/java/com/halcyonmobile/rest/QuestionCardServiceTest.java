package com.halcyonmobile.rest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.halcyonmobile.model.dto.QuestionCardDTO;

public class QuestionCardServiceTest {

	private QuestionCardService qcs;
	
	@Before
	public void setUp() throws Exception {
		qcs = new QuestionCardService();
	}

	@Test
	public void findByPosition_PositionIdIs1_isNotEmpty() {
		List<QuestionCardDTO> list = qcs.findByPosition(1);
		
		assertNotEquals(0, list.size());
	}

}
