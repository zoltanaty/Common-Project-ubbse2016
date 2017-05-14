package com.halcyonmobile.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.halcyonmobile.model.User;

public class UserServiceTest {
	public static final int USERID = 1;
	
	@Test
	public void findById() {
		UserService userService = new UserService();
		
		User userId = userService.findById(USERID);
		
		assertNotNull(userId.getId());
	}
}
