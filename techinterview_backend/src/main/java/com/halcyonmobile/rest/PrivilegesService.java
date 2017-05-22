package com.halcyonmobile.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.halcyonmobile.model.Privileges;
import com.halcyonmobile.persistence.Entitymanager;

@Path("/privileg")
public class PrivilegesService {

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Privileges> findAll() {
        EntityManager em = Entitymanager.getEntityManagerInstance();
        Query query = em.createQuery("FROM Privileges p");

        @SuppressWarnings("unchecked")
        List<Privileges> psList = (List<Privileges>) query.getResultList();

        return (ArrayList<Privileges>) psList;
    }

    @GET
    @Path("/{pname}")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Privileges> getIdByName(@PathParam("pname") String privilegName) {
        EntityManager em = Entitymanager.getEntityManagerInstance();
        Query query = em.createQuery("FROM Privileges p where p.nameprivileges like :name");
        query.setParameter("name", privilegName);

        @SuppressWarnings("unchecked")
        List<Privileges> psList = (List<Privileges>) query.getResultList();

        return (ArrayList<Privileges>) psList;
    }
}

