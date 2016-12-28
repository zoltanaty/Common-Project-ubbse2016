package com.halcyonmobile.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
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
}
