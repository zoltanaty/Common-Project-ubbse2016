package com.halcyonmobile.model;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.halcyonmobile.persistence.Entitymanager;

public class OwnersTest {
	private static final String USERNAME = "username1";
	private static final String PASSWORD = "password1";
	private static final String PRIVILEGE = "1";
	
	@Test
	public void OwnersPojoTest() {
		Owners owners = new Owners();
		owners.setUsername(USERNAME);
		owners.setPassword(PASSWORD);
		owners.setPrivilege(PRIVILEGE);
		
		assertNotNull(owners.getUsername());
		assertNotNull(owners.getPassword());
		assertNotNull(owners.getPrivilege());
		
		assertNotNull(owners.toString());
		EntityManager em = Entitymanager.getEntityManagerInstance();
		em.getTransaction().begin();
		em.persist(owners);
		em.flush();
		em.getTransaction().commit();
		
		assertNotNull(owners.getUsername());
	}
}
