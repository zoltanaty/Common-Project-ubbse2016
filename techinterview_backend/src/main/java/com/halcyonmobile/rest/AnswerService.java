package com.halcyonmobile.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.halcyonmobile.model.Answer;
import com.halcyonmobile.persistence.Entitymanager;

@Path("/answer")
public class AnswerService {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Answer> findAll() {

		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Answer");

		@SuppressWarnings("unchecked")
		List<Answer> answerList = (List<Answer>) query.getResultList();
		return answerList;
	}

	@GET
	@Path("/{id_question}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Answer> findByQuestion(@PathParam("id_question") Integer idQuestion) {

		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Answer a WHERE a.id_question = :id_question");
		query.setParameter("id_question", idQuestion);

		@SuppressWarnings("unchecked")
		List<Answer> answerList = (List<Answer>) query.getResultList();
		return answerList;
	}

	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertAnswer(String[] answers, int qId, boolean[] isCorrect) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Answer answer = new Answer();

		System.out.println(answers[0] + answers[1] + qId + isCorrect);
		for(int i = 0; i < answers.length; i++) {
			if(!answers[i].equals("")){
				answer.setId_question(qId);
				answer.setAnswer(answers[i]);
				answer.setIsCorrect(isCorrect[i]);
				em.getTransaction().begin();
				em.persist(answer);

				em.flush();
				em.clear();
				em.getTransaction().commit();
			}
		}


	}
}
