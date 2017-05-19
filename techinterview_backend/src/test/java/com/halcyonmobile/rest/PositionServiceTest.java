package com.halcyonmobile.rest;

import org.junit.Test;

import com.halcyonmobile.model.Position;

import static org.junit.Assert.*;

import java.util.List;

public class PositionServiceTest {
	private static final String POSITION = "POSITION_1";
	private static final int NRQUE = 20;

	@Test 
	public void findAllTest() {
		PositionService positionService = new PositionService();
		
		List<Position> resultList = positionService.findAll();
		assertNotEquals(0, resultList.size());
	}
	
	@Test
	public void addPositionTest() {
		PositionService positionService = new PositionService();
		
		positionService.addPosition(POSITION, NRQUE);
		
		int positionId = positionService.findByName(POSITION);
		
		assertNotNull(positionId);
	}
}
