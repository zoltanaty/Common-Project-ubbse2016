package com.halcyonmobile.model;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.halcyonmobile.persistence.Entitymanager;

public class UserTest {
	private static final String NAME = "NAME_1";
	private static final String EMAIL = "EMAIL_1";
	private static final int POSITIONID = 1;
	
	@Test
	public void UserPojoTest() {
		User user = new User();
		user.setName(NAME);
		user.setEmail(EMAIL);
		user.setPositionId(POSITIONID);
		
		assertNotNull(user.getName());
		assertNotNull(user.getEmail());
		assertNotNull(user.getPositionId());
		
		assertNotNull(user.toString());
		EntityManager em = Entitymanager.getEntityManagerInstance();
		em.getTransaction().begin();
		em.persist(user);
		em.flush();
		em.getTransaction().commit();
		
		assertNotNull(user.getId());
	}
}
