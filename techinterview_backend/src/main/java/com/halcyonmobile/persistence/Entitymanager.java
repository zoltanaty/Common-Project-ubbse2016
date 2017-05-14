package com.halcyonmobile.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Entitymanager {
	
	private static EntityManager em = null;
	private static Object lock;
	
	public synchronized static EntityManager getEntityManagerInstance(){
		if(em == null){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("TechInterViewPersistence");
	        em = emf.createEntityManager();
		}
		
		return em;
	}
}
