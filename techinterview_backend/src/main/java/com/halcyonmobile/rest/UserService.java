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

import com.halcyonmobile.model.User;
import com.halcyonmobile.persistence.Entitymanager;

@Path("/user")
public class UserService {
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> findAll() {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM User");
		
	    @SuppressWarnings("unchecked")
		List<User> userList = (List<User>) query.getResultList();
	    return userList;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User findById(@PathParam("id") Integer id) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		User user = em.find(User.class, id);
		return user;
	}
	
	@GET
	@Path("/findByPosition/{id_position}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> findByPosition(@PathParam("id_position") Integer id_position) {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM User u WHERE u.id_position = :id_position");
		query.setParameter("id_position", id_position);
		
	    @SuppressWarnings("unchecked")
		List<User> userList = (List<User>) query.getResultList();
	    return userList;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
}