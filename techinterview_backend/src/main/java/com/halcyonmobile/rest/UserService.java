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

	public List<User> findAll() {

		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM User");

		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) query.getResultList();
		return userList;
	}

	@GET
 	@Path("/{id}")
 	@Produces(MediaType.APPLICATION_XML)
 	public User findById(@PathParam("id") Integer id) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		User user = em.find(User.class, id);
		return user;
	}

	public List<User> findByPosition(Integer id_position) {

		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM User u WHERE u.id_position = :id_position");
		query.setParameter("id_position", id_position);

		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) query.getResultList();
		return userList;
	}
	
	public List<User> findByPositionAndByKeyword(Integer id_position, String find) {

		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM User u WHERE u.id_position = :id_position AND u.name LIKE :find");
		query.setParameter("id_position", id_position);
		query.setParameter("find", "%"+find+"%");

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
	
	public void deleteUser(User user) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
	}
}