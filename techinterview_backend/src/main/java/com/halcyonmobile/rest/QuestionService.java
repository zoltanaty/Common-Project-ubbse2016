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

	public int insertQuestion(String name, int type, int pos) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		int id = 0;
		
		Question q = new Question();
		
		q.setQuestion(name);
		q.setId_questiontype(type);
		q.setId_position(pos);
		
		em.getTransaction().begin();
		em.persist(q);
		
		id = findIdByQuestion(name);
		em.getTransaction().commit();
		
		return id;
	}
	
	public int findIdByQuestion(String question) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		
		TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q WHERE q.question = :question", Question.class);
		
		return query.setParameter("question", question).getSingleResult().getId();
	}
	
	public Question findById(Integer id) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Question question = em.find(Question.class, id);
		return question;
	}
	
	public void deleteQuestion(Question question) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		
		em.getTransaction().begin();
		em.remove(question);
		em.getTransaction().commit();
	}
}
