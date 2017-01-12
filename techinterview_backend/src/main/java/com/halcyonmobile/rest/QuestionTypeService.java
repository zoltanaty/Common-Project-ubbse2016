package com.halcyonmobile.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.halcyonmobile.model.QuestionType;
import com.halcyonmobile.persistence.Entitymanager;

@Path("/questiontype")
public class QuestionTypeService {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<QuestionType> findAll() {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM QuestionType");
		
	    @SuppressWarnings("unchecked")
		List<QuestionType> questionTypeList = (List<QuestionType>) query.getResultList();
	    return questionTypeList;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public QuestionType findById(@PathParam("id") Integer id) {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		QuestionType questionType = em.find(QuestionType.class, id);

		return questionType;
	}

	public int findByName(String name) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		
		TypedQuery<QuestionType> query = em.createQuery("SELECT q FROM QuestionType q WHERE q.name = :name", QuestionType.class);
		
		return query.setParameter("name", name).getSingleResult().getId();
	}
}
