package com.halcyonmobile.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.halcyonmobile.model.Owners;
import com.halcyonmobile.persistence.Entitymanager;

public class OwnersService {
	
	public ArrayList<Owners> findAll() {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Owners");
		
		@SuppressWarnings("unchecked")
		List<Owners> ownersList = (List<Owners>) query.getResultList();
		
		return (ArrayList<Owners>) ownersList;
	}
	

	public void deleteOwner(String username) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		
		em.getTransaction().begin();
		Query query = em.createQuery("DELETE FROM Owners WHERE username = :username");
		query.setParameter("username", username);
		
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
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
