package com.halcyonmobile.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.halcyonmobile.model.Owners;
import com.halcyonmobile.persistence.Entitymanager;

@Path("/owner")
public class OwnersService {
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Owners> findAll() {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Owners");
		
		List<Owners> ownersList = (List<Owners>) query.getResultList();
		
		return (ArrayList<Owners>) ownersList;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteOwner(String username) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		
		em.getTransaction().begin();
		Query query = em.createQuery("DELETE FROM Owners WHERE username = :username");
		query.setParameter("username", username);
		
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addOwner(String username, String password, int priv){
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Owners owners = new Owners();
		
		owners.setUsername(username);
		owners.setPassword(password);
		owners.setPrivilege("" + priv);
		
		em.getTransaction().begin();
		em.persist(owners);
		em.getTransaction().commit();
	}
}
