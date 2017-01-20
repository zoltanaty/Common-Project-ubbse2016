package com.halcyonmobile.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.halcyonmobile.model.Result;
import com.halcyonmobile.model.dto.ResultDTO;
import com.halcyonmobile.persistence.Entitymanager;

@Path("/result")
public class ResultService {
	
	public List<Result> findAll() {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Result");
		
	    @SuppressWarnings("unchecked")
		List<Result> resultList = (List<Result>) query.getResultList();
	    return resultList;
	}
	
	public Result findById(int id) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Result result = em.find(Result.class, id);
		return result;
	}
	
	public List<Result> findByUserId(Integer id_user) {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Result r WHERE r.idUser = :id_user");
		query.setParameter("id_user", id_user);
		
	    @SuppressWarnings("unchecked")
		List<Result> resultList = (List<Result>) query.getResultList();
	    return resultList;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean receiveResult(ResultDTO resultDTO) {
		
		System.out.println("resuttDTO: " + resultDTO);
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		
		try{
			
			for(Result result : resultDTO.getResultList()){
				if(result.getQuestion()!=null){
					em.getTransaction().begin();
					em.persist(result);
					em.getTransaction().commit();
				}
				
			}
			
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
				
	    
	    return true;
	}
	
	public void setIsCorrect(Result newResultRow) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		em.getTransaction().begin();
		em.merge(newResultRow);
		em.getTransaction().commit();
	}
}
