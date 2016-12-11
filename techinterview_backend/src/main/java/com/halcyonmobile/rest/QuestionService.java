package com.halcyonmobile.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.halcyonmobile.model.Question;
import com.halcyonmobile.persistence.Entitymanager;

@Path("/question")
public class QuestionService {
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Question> findAll() {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Question");
		
	    @SuppressWarnings("unchecked")
		List<Question> questionList = (List<Question>) query.getResultList();
	    return questionList;
	}
	
	@GET
	@Path("/{id_position}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Question> findByPosition(@PathParam("id_position") Integer idPosition) {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Question q WHERE q.id_position = :id_position");
		query.setParameter("id_position", idPosition);
		
	    @SuppressWarnings("unchecked")
		List<Question> questionList = (List<Question>) query.getResultList();
	    return questionList;
	}

}
