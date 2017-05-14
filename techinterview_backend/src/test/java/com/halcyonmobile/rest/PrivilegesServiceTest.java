package com.halcyonmobile.rest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.halcyonmobile.model.Privileges;

public class PrivilegesServiceTest {

	@Test
	public void findAllTest() {
		PrivilegesService privilegesService = new PrivilegesService();
		
		List<Privileges> resultList = privilegesService.findAll();
		assertNotEquals(0, resultList.size());
	}

}
