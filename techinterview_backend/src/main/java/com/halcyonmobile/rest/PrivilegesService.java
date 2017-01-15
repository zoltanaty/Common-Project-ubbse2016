package com.halcyonmobile.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.halcyonmobile.model.Privileges;
import com.halcyonmobile.persistence.Entitymanager;

public class PrivilegesService {
	

	public ArrayList<Privileges> findAll() {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Privileges p");
		
		@SuppressWarnings("unchecked")
		List<Privileges> psList = (List<Privileges>) query.getResultList();
		
		return (ArrayList<Privileges>) psList;
	}
}

