package com.halcyonmobile.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.halcyonmobile.model.Result;
import com.halcyonmobile.model.User;
import com.halcyonmobile.model.dto.ResultDTO;
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
	
	@POST
	@Path("/{id_user}")
	@Consumes(MediaType.APPLICATION_JSON)
	
	public int registerUser(User user) {
		
		System.out.println(user);
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		Query query = em.createQuery("FROM User u WHERE u.email = :email");
		query.setParameter("email", user.getEmail());		
	    @SuppressWarnings("unchecked")
		List<User> registeredUserList = (List<User>) query.getResultList();
	    em.getTransaction().commit();
	    
	    return registeredUserList.get(0).getId();
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
}
