package com.halcyonmobile.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.halcyonmobile.model.Privileges;
import com.halcyonmobile.persistence.Entitymanager;

@Path("/privilege")
public class PrivilegesService {
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Privileges> findAll() {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Privileges p");
		
		List<Privileges> psList = (List<Privileges>) query.getResultList();
		
		return (ArrayList<Privileges>) psList;
	}
}

