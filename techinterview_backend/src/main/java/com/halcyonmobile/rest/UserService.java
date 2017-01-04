package com.halcyonmobile.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.halcyonmobile.model.User;
import com.halcyonmobile.persistence.Entitymanager;

public class UserService {

	public List<User> findAll() {
		
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM User");
		
	    @SuppressWarnings("unchecked")
		List<User> userList = (List<User>) query.getResultList();
	    return userList;
	}

	public User findById(Integer id) {
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
}