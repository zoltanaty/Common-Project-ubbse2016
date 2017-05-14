package com.halcyonmobile.model;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.halcyonmobile.persistence.Entitymanager;

public class PositionTest {
	private static final String NAME ="Name";
	private static final int NRQUESTIONS = 0;

	@Test
	public void positionPojoTest() {
		Position position = new Position();
		position.setName(NAME);
		position.setnrQue(NRQUESTIONS);
		assertNotNull(position.getName());
		assertNotNull(position.getnrQue());
		
		assertNotNull(position.toString());
		EntityManager em = Entitymanager.getEntityManagerInstance();
		em.getTransaction().begin();
		em.persist(position);
		em.flush();
		em.getTransaction().commit();

		assertNotNull(position.getId());
	}

	
	@Test
	public void gettersAndSettersTest(){
		Position position = new Position();
		
		position.setName(NAME);
		assertEquals(NAME,position.getName());
		
		position.setnrQue(NRQUESTIONS);
		assertEquals(NRQUESTIONS,position.getnrQue());
	}

}
