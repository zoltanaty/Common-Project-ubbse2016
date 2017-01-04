package com.halcyonmobile.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.halcyonmobile.model.Result;
import com.halcyonmobile.persistence.Entitymanager;

public class ResultService {
	
	public List<Result> findAll() {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Result");
		
	    @SuppressWarnings("unchecked")
		List<Result> resultList = (List<Result>) query.getResultList();
	    return resultList;
	}
	
	public List<Result> findByUserId(Integer id_user) {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Result r WHERE r.id_user = :id_user");
		query.setParameter("id_user", id_user);
		
	    @SuppressWarnings("unchecked")
		List<Result> resultList = (List<Result>) query.getResultList();
	    return resultList;
	}
}
