package com.halcyonmobile.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.halcyonmobile.model.Result;
import com.halcyonmobile.persistence.Entitymanager;

@Path("/result")
public class ResultService {
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Result> findAll() {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Result");
		
	    @SuppressWarnings("unchecked")
		List<Result> resultList = (List<Result>) query.getResultList();
	    return resultList;
	}
	
	@GET
	@Path("/{id_user}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Result> findByUserId(@PathParam("id_user") Integer id_user) {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Result r WHERE r.id_user = :id_user");
		query.setParameter("id_user", id_user);
		
	    @SuppressWarnings("unchecked")
		List<Result> resultList = (List<Result>) query.getResultList();
	    return resultList;
	}
}
