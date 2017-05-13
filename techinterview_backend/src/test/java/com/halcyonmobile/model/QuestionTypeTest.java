package com.halcyonmobile.model;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.halcyonmobile.persistence.Entitymanager;

public class QuestionTypeTest {
	
	private static final String NAME = "NAME";

	@Test
	public void QuestionTypePojoTest() {
		QuestionType questionType = new QuestionType();
		questionType.setName(NAME);
		assertEquals(NAME, questionType.getName());
		
		assertNotNull(questionType.toString());
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		em.getTransaction().begin();
		em.persist(questionType);
		em.flush();
		em.getTransaction().commit();
		
		assertNotNull(questionType.getId());
	}

}
