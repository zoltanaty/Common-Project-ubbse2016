package com.halcyonmobile.persistence;

import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

public class EntitymanagerTest {

    @Test
    public void getEntityManagerInstance() throws Exception {
        EntityManager em = Entitymanager.getEntityManagerInstance();
        assertNotNull(em);
    }

}