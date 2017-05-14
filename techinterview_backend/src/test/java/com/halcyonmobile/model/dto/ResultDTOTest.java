package com.halcyonmobile.model.dto;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.halcyonmobile.model.Result;

public class ResultDTOTest {

	private ResultDTO resultDTO;
	
	@Before
	public void setUp() {
		resultDTO = new ResultDTO();
	}
	
	@Test
	public void getResultList_ResultListIsSetted_TheGivenList() {
		List<Result> resultList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			resultList.add(new Result());
		}
		
		resultDTO.setResultList(resultList);
		
		assertArrayEquals(resultList.toArray(), resultDTO.getResultList().toArray());
	}
	
	@Test
	public void setResultList_ResultListIsPreviouslySetted_TheNewList() {
		List<Result> resultList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			resultList.add(new Result());
		}
		resultDTO.setResultList(resultList);
		
		for (int i = 0; i < 3; i++) {
			resultList.add(new Result());
		}
		resultDTO.setResultList(resultList);
	
		assertArrayEquals(resultList.toArray(), resultDTO.getResultList().toArray());
	}

}
