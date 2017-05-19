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
		Owners owner = new Owners();
		owner.setUsername(USERNAME);
		owner.setPassword(PASSWORD);
		owner.setPrivilege(PRIVILEGE);
		
		assertNotNull(owner.getUsername());
		assertNotNull(owner.getPassword());
		assertNotNull(owner.getPrivilege());
		
		assertNotNull(owner.toString());
		EntityManager em = Entitymanager.getEntityManagerInstance();
		em.getTransaction().begin();
		em.persist(owner);
		em.flush();
		em.getTransaction().commit();
		
		assertNotNull(owner.getUsername());
		
		em.getTransaction().begin();
		em.remove(owner);
		em.flush();
		em.getTransaction().commit();
	}
	
	
}
