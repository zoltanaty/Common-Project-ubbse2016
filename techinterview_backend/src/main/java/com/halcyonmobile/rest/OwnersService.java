package com.halcyonmobile.rest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	
	public static String hashPassword(String password) throws UnsupportedEncodingException {
		String genPass = null;
		String salt = "tomcat";
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(salt.getBytes("UTF-8"));
			byte[] bytes = md.digest(password.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			genPass = sb.toString();
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return genPass;
	}
	
	public void addOwner(String username, String password, int priv) throws UnsupportedEncodingException{
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Owners owners = new Owners();
		
		owners.setUsername(username);
		owners.setPassword(hashPassword(password));
		owners.setPrivilege("" + priv);
		
		em.getTransaction().begin();
		em.persist(owners);
		em.getTransaction().commit();
	}
}
