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

import com.halcyonmobile.model.Position;
import com.halcyonmobile.persistence.Entitymanager;

@Path("/position")
public class PositionService {
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_XML)
	public List<Position> findAll() {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Position");
		
	    @SuppressWarnings("unchecked")
		List<Position> positionList = (List<Position>) query.getResultList();
	    return positionList;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Position findById(@PathParam("id") Integer id) {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Position position = em.find(Position.class, id);

		return position;
	}

	@GET
	@Path("/name/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public int findByName(@PathParam("name") String name) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		
		TypedQuery<Position> query = em.createQuery("SELECT p FROM Position p WHERE p.name = :name", Position.class);
		
		return query.setParameter("name", name).getResultList().get(0).getId();
	}

	public void addPosition(String pos, int nrQue) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		
		Position p = new Position();
		p.setName(pos);
		p.setnrQue(nrQue);
		
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
	}
	
	public void deletePosition(Position position) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		
		em.getTransaction().begin();
		em.remove(position);
		em.getTransaction().commit();
	}
}