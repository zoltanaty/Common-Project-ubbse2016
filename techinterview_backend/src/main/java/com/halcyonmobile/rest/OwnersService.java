package com.halcyonmobile.rest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.halcyonmobile.model.Owners;
import com.halcyonmobile.persistence.Entitymanager;

@Path("/owner")
public class OwnersService {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Owners> findAll() {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Query query = em.createQuery("FROM Owners");
		
		@SuppressWarnings("unchecked")
		List<Owners> ownersList = (List<Owners>) query.getResultList();
		
		return (ArrayList<Owners>) ownersList;
	}

	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_XML)
	public Owners findById(@PathParam("username") String username) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		Owners owner = em.find(Owners.class, username);
		return owner;
	}

	public void deleteOwner(Owners owner) {
		EntityManager em = Entitymanager.getEntityManagerInstance();
		em.getTransaction().begin();
		em.remove(owner);
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
